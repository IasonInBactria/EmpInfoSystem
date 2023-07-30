package com.example.empinfosystem.controller;


import com.example.empinfosystem.pojo.Emp;
import com.example.empinfosystem.pojo.Result;
import com.example.empinfosystem.service.EmpService;
import com.example.empinfosystem.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YangY
 */
@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp){
        log.info("员工登录:{}", emp);
        Emp ret_emp = empService.login(emp);
        if (ret_emp != null)
        {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", emp.getId());
            claims.put("username", emp.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return Result.success(jwt);
        }

        return Result.error("用户名或密码错误");
    }
}
