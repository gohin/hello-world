package com.kure.test.gc;

import org.openjdk.jol.info.ClassLayout;

/**
 * jol 查看对象内存结构 %8 = 0 长度需要被8整除
 * 对象头 markword (gc info + hashcode + lock info) + klass pointer
 * 实例
 * 对齐padding
 * 无锁  unused:25 | identity_hashcode:31 | unused:1 | age:4 | biased_lock:1 | lock:2
 * 偏向  thread ：54             | epoch：2 | unused:1 | age:4 | biased_lock:1 | lock:2
 * 轻量 ptr_to_lock_record:62												  | lock:2
 * 重量 ptr_to_heavyweight_moniter:62										  | lock:2
 * GC  空																	  | lock:2
 *
 * MarkWord存储内容       偏向标记  对象状态     状态
 * hashcode，age           0      01          无锁
 * 偏向线程id，age          1      01 		  偏向锁
 * 指向锁记录的指针				   00		  轻量锁
 * 指向重量级锁的指针				   10 		  重量锁
 * 空							   11          GC
 */
public class JvmTest {
    static String s;
    public static void main(String[] args) {
        JvmTest o = new JvmTest();
        /**
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
         *      12     4        (loss due to the next object alignment)
         *
         *
         *      00000001 unused:1 age:4 biased_lock:1 lock:2
         */
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        /**
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           28 f4 6e 02 (00101000 11110100 01101110 00000010) (40825896)
         *       4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
         *       8     4        (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
         *      12     4        (loss due to the next object alignment)
         *
         *      0       0101    0       00
         *      未使用           未偏向   轻量锁
         */
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }

        /**
         *  OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
         *       0     4        (object header)                           01 dc 3a 13 (00000001 11011100 00111010 00010011) (322624513)
         *       4     4        (object header)                           66 00 00 00 (01100110 00000000 00000000 00000000) (102)
         *       8     4        (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
         *      12     4        (loss due to the next object alignment)
         *
         *      从低位往高位看 hashcode的16进制是66133adc，从对象头第二行的VALUE第一个往前数正好是1540e19d.
         */
        o.hashCode();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
