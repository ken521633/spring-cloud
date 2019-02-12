package com.sammy.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDetailVO implements Serializable {

    private String name;

    private Integer age;
}
