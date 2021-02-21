package com.kure.test.exception;

public class FinallyTest {
//    static int f() {
//        byte var0 = 0;
//
//        byte var1;
//        try {
//            var1 = var0;
//        } finally {
//            int var5 = var0 + 1;
//            System.out.println("finally执行");
//        }
//
//        return var1;
//    }

//    static int[] f2() {
//       int[] var0 = new int[]{0};
//
//    int[] var1;
//        try {
//        var1 = var0;
//    } finally {
//        int var10002 = var0[0]++;
//        System.out.println("finally执行");
//    }
//
//        return var1;
//}

    public static void main(String[] args) {
        System.out.println(FinallyTest.f());
        System.out.println(FinallyTest.f2()[0]);
    }

    // 测试 修改值类型
    static int f() {
        int ret = 0;
        try {
            return ret;  // 返回 0，finally内的修改效果不起作用
        } finally {
            ret++;
            System.out.println("finally执行");
        }
    }

    // 测试 修改引用类型
    static int[] f2(){
        int[] ret = new int[]{0};
        try {
            return ret;  // 返回 [1]，finally内的修改效果起了作用
        } finally {
            ret[0]++;
            System.out.println("finally执行");
        }
    }
}




