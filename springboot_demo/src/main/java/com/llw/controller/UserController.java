package com.llw.controller;

import com.llw.anno.MyLog;
import com.llw.pojo.PageBean;
import com.llw.pojo.Result;
import com.llw.pojo.User;
import com.llw.service.UserService;
import com.llw.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page,
//                       @RequestParam(defaultValue = "10") Integer pageSize) {
//        log.info("分页查询，参数: {}, {}",page,pageSize);
//        //调用service分页查询
//        PageBean pageBean = userService.page(page, pageSize);
//        return Result.success(pageBean);
//    }

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer pageSize,
                        String name, Integer dept,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                        @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        log.info("分页查询，参数: {}, {}, {}, {}, {}, {}",page,pageSize,name,dept,begin,end);
        //调用service分页查询
        PageBean pageBean = userService.page(page, pageSize, name, dept, begin, end);
        return Result.success(pageBean);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询员工信息,id: {}", id);
        User user = userService.getById(id);
        return Result.success(user);
    }

    @DeleteMapping("/{ids}")
    @MyLog
    public Result deleteByIds(@PathVariable List<Integer> ids) {
        log.info("批量删除: id {}", ids);
        userService.deleteByIds(ids);
        return Result.success();
    }

    @PostMapping
    @MyLog
    public Result add(@RequestBody User user) {
        log.info("新增员工，user:{}", user);
        userService.add(user);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        log.info("员工登录:{}", user);
        User u = userService.login(user);

        //登录成功，生成令牌，下发令牌
        if (u != null) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("name", u.getName());

            String jwt = JwtUtils.generateJWT(claims);// jwt包含了当前登录的员工信息
            return Result.success(jwt);
        }

        // 登录失败，返回错误信息
        return Result.error("用户名或密码错误");
    }

    @PutMapping
    @MyLog
    public Result update(@RequestBody User user) {
        log.info("更新员工信息: {}", user);
        userService.update(user);
        return Result.success();
    }

}
