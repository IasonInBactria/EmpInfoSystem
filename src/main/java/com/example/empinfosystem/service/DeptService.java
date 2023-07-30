package com.example.empinfosystem.service;

import com.example.empinfosystem.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * @return 全部数据
     */
    List<Dept> list();
    void delete(Integer id);
    void add(Dept dept);
}
