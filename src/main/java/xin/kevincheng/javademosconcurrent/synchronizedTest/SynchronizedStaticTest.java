package xin.kevincheng.javademosconcurrent.synchronizedTest;

/**
 * @author kc
 * @title
 * @description The class SynchronizedStaticTest is for
 * @date Create in 16:52 2019/12/10
 */
public class SynchronizedStaticTest {
    public static synchronized void method1() {
        System.out.println("Method 1 Start");
        try {
            System.out.println("Method 1 Execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Method 1 End");
    }

    public static synchronized void method2() {
        System.out.println("Method 2 Start");
        try {
            System.out.println("Method 2 Execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Method 2 End");
    }

    public static void method3() {
        System.out.println("Method 3 Start");
        try {
            synchronized (SynchronizedStaticTest.class) {
                System.out.println("Method 3 Execute");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Method 3 End");
    }

    /**
     * @Author k5068
     * @Date 2019/12/10 17:51
     * @Description This is description of method
     *              We can see that,
     *              different static synchronized methods needs to be waited others finished,
     *              besides, if you have synchronized just on block.
     *              The resources in static method before synchronized block can be visited after one synchronized method in sleep.
     *              After all synchronized things achieve the Gate without variables not synchronized visited, then they will be finished one by one.
     * @Param []
     * @Return void
     * @Since
     */
    public void methodTest() {
        final SynchronizedStaticTest test = new SynchronizedStaticTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method2();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method3();
            }
        }).start();
    }

    public void methodOnClass() {
        final SynchronizedStaticTest test = new SynchronizedStaticTest();
        final SynchronizedStaticTest test2 = new SynchronizedStaticTest();
        final SynchronizedStaticTest test3 = new SynchronizedStaticTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test2.method2();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test3.method3();
            }
        }).start();
    }

    public static void main(String[] args) {
        final SynchronizedStaticTest test = new SynchronizedStaticTest();

//        test.methodTest();
        test.methodOnClass();
    }
}
