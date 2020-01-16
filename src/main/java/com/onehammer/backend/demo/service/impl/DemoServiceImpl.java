package com.onehammer.backend.demo.service.impl;

import com.onehammer.backend.demo.dao.DemoMapper;
import com.onehammer.backend.demo.entity.DemoEntity;
import com.onehammer.backend.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<DemoEntity> getUser() {
        return demoMapper.getUser();
    }
}
