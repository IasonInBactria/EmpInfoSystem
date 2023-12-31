package com.example.empinfosystem.controller;


import com.example.empinfosystem.anno.Log;
import com.example.empinfosystem.pojo.Emp;
import com.example.empinfosystem.pojo.PageBean;
import com.example.empinfosystem.pojo.Result;
import com.example.empinfosystem.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpService empService;
    @GetMapping()
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询,参数 {}, {}, {}, {}, {}, {}", page, pageSize, name, gender,begin, end);
        PageBean pageBean = empService.page(page, pageSize, name, gender,begin, end);
        return Result.success(pageBean);
    }

    @DeleteMapping("/{ids}")
    @Log
    public Result delete(@PathVariable List<Integer> ids){
        log.info("批量删除 ids:{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    @PostMapping
    @Log
    public Result save(@RequestBody Emp emp){
        log.info("新增员工:{}", emp);
        empService.save(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("根据id{}查询信息", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @PutMapping
    @Log
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息：{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
