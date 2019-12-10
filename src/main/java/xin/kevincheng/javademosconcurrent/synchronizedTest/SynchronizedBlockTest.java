package xin.kevincheng.javademosconcurrent.synchronizedTest;

/**
 * @author kc
 * @title
 * @description The class SynchronizedBlockTest is for
 * @date Create in 17:11 2019/12/10
 */
public class SynchronizedBlockTest {
    public void method1() {
        System.out.println("Block 1 Start");
        try {
            synchronized (this) {
                System.out.println("Block 1 Execute");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Block 1 End");
    }

    public void method2() {
        System.out.println("Block 2 Start");
        try {
            synchronized (this) {
                System.out.println("Block 2 Execute");
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Block 2 End");
    }

    /**
     * @Author k5068
     * @Date 2019/12/10 17:22
     * @Description This is description of method
     *              We can see the synchronized blocks are synchronized,
     *              but the code before them can be visited.
     *              Which means sometimes we are not need to go through the whole method,
     *              just visit the variables before synchronized block.
     * @Param []
     * @Return void
     * @Since
     */
    public void methodTest() {
        SynchronizedBlockTest test = new SynchronizedBlockTest();

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
    }

    /**
     * @Author k5068
     * @Date 2019/12/10 17:24
     * @Description This is description of method
     *              As the method above said,
     *              however,
     *              this method shows another situation,
     *              which calls method on different class entities.
     *              That will be let the synchronized key not work,
     *              because different entities use different cpu space.
     * @Param []
     * @Return void
     * @Since
     */
    public void methodOnClassTest() {
        SynchronizedBlockTest test = new SynchronizedBlockTest();
        SynchronizedBlockTest test1 = new SynchronizedBlockTest();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                test1.method2();
            }
        }).start();
    }

    public static void main(String[] args) {
        SynchronizedBlockTest test = new SynchronizedBlockTest();
        test.methodOnClassTest();
    }
}
