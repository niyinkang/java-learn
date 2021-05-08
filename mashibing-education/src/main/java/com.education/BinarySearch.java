package com.education;

public class BinarySearch {

    /**
     * 二分查找法
     * @param arr 保证有序
     * @param num
     * @return
     */
    public static boolean find(int[] arr, int num) {
        if(arr == null || arr.length == 0) {
            return false;
        }
        int L = 0;
        int R = arr.length - 1;
        while(L <= R) {
            int mid = (L + R) / 2;
            if(arr[mid] == num) {
                return true;
            } else if(arr[mid] < num) {
                L = mid + 1;
            } else {
                R = mid - 1;
            }
        }
        return false;
    }

    /**
     * 找到>=num最左数字的位置
     * @param arr 保证有序
     * @param num
     * @return
     */
    public static int mostLeftNoLessNumIndex(int[]arr, int num) {
        if(arr == null || arr.length == 0) {
            return -1;
        }
        int L = 0;
        int R = arr.length - 1;
        int ans = -1;
        while(L <= R) {
            int mid = (L + R) / 2;
            if(arr[mid] >= num) {
                ans = mid;
                R = mid - 1;
            } else {
                L = mid + 1;
            }
        }
        return ans;
    }

    /**
     * 返回其中一个局部最小数的位置
     * 两头情况：
     * [0] < [1] 则0位置为局部最小
     * [N-1] < [N-2] 则N-1位置为局部最小
     * 中间情况：
     * [i-1] > [i] < [i+1]  则i位置为局部最小
     *
     * @param arr  整数无序  相邻的数不相等
     * @return
     */
    public static int oneMinIndex(int[] arr) {
        if(arr == null || arr.length == 0) {
            return -1;
        }
        int N = arr.length;
        if(N == 1) {
            return 0;
        }
        if(arr[0] < arr[1]) {
            return 0;
        }
        if(arr[N-1] < arr[N-2]) {
            return N-1;
        }
        int L = 0;
        int R = N-1;
        while(L < R - 1) {
            int mid = (L + R) / 2;
            if(arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
                return mid;
            }
            if(arr[mid] > arr[mid - 1]) {
                R = mid - 1;
                continue;
            }
            if(arr[mid] > arr[mid + 1]) {
                L = mid + 1;
                continue;
            }
        }
        return arr[L] < arr[R] ? L : R;
    }

    public static void main(String[] args) {
        int[] arr = {0, 2, 2, 5, 6, 7, 9};
        System.out.println(find(arr, 7));

        System.out.println(mostLeftNoLessNumIndex(arr, 8));

        int[] arr1 = {3, 2, 9, 2, 5, 7, 6, 8, 2, 3};
        System.out.println(oneMinIndex(arr1));
    }

}
