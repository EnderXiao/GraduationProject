import com.ender.entity.Employee;
import com.ender.mapper.EmployeeMapper;
import com.mysql.cj.Session;
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
public class EmployeeTest {

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
    public void testFindEmployees() {
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        List<Employee> allEmployees = mapper.findEmployees();
        for (Employee allEmployee : allEmployees) {
            System.out.println(allEmployee);
        }
    }

    @Test
    public void testFindEmployessById(){
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Employee employee = mapper.findEmployeesById(1);
        System.out.println(employee);
    }

    @After
    public void after(){
        session.commit();
        session.close();
    }
}
