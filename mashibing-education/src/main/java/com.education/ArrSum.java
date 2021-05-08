package com.education;

/**
 * 求数组 n-m 位数字和
 * 1.前缀和数组
 * 2.i*i矩阵 把所有可能的n-m位数字和提前求出放入矩阵对应位置
 */
public class ArrSum {

    public static void main(String[] args) {
        int[] arr = {5, 8, -7, 2, 4, 3, 1};
        int[] preSum = createSum(arr);
        for(int i = 0; i < preSum.length; i++) {
            System.out.print(preSum[i] + " ,");
        }
        System.out.println();
        int sum = rangePreSum(preSum, 2,5);
        System.out.println(sum);
    }

    /**
     * 计算前缀和数组
     * 原始数组：[1, 3, 7, -1, 5]
     * 前缀和数组每一项为原始数组前n项之和
     * 前缀和数组：[1, 4, 11, 10, 15]
     * @param arr
     * @return
     */
    public static int[] createSum(int[] arr) {
        int[] ans = new int[arr.length];
        ans[0] = arr[0];
        for(int i = 1; i < arr.length; i++) {
            ans[i] = ans[i - 1] + arr[i];
        }
        return ans;
    }

    /**
     * Sum(n到m) = 前缀和数组第m项 - 第n-1项(n = 0时取第0项)
     * @return
     */
    public static int rangePreSum(int arr[], int n, int m) {
        return n == 0 ? arr[m] : arr[m] - arr[n - 1];
    }

}
