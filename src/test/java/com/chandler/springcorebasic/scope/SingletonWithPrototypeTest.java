package com.chandler.springcorebasic.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

public class SingletonWithPrototypeTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class); // 둘다 넣어줘야 의존관계 주입도 됩니다

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);

        assertEquals(1, clientBean1.logic());
        assertEquals(2, clientBean2.logic()); // 의도한게 아님

        ac.close();
    }

    @Scope
    static class ClientBean {

        private final PrototypeBean prototypeBean;

        @Autowired
        public ClientBean(PrototypeBean prototypeBean) {
            this.prototypeBean = prototypeBean;
        }

        public int logic() {

            prototypeBean.addCount();
            return prototypeBean.getCount();
        }

    }

    @Scope(value = SCOPE_PROTOTYPE)
    static class PrototypeBean {

        private int count = 0;

        public void addCount() {
            this.count++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init : " + this);
        }

        @PreDestroy
        public void close() {
            System.out.println("PrototypeBean.close : " + this);
        }

    }
}
