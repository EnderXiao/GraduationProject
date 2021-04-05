import com.ender.entity.Dept;
import com.ender.entity.Employee;
import com.ender.mapper.DeptMapper;
import com.ender.mapper.EmployeeMapper;
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
 * @date 2021/2/7
 */
public class DeptTest {

    private SqlSession session;

    @Before
    public void befor(){
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
    public void testFindDepts() {
        DeptMapper mapper = session.getMapper(DeptMapper.class);
        List<Dept> depts = mapper.findDepts();
        for (Dept dept : depts) {
            System.out.println(dept);
        }
    }

    @After
    public void after(){
        session.commit();
        session.close();
    }
}
