package com.sammy.feginCustomer.controller;

import com.sammy.api.DemoService;
import com.sammy.vo.UserDetailVO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class SayController {
    @Resource
    private DemoService demoService;

    @RequestMapping("/api/{name}")
    public String say(@PathVariable String name) {
        return demoService.say(name);
    }

    @RequestMapping("/user/detail")
    public String detail() {
        UserDetailVO vo = demoService.detail();
        return vo.toString();
    }
}
