package com.kure.test.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

public class PhantomReferenceTest {
static final List<Object> LIST = new LinkedList<>();
    static ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(),QUEUE);

        System.out.println(phantomReference.get());

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);

        new Thread(()-> {
            while (true) {
                LIST.add(new byte[1024*1024]);
                try {
                    Thread.sleep(1000);
                    System.out.println(phantomReference.get());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()-> {
            while (true) {
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("jvm回收"+poll);
                }
            }
        }).start();
    }
}

class M {

}
