package com.example.pro.down.service;

import java.util.List;

import com.example.pro.down.entity.Down;
import com.example.pro.down.mapper.DownMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DownServiceImpl implements DownService {
    @Autowired
    private DownMapper downMapper;

    public PageInfo<Down> findAll(Down down) {
        String orderBy = "stick=1 asc,id desc,mofifyTime desc";
        PageHelper.startPage(1, 2, orderBy);
        List<Down> downs = downMapper.findAll(down);
        PageInfo<Down> pageInfo = new PageInfo<Down>(downs);
        return pageInfo;
    };

    public Down findById(Integer id) {
        return downMapper.findById(id);
    };

    public Down create(Down down) {
        downMapper.create(down);
        return down;
    };

    public Integer modify(Down down) {
        return downMapper.modify(down);
    };

    public Integer delete(Integer id) {
        return downMapper.delete(id);
    };
}