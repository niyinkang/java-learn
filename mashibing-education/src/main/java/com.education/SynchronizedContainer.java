package com.education;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 设计一个阻塞容量固定的容器
 *
 * 两个线程生产 十个线程消费
 * @param <T>
 */
public class SynchronizedContainer<T> {
    private LinkedList<T> list = new LinkedList<>();
    private int count = 0;
    private final int MAX = 10;

    private Lock lock = new ReentrantLock();
    private Condition producerCondition = lock.newCondition();
    private Condition consumerCondition = lock.newCondition();

    public synchronized void put(T t) {
        // 要用while 不能用if
        // 因为被叫醒后 还要重新判断队列是否满的
        // 满的话则需要继续等待
        while(count == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        count++;
        this.notifyAll();
    }

    public synchronized T get() {
        // 要用while 不能用if
        // 因为被叫醒后 还要重新判断队列是否为空
        // 为空则需要继续等待
        while(count == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T t = list.pollFirst();
        count--;
        this.notifyAll();
        return t;
    }

    /**
     * 高级put
     * 尽可能均衡的生产与消费
     * @param t
     */
    public void condPut(T t) {
        try {
            lock.lock();
            while(count == MAX) {
                producerCondition.await();
            }
            list.add(t);
            count++;
            consumerCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 高级get
     * @return
     */
    public T condGet() {
        T t = null;
        try {
            lock.lock();
            while(count == 0) {
                consumerCondition.await();
            }
            t = list.pollFirst();
            count--;
            producerCondition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] args) {
        SynchronizedContainer<String> test = new SynchronizedContainer<>();

        for(int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                for(int j = 0; j < 200; j++) {
                    System.out.println(test.condGet());
                }
            });
            t.start();
        }

        for(int i = 0; i < 2; i++) {
            Thread t = new Thread(() -> {
                for(int j = 0; j < 1000; j++) {
                    test.condPut(Thread.currentThread().getName() + "-" + j);
                }
            }, i + "");
            t.start();
        }

    }

}
