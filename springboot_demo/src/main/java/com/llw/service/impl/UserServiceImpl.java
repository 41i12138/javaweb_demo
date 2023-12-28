package com.llw.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.llw.mapper.UserMapper;
import com.llw.pojo.PageBean;
import com.llw.pojo.User;
import com.llw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        //1.获取总记录数
//        Long count = userMapper.count();
//
//        //2.获取分页查询列表
//        Integer start = (page - 1) * pageSize;
//        List<User> userList = userMapper.page(start, pageSize);
//
//        //3.封装pageBean对象
//        PageBean pageBean = new PageBean(count, userList);
//
//        return pageBean;
//    }


//    @Override
//    public PageBean page(Integer page, Integer pageSize) {
//        //1.设置分页参数
//        PageHelper.startPage(page, pageSize);
//
//        //2.执行查询(使用pageHelper插件的Page类强转list)
//        List<User> userList = userMapper.page();
//        Page<User> p = (Page<User>) userList;
//
//        //3.封装pageBean对象
//        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
//
//        return pageBean;
//    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name, Integer dept, LocalDate begin, LocalDate end) {
        //1.设置分页参数
        PageHelper.startPage(page, pageSize);

        //2.执行查询(使用pageHelper插件的Page类强转list)
        List<User> userList = userMapper.page(name, dept, begin, end);
        Page<User> p = (Page<User>) userList;

        //3.封装pageBean对象
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());

        return pageBean;
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        userMapper.deleteByIds(ids);
    }

    @Override
    public void add(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());

        userMapper.update(user);
    }

    @Override
    public User login(User user) {
        return userMapper.getByIdAndPassword(user);
    }
}
