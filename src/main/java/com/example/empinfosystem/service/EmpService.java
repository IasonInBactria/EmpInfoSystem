package com.example.empinfosystem.service;

import com.example.empinfosystem.pojo.Emp;
import com.example.empinfosystem.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    PageBean page(Integer page, Integer pageSize, String name, Short gender,
                  LocalDate begin, LocalDate end);
    void delete(List<Integer> ids);
    void save(Emp emp);

    /**
     * @param id
     * @return
     */
    Emp getById(Integer id);

    void update(Emp emp);

    Emp login(Emp emp);
}
