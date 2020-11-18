package com.kure.test.gc;

/**
 * finalize 方法只会被执行一次
 * 如果对象面临第二次回收，finalize不会执行
 *
 * 新生代包括 eden survivor0 survivor1 标记-复制法 将 eden区回收放入 survivor0
 * eden区满，执行yong gc
 * old区满，执行full gc 标志整理法
 *
 * 强引用 strongly reference 只要引用存在 就不会被回收
 * 软引用 soft reference 内存溢出前 回收
 * 弱引用 weak reference 下一次gc时收回
 *
 * 新生代垃圾收集器 serial(单线程) parNew(多线程) parallel
 * 老年代垃圾收集器 CMS serial-old（标志-整理） parallel-old（标记-整理）
 * jdk9之后 G1收集器
 *
 * 查看jdk默认的垃圾收集器 java -XX:+PrintCommandLineFlags -version
 * jdk8 默认 Parallel Scavenge + Parallel Old
 *
 */
public class FinalizeEscapeGc {

    public static FinalizeEscapeGc SAVE_HOKE = null;

    public void isAlive(){
        System.out.println("yes, i am still alive");
    }


    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("method finalize is exec");
        FinalizeEscapeGc.SAVE_HOKE = this;
    }

    public static void main(String[] args) throws Throwable{
        SAVE_HOKE = new FinalizeEscapeGc();

        SAVE_HOKE = null;
        System.gc();

        // Finalize方法优先级低 先等待0.5s
        Thread.sleep(500);

        if(SAVE_HOKE != null) {
            SAVE_HOKE.isAlive();
        } else {
            System.out.println("no i am dead");
        }


        SAVE_HOKE = null;
        System.gc();

        Thread.sleep(500);

        if(SAVE_HOKE != null) {
            SAVE_HOKE.isAlive();
        } else {
            System.out.println("no i am dead");
        }
    }

}
