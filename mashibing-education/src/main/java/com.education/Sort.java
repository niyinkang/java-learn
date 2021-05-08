package com.education;

public class Sort {

    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 8, 6, 4, 9, 2, 3};
        selectSort(arr);
        print(arr);

        arr = new int[]{5, 7, 1, 8, 6, 4, 9, 2, 3};
        bubbleSort(arr);
        print(arr);

        arr = new int[]{5, 7, 1, 8, 6, 4, 9, 2, 3};
        insertSort(arr);
        print(arr);

        // 对数器
        int maxLen = 50;
        int maxValue = 1000;
        int testTime = 10000;
        for(int i = 0; i < testTime; i++) {
            int[] arr1 = lenRandomValueRandom(maxLen, maxValue);
            int[] arr2 = copy(arr1);
            selectSort(arr1);
            insertSort(arr2);
            if(!checkValues(arr1)) {
                System.out.println("选择排序错误！");
            }
            if(!checkValues(arr2)) {
                System.out.println("插入排序错误！");
            }
        }
        System.out.println("排序正确！");
    }

    public static void print(int[] arr) {
        for (int i = 0;i < arr.length;i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println();
    }

    /**
     * 选择排序
     * 第1位 从后面数组选出最小值与第1位交换
     * 第2位 从后面数组选出最小值与第2位交换
     * 以此类推到第n位
     * @param arr
     */
    public static void selectSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ length-1
        // 1 ~ length-1
        for(int i = 0 ; i < arr.length; i++) {
            int minValueIndex = i;
            // 1 ~ length-1
            // 2 ~ length-1
            for(int j = i + 1; j < arr.length; j++) {
                minValueIndex = arr[j] < arr[minValueIndex] ? j : minValueIndex;
            }
            swap(arr, i, minValueIndex);
        }
    }

    /**
     * 冒泡排序
     * 从第1位与第2位判断是否交换
     *   第2位与第3位判断是否交换
     *   直到第n-1位与第n位判断是否交换
     * 从第1位与第2位判断是否交换
     *   第2位与第3位判断是否交换
     *   直到第n-2位与第n-1位判断是否交换
     * 以此类推到第1位
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ length-1
        // 0 ~ length-2
        for(int i = arr.length - 1; i >= 0; i--) {
            for(int j = 1; j <= i; j++) {
                if(arr[j - 1] > arr[j]) {
                    swap(arr, j - 1, j);
                }
            }
        }
    }

    /**
     * 插入排序
     * 从第2位开始保证1 ~ 2有序
     * 从第3位开始保证1 ~ 3有序
     * 以此类推第n位
     * @param arr
     */
    public static void insertSort(int[] arr) {
        if(arr == null || arr.length < 2) {
            return;
        }
        // 0 ~ 1
        // 0 ~ 2
        // 0 ~ length-1
        for(int i = 1; i < arr.length; i++) {
            for(int j = i; j - 1 >= 0 && arr[j - 1] > arr[j]; j--) {
                swap(arr, j - 1, j);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    /**
     * 返回一个随机长度 随机值的数组
     * @param maxLen
     * @param maxValue
     * @return
     */
    public static int[] lenRandomValueRandom(int maxLen, int maxValue) {
        int len = (int)(Math.random() * maxLen);
        int[] ans = new int[len];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int)(Math.random() * maxValue);
        }
        return ans;
    }

    public static int[] copy(int[] arr) {
        int[] ans = new int[arr.length];
        for(int i = 0; i < arr.length; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }

    public static boolean checkValues(int[] arr) {
        if(arr.length < 2) {
            return true;
        }
        int max = arr[0];
        for(int i = 1; i < arr.length; i++) {
            if (max > arr[i]) {
                return false;
            }
            max = Math.max(max, arr[i]);
        }
        return true;
    }

}
