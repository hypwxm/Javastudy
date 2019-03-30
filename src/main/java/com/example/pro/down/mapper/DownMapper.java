package com.example.pro.down.mapper;

import java.util.List;

import com.example.pro.down.entity.Down;

import org.springframework.stereotype.Repository;

@Repository
public interface DownMapper {
    List<Down> findAll(Down down);

    Down findById(Integer id);

    Integer create(Down down);

    Integer modify(Down down);

    Integer delete(Integer id);
}