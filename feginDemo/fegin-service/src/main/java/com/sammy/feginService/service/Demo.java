package com.sammy.feginService.service;

import com.sammy.vo.UserDetailVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Demo {

    @RequestMapping("/say/{name}")
    public String say(@PathVariable String name) {
        return "hello " + name + " your visit port is 6191";
    }

    @RequestMapping("/detail")
    public UserDetailVO detail() {
        UserDetailVO vo = new UserDetailVO();
        vo.setAge(22);
        vo.setName("sammy");
        return vo;
    }
}
