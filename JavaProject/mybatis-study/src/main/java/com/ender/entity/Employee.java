package com.ender.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Ender-PC
 * @date 2021/2/6
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    private int id;
    private String name;
    /**
     * 外键Dept，维护关系
     */
    private Dept dept;
}
