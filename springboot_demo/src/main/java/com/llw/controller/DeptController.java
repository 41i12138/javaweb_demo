package com.llw.controller;

import com.llw.anno.MyLog;
import com.llw.pojo.Dept;
import com.llw.pojo.Result;
import com.llw.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {

    // 获取日志记录对象（可直接使用lombok中@Slf4j注解，则不用手动定义日志）
//    private static Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    /**
     * 查询部门信息
     * @return
     */
//    @RequestMapping(value = "/depts", method = RequestMethod.GET) // 指定请求方式为GET
    @GetMapping // 指定请求方式为GET
    public Result getAllDept() {
        log.info("查询全部部门数据");
        // 调用service查询部门信息
        List<Dept> deptList = deptService.getAllDept();

        return Result.success(deptList);
    }

    /**
     * 删除部门
     * @return
     */
    @MyLog
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        log.info("根据id删除部门：{}",id);
        //调用service删除部门
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * 新增部门
     * @return
     */
    @MyLog
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门：{}",dept);
        //调用service新增部门
        deptService.add(dept);
        return Result.success();
    }

}
