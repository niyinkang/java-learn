package com.education;

public class PrintBinary {

    public static void main(String[] args) {
        int a = 100;
        print(a);

        // 负数 = 取反 + 1
        int b = ~a + 1;
        print(b);
        System.out.println(b);

        print(a << 2);
        System.out.println(a << 2);
        print(b >> 2);
        System.out.println(b >> 2);
        print(b >>> 2);
        System.out.println(b >>> 2);

        int c = Integer.MAX_VALUE;
        System.out.println(~c + 1);

        int d = Integer.MIN_VALUE;
        System.out.println(~d + 1);

        System.out.println("******************");
        print(a);
        print(b);
        print(a ^ b);
        int e = 0;
        e ^= a;
        print(e);
    }

    public static void print(int num) {
        for(int i = 31; i >= 0; i--) {
            System.out.print((num & (1 << i)) == 0 ? "0" : "1");
        }
        System.out.println();
    }

}
