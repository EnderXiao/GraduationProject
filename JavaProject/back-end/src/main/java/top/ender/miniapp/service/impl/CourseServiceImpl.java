package top.ender.miniapp.service.impl;

import org.apache.ibatis.jdbc.Null;
import top.ender.miniapp.entity.Course;
import top.ender.miniapp.dao.CourseDao;
import top.ender.miniapp.service.CourseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * (Course)表服务实现类
 *
 * @author makejava
 * @since 2021-02-12 11:02:55
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseDao courseDao;

    /**
     * 通过ID查询单条数据
     *
     * @param courseId 主键
     * @return 实例对象
     */
    @Override
    public Course queryById(Integer courseId) {
        return this.courseDao.queryById(courseId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Course> queryAllByLimit(int offset, int limit) {
        return this.courseDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    @Override
    public Course insert(Course course) {
        this.courseDao.insert(course);
        return course;
    }

    /**
     * 查询多条数据
     * @param course 查询对象
     * @return 对象列表
     */
    @Override
    public List<Course> queryAll(Course course){
        return this.courseDao.queryAll(course);
    }

    /**
     * 修改数据
     *
     * @param course 实例对象
     * @return 实例对象
     */
    @Override
    public Course update(Course course) {
        this.courseDao.update(course);
        return this.queryById(course.getCourseId());
    }

    /**
     * 通过主键删除数据
     *
     * @param courseId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer courseId) {
        return this.courseDao.deleteById(courseId) > 0;
    }

    /**
     * 通过班级Id查询课程
     *
     * @param classId 课程ID
     * @return 对象列表
     */
    @Override
    public List<Course> queryByClass(Integer classId,Integer week){
        List<Course> courses = this.courseDao.queryByClass(classId,week);
        return courses;
    }
}
