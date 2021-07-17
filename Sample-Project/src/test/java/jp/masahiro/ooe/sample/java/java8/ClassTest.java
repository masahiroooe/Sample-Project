package jp.masahiro.ooe.sample.java.java8;

import org.junit.Test;

public class ClassTest {

    @Test
    public void forNameTest() throws Exception{
        Object o = Class.forName("jp.masahiro.ooe.sample.java.java8.StreamAPITest").newInstance();

        if (o instanceof StreamAPITest) {
            System.out.println("--- o ---");
            ((StreamAPITest)o).test1();

            System.out.println("--- new instance st ---");
            StreamAPITest st = (StreamAPITest) o.getClass().newInstance();

            st.test1();
            System.out.println("------");
    
        } else {

            throw new ClassCastException();

        }

    }
    
}
