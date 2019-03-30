package com.example.pro.down.service;

import com.example.pro.down.entity.Down;
import com.github.pagehelper.PageInfo;

public interface DownService {
    PageInfo<Down> findAll(Down down);

    Down findById(Integer id);

    Down create(Down down);

    Integer modify(Down down);

    Integer delete(Integer id);
}