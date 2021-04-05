package top.ender;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Ender-PC
 * @date 2021/2/8
 */
public class UserTest {

    @Test
    public void iocTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("application.xml");
        User user = (User) applicationContext.getBean("user1");
        System.out.println(user);
    }
}
