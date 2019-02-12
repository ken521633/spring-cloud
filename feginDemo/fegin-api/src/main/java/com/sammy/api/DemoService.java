package com.sammy.api;

import com.sammy.fallback.DemoFallBack;
import com.sammy.vo.UserDetailVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "FEGIN-SERVICE", fallback = DemoFallBack.class)
public interface DemoService {

    @PutMapping(path = "/say/{name}")
    String say(@PathVariable("name") String name);

    @PutMapping(path = "/detail")
    UserDetailVO detail();
}
