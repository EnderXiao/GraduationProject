package com.ender.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Ender-PC
 * @date 2021/2/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private int uId;
    private String uUsername;
    private String uPassword;
}
