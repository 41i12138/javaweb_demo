package com.llw.service;

import com.llw.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询全部部门数据
     * @return
     */
    List<Dept> getAllDept();


    /**
     * 删除部门
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    void add(Dept dept);
}
