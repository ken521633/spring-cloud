package com.sammy.feignservice2.service;

import com.sammy.vo.UserDetailVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Demo {

    @RequestMapping("/say/{name}")
    public String say(@PathVariable String name) {
        return "hello " + name + " your visit port is 6192";
    }

    @RequestMapping("/detail")
    public UserDetailVO detail() {
        UserDetailVO vo = new UserDetailVO();
        vo.setAge(35);
        vo.setName("sammy1");
        return vo;
    }
}
