package top.ender.miniapp.service.impl;

import top.ender.miniapp.entity.Classroom;
import top.ender.miniapp.dao.ClassroomDao;
import top.ender.miniapp.service.ClassroomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Classroom)表服务实现类
 *
 * @author makejava
 * @since 2021-02-12 11:02:15
 */
@Service("classroomService")
public class ClassroomServiceImpl implements ClassroomService {
    @Resource
    private ClassroomDao classroomDao;

    /**
     * 通过ID查询单条数据
     *
     * @param classRoomId 主键
     * @return 实例对象
     */
    @Override
    public Classroom queryById(Integer classRoomId) {
        return this.classroomDao.queryById(classRoomId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<Classroom> queryAllByLimit(int offset, int limit) {
        return this.classroomDao.queryAllByLimit(offset, limit);
    }

    /**
     * 查询全部数据
     *
     * @return 对象列表
     */
    @Override
    public List<Classroom> queryAll(Classroom classroom){
        return this.classroomDao.queryAll(classroom);
    }

    /**
     * 新增数据
     *
     * @param classroom 实例对象
     * @return 实例对象
     */
    @Override
    public Classroom insert(Classroom classroom) {
        this.classroomDao.insert(classroom);
        return classroom;
    }

    /**
     * 修改数据
     *
     * @param classroom 实例对象
     * @return 实例对象
     */
    @Override
    public Classroom update(Classroom classroom) {
        this.classroomDao.update(classroom);
        return this.queryById(classroom.getClassRoomId());
    }

    /**
     * 通过主键删除数据
     *
     * @param classRoomId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer classRoomId) {
        return this.classroomDao.deleteById(classRoomId) > 0;
    }
}
