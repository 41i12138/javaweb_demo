package com.llw.service.impl;

import com.llw.anno.MyLog;
import com.llw.mapper.DeptMapper;
import com.llw.mapper.UserMapper;
import com.llw.pojo.Dept;
import com.llw.service.DeptService;
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
    private UserMapper userMapper;

    @Override
    public List<Dept> getAllDept() {
        return deptMapper.getAllDept();
    }

    @Transactional(rollbackFor = Exception.class) //spring事务管理
    @Override
    public void deleteById(Integer id) {
        deptMapper.deleteById(id); //根据id删除部门数据

        userMapper.deleteByDeptId(id); //根据部门id删除该部门下的员工
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }
}
