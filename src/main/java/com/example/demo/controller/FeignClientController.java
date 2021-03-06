package com.example.demo.controller;

import com.example.demo.model.entity.rabc.Users;
import com.example.demo.service.microservice.eurekaclient.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feignclient")
public class FeignClientController {
    //@Resource的作用相当于@Autowired。
    //@Resource:如果既不制定name也不制定type属性，这时将通过反射机制使用byName自动注入策略
    //@Autowired:按照byType自动注入。


    /**
     * 其他微服务调用：
     * Service 层封装其他微服务的调用。通过Feign指定注册中心的微服务的名称进行调用。
     * 通过微服务调用熔断：重写整个服务接口，进行熔断处理
     */
    //  @Resource  //java 的 IOC
    @Autowired    //spring mvc 的IOC
    private UserService userService;

    @GetMapping("")
    public String test(String name) {
        return userService.home(name);
    }

    //http://localhost:8081/feignclient/testParam?name=fanckyTest1
    @RequestMapping("testParam")
    public String testParam(@RequestParam String name) {
        String re = userService.home(name);
        return re;
    }
}
