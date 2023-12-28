package com.llw.service;

import com.llw.pojo.PageBean;
import com.llw.pojo.User;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface UserService {
    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
//    PageBean page(Integer page, Integer pageSize);

    /**
     * 分页条件查询
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Integer dept, LocalDate begin, LocalDate end);

    /**
     * 根据ID查询员工
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 批量删除
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 新增员工
     * @param user
     */
    void add(User user);

    /**
     * 更新员工
     * @param user
     */
    void update(User user);

    /**
     * 员工登录
     * @param user
     * @return
     */
    User login(User user);
}
