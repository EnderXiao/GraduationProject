package com.ender.mapper;

import com.ender.entity.Employee;

import java.util.List;

/**
 * @author Ender-PC
 * @date 2021/2/6
 */
public interface EmployeeMapper {

    /**
     * 获取所有员工信息
     * @return employeeList
     */
    List<Employee> findEmployees();

    /**
     * 按id获取指定员工信息
     * @param id
     * @return employee
     */
    Employee findEmployeesById(Integer id);

    /**
     * 按部门id获取员工信息
     * @param id
     * @return employeeList
     */
    List<Employee> findEmployeesByDid(Integer id);
}
