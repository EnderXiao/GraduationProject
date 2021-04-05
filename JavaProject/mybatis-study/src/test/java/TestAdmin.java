import com.ender.dao.AdminMapper;
import com.ender.entity.Admin;
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

/**
 * @author Ender-PC
 * @date 2021/2/2
 */
public class TestAdmin {

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
    public void testSelectAdmins(){
        //使用代理调用dao
        AdminMapper mapper = session.getMapper(AdminMapper.class);
        List<Admin> admins = mapper.selectAdmins();
        for (Admin admin : admins) {
            System.out.println(admin);
        }
    }

    @Test
    public void testSelectAdminsById(){
        AdminMapper mapper = session.getMapper(AdminMapper.class);
        Admin admin = mapper.selectAdminById(1);
        System.out.println(admin);
    }

    @Test
    public void testSaveAdmin(){
        AdminMapper mapper = session.getMapper(AdminMapper.class);
        Admin admin = new Admin(4, "赵六", "123");
        int rows = mapper.saveAdmin(admin);
        System.out.println(rows);
    }

    @Test
    public void testUpdateAdmin(){
        AdminMapper mapper = session.getMapper(AdminMapper.class);
        Admin admin = new Admin(4, "赵六2", "dsjioa123");
        int rows = mapper.updateAdmin(admin);
        System.out.println(rows);
    }

    @Test
    public void testDeleteAdmin(){
        AdminMapper mapper = session.getMapper(AdminMapper.class);
        int rows = mapper.deleteAdmin(4);
        System.out.println(rows);
    }


    @After
    public void after(){
        session.commit();
        session.close();
    }
}
