package top.ender.miniapp.service;

import top.ender.miniapp.entity.User;

import java.util.Date;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author makejava
 * @since 2021-02-12 17:11:28
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param uId 主键
     * @return 实例对象
     */
    User queryById(String uId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param uId 主键
     * @return 是否成功
     */
    boolean deleteById(String uId);

    /**
     * 登录事物
     *
     * @param uId Integer
     * @param password String
     * @param lastEditTime Date
     * @return 实列对象
     */
    User logIn(String uId, String password, Date lastEditTime);

}
