package com.ender.dao;

import com.ender.entity.Admin;
import com.ender.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Ender-PC
 */
public interface AdminMapper {
    /**
     * 查询所有用户信息
     * @return
     */
    @Select("select id,username,password from admin")
    List<Admin> selectAdmins();

    /**
     * 根据id查找用户
     * @param id
     * @return
     */
    @Select("select id,username,password from admin where id = #{id}")
    Admin selectAdminById(int id);

    /**
     * 保存用户信息a
     * @param admin
     * @return
     */
    @Insert("insert into admin (username , password) value (#{username},#{password})")
    int saveAdmin(Admin admin);

    /**
     * 更新用户信息
     * @param admin
     * @return
     */
    @Update("update admin set username = #{username} , password = #{password} where id = #{id}")
    int updateAdmin(Admin admin);

    /**
     * 根据id删除一个用户记录
     * @param id
     * @return
     */
    @Delete("delete from admin where id = #{id}")
    int deleteAdmin(int id);
}
