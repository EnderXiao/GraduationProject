package com.ender.dao;

import com.ender.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ender-PC
 */
public interface UserMapper {
    /**
     * 查询所有用户信息
     * @return
     */
    List<User> selectUsers();

    /**
     * 根据id查找多用户
     * @param ids
     * @return
     */
    List<User> selectUsersByIds(@Param("ids") int[] ids);

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    User selectUserById(int id);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    int saveUser(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    int updateUser(User user);

    /**
     * 根据id删除一个用户记录
     * @param id
     * @return
     */
    int deleteUser(int id);

    /**
     * 根据具体参数查找
     * @param user
     * @return
     */
    List<User> findUsersByParam(User user);

    /**
     *
     * @param user
     * @return
     */
    List<User> fuzzyCheckUser(User user);

}
