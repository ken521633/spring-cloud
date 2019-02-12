package com.sammy.fallback;

import com.sammy.api.DemoService;
import com.sammy.vo.UserDetailVO;
import org.springframework.stereotype.Component;


@Component
public class DemoFallBack implements DemoService {
    @Override
    public String say(String name) {
        return "the name " + name + " do hys";
    }

    @Override
    public UserDetailVO detail() {
        return null;
    }
}
