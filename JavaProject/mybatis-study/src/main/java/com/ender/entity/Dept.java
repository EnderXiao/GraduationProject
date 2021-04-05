package com.ender.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ender-PC
 * @date 2021/2/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept implements Serializable {
    private int id;
    private String name;
    /**
     * 该部门下的所有员工
     */
    List<Employee> employees;
}
