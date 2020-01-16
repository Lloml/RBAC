package com.onehammer.backend.demo.dao;

import com.onehammer.backend.demo.entity.DemoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 */
@Component
public interface DemoMapper {

    List<DemoEntity> getUser();

}
