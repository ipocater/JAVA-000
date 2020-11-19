import com.ipoca.SpringDI.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    @org.junit.Test
    public void text(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        People people =(People) context.getBean("people");
        System.out.println("---------------------------------------------------------");
        System.out.println(people);
        people.getCat().say();
        people.getDog().say();
        System.out.println("---------------------------------------------------------");
        People1 people1 =(People1) context.getBean("people1");
        System.out.println(people1);
        people1.getCat().say();
        people1.getDog().say();
        System.out.println("---------------------------------------------------------");
        People2 people2 =(People2) context.getBean("people2");
        System.out.println(people2);
        people2.getCat().say();
        people2.getDog().say();
        System.out.println("---------------------------------------------------------");
        People3 people3 =(People3) context.getBean("people3");
        System.out.println(people3);
        people3.getCat().say();
        people3.getDog().say();
        System.out.println("---------------------------------------------------------");
        People4 people4 =(People4) context.getBean("people4");
        System.out.println(people4);
        people4.getCat().say();
        people4.getDog().say();
    }

}
