package com.education;

/**
 * 概率
 */
public class RandToRand {

    public static void main(String[] args) {
        int testTimes = 1000000;
        int k = 9;
        int[] counts = new int[k];
        for(int i = 0 ; i < testTimes; i++) {
            // Math.random [0,1)
            // ans  [0, k-1]整数
            int ans = (int)(Math.random() * k);
            counts[ans]++;
        }
        for(int i = 0; i < k; i++) {
            System.out.println("第" + i + "个数次数：" + counts[i]);
        }
        System.out.println("========");

        // 返回[0-1)的一个小数
        // 任意的x, x属于[0,1), [0,x)范围上的数出现概率由x调整成x平方
        int count = 0;
        double x = 0.7;
        for(int i = 0 ; i < testTimes; i++) {
            if(xToXPower2() < x) {
                count++;
            }
        }
        System.out.println((double)count / (double) testTimes);
        System.out.println(Math.pow(x, 2));
        System.out.println("========");


        count = 0;
        x = 0.7;
        for(int i = 0 ; i < testTimes; i++) {
            if(xToXRemainder() < x) {
                count++;
            }
        }
        System.out.println((double)count / (double) testTimes);
        System.out.println(1 - Math.pow(1 - x, 2));
        System.out.println("========");

        // 返回[0-1)的一个小数
        // 任意的x, x属于[0,1), [0,x)范围上的数出现概率由x调整成x^3
        count = 0;
        x = 0.7;
        for(int i = 0 ; i < testTimes; i++) {
            if(xToXPower3() < x) {
                count++;
            }
        }
        System.out.println((double)count / (double) testTimes);
        System.out.println(Math.pow(x, 3));
        System.out.println("========");

        // 提供[1,5]整数等概率
        // 用提供的该函数求得等概率返回[1,7]整数
        k = 8;
        counts = new int[k];
        for(int i = 0 ; i < testTimes; i++) {
            int ans = f3();
            counts[ans]++;
        }
        for(int i = 0; i < k; i++) {
            System.out.println("第" + i + "个数次数：" + counts[i]);
        }
        System.out.println("========");


        k = 2;
        counts = new int[k];
        for(int i = 0 ; i < testTimes; i++) {
            int ans = g1();
            counts[ans]++;
        }
        for(int i = 0; i < k; i++) {
            System.out.println("第" + i + "个数次数：" + counts[i]);
        }
    }

    // 返回[0-1)的一个小数
    // 任意的x, x属于[0,1), [0,x)范围上的数出现概率由x调整成x平方
    public static double xToXPower2() {
        return Math.max(Math.random(), Math.random());
    }

    // 返回[0-1)的一个小数
    // 任意的x, x属于[0,1), [0,x)范围上的数出现概率由x调整成1-(1-x)^2
    // 两次[x, 1)的概率是(1-x)^2
    // 1-(1-x)^2则是两次得不到[x, 1)的概率值
    // 即为Math.min(Math.random(), Math.random())的概率值
    public static double xToXRemainder() {
        return Math.min(Math.random(), Math.random());
    }

    // 返回[0-1)的一个小数
    // 任意的x, x属于[0,1), [0,x)范围上的数出现概率由x调整成x^3
    public static double xToXPower3() {
        return Math.max(Math.random(), Math.max(Math.random(), Math.random()));
    }

    // 提供[1,5]整数等概率
    // 不能修改该函数
    // 用提供的该函数求得等概率返回[1,7]整数
    public static int f() {
        return (int)(Math.random() * 5) + 1;
    }

    // 等概率返回0和1
    public static int f1() {
        int ans = 0;
        do {
            ans = f();
        } while (ans == 3);
        return ans < 3 ? 0 : 1;
    }

    // 按每一位上随机0-1 得到 000 - 111
    // 得到[0-7]等概率整数
    public static int f2() {
        return (f1() << 2) + (f1() << 1) + f1();
    }

    // 当出现7 重新随机
    // 得到[0-6]等概率整数
    // +1得到[1-7]等概率整数
    public static int f3() {
        int ans = 0;
        do {
           ans = f2();
        } while (ans == 7);
        return ans + 1;
    }

    // 给定一个固定概率(不相等)得到0和1的函数
    public static int g() {
        return Math.random() < 0.83 ? 0 : 1;
    }

    // 等概率返回0 或者 1
    public static int g1() {
        int ans = 0;
        // 两次得到结果为
        // 0 0  为83%
        // 1 1  为17%
        // 0 1  为83% * 17%
        // 1 0  为17% * 83%
        do {
            ans = g();
        } while (ans == g());
        return ans;
    }

}
