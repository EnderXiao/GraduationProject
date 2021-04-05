package top.ender.miniapp.service;

import top.ender.miniapp.entity.Classroom;
import top.ender.miniapp.entity.Course;

import java.util.List;

/**
 * (Classroom)表服务接口
 *
 * @author makejava
 * @since 2021-02-12 11:02:14
 */
public interface ClassroomService {

    /**
     * 通过ID查询单条数据
     *
     * @param classRoomId 主键
     * @return 实例对象
     */
    Classroom queryById(Integer classRoomId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Classroom> queryAllByLimit(int offset, int limit);

    /**
     * 查询全部数据
     *
     * @return 对象列表
     */
    List<Classroom> queryAll(Classroom classroom);

    /**
     * 新增数据
     *
     * @param classroom 实例对象
     * @return 实例对象
     */
    Classroom insert(Classroom classroom);

    /**
     * 修改数据
     *
     * @param classroom 实例对象
     * @return 实例对象
     */
    Classroom update(Classroom classroom);

    /**
     * 通过主键删除数据
     *
     * @param classRoomId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer classRoomId);

}
