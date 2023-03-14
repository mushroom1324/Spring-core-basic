package hello.core.singleton;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadB : order 20000
        int userAprice = statefulService1.order("userA", 10000);
        // ThreadA : order 10000
        int userBprice = statefulService2.order("userB", 20000);

        //ThreadA : 사용자A 주문 금액 조회
        System.out.println("price = " + userAprice);

        Assertions.assertThat(userAprice).isEqualTo(10000);
    }

    static class TestConfig {


        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}