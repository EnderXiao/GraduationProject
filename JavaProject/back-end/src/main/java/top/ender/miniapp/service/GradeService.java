package top.ender.miniapp.service;

import top.ender.miniapp.entity.Grade;
import top.ender.miniapp.entity.User;

import java.util.List;

/**
 * (Grade)表服务接口
 *
 * @author makejava
 * @since 2021-02-21 09:36:35
 */
public interface GradeService {

    /**
     * 通过ID查询单条数据
     *
     * @param gradeId 主键
     * @return 实例对象
     */
    Grade queryById(Integer gradeId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<Grade> queryAllByLimit(int offset, int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param grade 实例对象
     * @return 对象列表
     */
    List<Grade> queryAll(Grade grade);

    /**
     * 新增数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    Grade insert(Grade grade);

    /**
     * 修改数据
     *
     * @param grade 实例对象
     * @return 实例对象
     */
    Grade update(Grade grade);

    /**
     * 通过主键删除数据
     *
     * @param gradeId 主键
     * @return 是否成功
     */
    boolean deleteById(Integer gradeId);

    /**
     * 通过用户Id以及时间进行查找
     *
     * @param uId 用户Id
     * @param schoolYear 成绩时间
     * @return 对象列表
     */
    List<Grade> queryGradeByUidAndTime(String uId, String schoolYear);
}
