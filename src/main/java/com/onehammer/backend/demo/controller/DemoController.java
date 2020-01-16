package com.onehammer.backend.demo.controller;

import com.onehammer.backend.demo.entity.DemoEntity;
import com.onehammer.backend.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: demo
 */
@RestController
@RequestMapping("/test")
public class DemoController {

    @Autowired
    private DemoService orderService;

    @GetMapping("/getUser")
    public List<DemoEntity> getUser(){
        List<DemoEntity> result = orderService.getUser();
        return result;
    }

}
