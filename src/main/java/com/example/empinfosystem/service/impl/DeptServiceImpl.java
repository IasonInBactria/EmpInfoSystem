package com.example.empinfosystem.service.impl;

import com.example.empinfosystem.mapper.DeptMapper;
import com.example.empinfosystem.mapper.EmpMapper;
import com.example.empinfosystem.pojo.Dept;
import com.example.empinfosystem.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public List<Dept> list(){
        return deptMapper.list();
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        //删除部门数据
        deptMapper.deleteById(id);
        //删除所有该部门员工
        empMapper.deleteByDeptId(id);
    }

    /**
     * @param dept
     */
    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

}
