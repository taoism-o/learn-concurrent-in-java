package xin.kevincheng.javademosconcurrent.synchronizedTest;

/**
 * @author kc
 * @title
 * @description The class SynchronizedTest is for
 * @date Create in 16:41 2019/12/10
 */
public class SynchronizedTest {
    public synchronized void method1() {
        System.out.println("Method 1 Start");
        try {
            System.out.println("Method 1 Execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Method 1 End");
    }

    public synchronized void method2() {
        System.out.println("Method 2 Test");

        try {
            System.out.println("Method 2 Execute");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Method 2 End");
    }

    /**
     * @Author k5068
     * @Date 2019/12/10 16:58
     * @Description This is description of method
     *              We can see, as added synchronized key on methods,
     *              there is a synchronized method queue found,
     *              one method runs needs to be waited for other ones finished.
     * @Param []
     * @Return void
     * @Since
     */
    public void methodTest() {
        final SynchronizedTest test = new SynchronizedTest();

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
     * @Date 2019/12/10 17:00
     * @Description This is description of method
     *              However, like the method just above,
     *              there is a additional situation to break that rule.
     *              Yes, to make two class entities.
     *              While synchronized key on method,
     *              which means they will be synchronized in one entity.
     *              That's true, but
     *              if you got two entities, they would change,
     *              because of entity selves are not in one Thread.
     * @Param []
     * @Return void
     * @Since
     */
    public void methodOnClassTest() {
        final SynchronizedTest test = new SynchronizedTest();
        final SynchronizedTest test2 = new SynchronizedTest();

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
    }

    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();

        test.methodTest();
        test.methodOnClassTest();
    }
}
