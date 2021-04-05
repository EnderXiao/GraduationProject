package com.ender.mapper;

import com.ender.entity.Dept;

import java.util.List;

/**
 * @author Ender-PC
 * @date 2021/2/6
 */
public interface DeptMapper {

    /**
     * 按id查找部门信息
     * @return dept
     */
    Dept findDeptById(Integer id);

    /**
     * 查找所有部门信息
     * @return deptList
     */
    List<Dept> findDepts();
}
