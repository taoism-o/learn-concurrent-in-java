package xin.kevincheng.javademosconcurrent;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JavaDemosConcurrentApplicationTests {

    @Test
    void contextLoads() {
        int i = 1;
        for (; ; ) {
            System.out.println(new StringBuilder().append("dd").append(i));

            if (i == 100) {
                break;
            }
            i++;
        }
    }

}
