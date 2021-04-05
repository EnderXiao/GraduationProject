import com.ender.dao.UserMapper;
import com.ender.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Ender-PC
 * @date 2021/2/2
 */
public class TestUser {

    private SqlSession session;

    @Before
    public void before(){
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testSelectUsers(){
        //使用代理调用dao
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.selectUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectUsersById(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.selectUserById(1);
        System.out.println(user);
    }

    @Test
    public void testSaveUser(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User(4, "", "123");
        int rows = mapper.saveUser(user);
        System.out.println(rows);
    }

    @Test
    public void testUpdateUser(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = new User(3, "肖三", "");
        int rows = mapper.updateUser(user);
        System.out.println(rows);
    }

    @Test
    public void testDeleteUser(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        int rows = mapper.deleteUser(4);
        System.out.println(rows);
    }

    @Test
    public void testFindUsersByParam(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user1 = new User();
        user1.setUUsername("赵六");
        List<User> users = mapper.findUsersByParam(user1);
        for (User user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void testfuzzyCheckUser(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user1 = new User();
        user1.setUUsername("三");
        List<User> users = mapper.fuzzyCheckUser(user1);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindUsersByIds(){
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> users = mapper.selectUsersByIds(new int[]{1,2});
        for (User user : users) {
            System.out.println(user);
        }
    }
    @After
    public void after(){
        session.commit();
        session.close();
    }
}
