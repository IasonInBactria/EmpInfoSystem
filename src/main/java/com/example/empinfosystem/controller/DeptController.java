package com.example.empinfosystem.controller;


import com.example.empinfosystem.anno.Log;
import com.example.empinfosystem.pojo.Dept;
import com.example.empinfosystem.pojo.Result;
import com.example.empinfosystem.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    @Autowired
    private DeptService deptService;
    @GetMapping()
    public Result list(){
        log.info("查询全部数据");

        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }

    @DeleteMapping("/{id}")
    @Log
    public Result delete(@PathVariable Integer id){
        log.info("根据id删除部门:{}", id);
        deptService.delete(id);
        return Result.success();
    }

    @PostMapping()
    @Log
    public Result add(@RequestBody Dept dept){
        log.info("新增部门");
        deptService.add(dept);
        return Result.success();
    }


}
