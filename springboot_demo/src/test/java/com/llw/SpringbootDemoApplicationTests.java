package com.llw;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.llw.mapper.UserMapper;
import com.llw.mapper.UserMapperMP;
import com.llw.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@SpringBootTest //springboot整合单元测试
class SpringbootDemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testGetAllUsers() {
        List<User> allUsers = userMapper.getAllUsers();
        allUsers.stream().forEach(System.out::println);
    }

    @Test
    public void testDeleteById() {
        userMapper.deleteById(10);
    }

    @Test
    public void testInsert() {
        //构造对象
        User user = new User();
        user.setName("杰瑞");
        user.setPassword("000");
        user.setDept(1);
        user.setSalary(7000);
        user.setBirthDate(LocalDate.of(2001,7,1));
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        userMapper.insert(user);
        System.out.println(user.getId());
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(9);
        user.setPassword("111");
        user.setSalary(7000);
        user.setUpdateTime(LocalDateTime.now());

        userMapper.update(user);
    }

    @Test
    public void testGetById() {
        User user = userMapper.getById(2);
        System.out.println(user);
    }

    @Test
    public void testGetUsersList() {
        List<User> usersList = userMapper.getUsersList("张", 1, LocalDate.of(2000, 01, 01), LocalDate.of(2001, 01, 01));
        System.out.println(usersList);
    }


    /**
     * Mybatis-xml 测试
     */
    @Test
    public void testGetUsersList2() {
        List<User> usersList = userMapper.getUsersList2(null, 1, null, null);
        System.out.println(usersList);
    }

    @Test
    public void testUpdate2() {
        User user = new User();
        user.setId(9);
        user.setPassword("777");
//        user.setSalary(6000);
//        user.setBirthDate(LocalDate.of(2000, 7, 1));
//        user.setUpdateTime(LocalDateTime.now());

        userMapper.update(user);
    }

    @Test
    public void testDeleteByIds() {
        List<Integer> ids = Arrays.asList(16, 17, 18);
        userMapper.deleteByIds(ids);
    }


    /**
     * JDBC测试代码
     * 定义实体类User
     * 将User对象存入ArrayList集合中
     * @throws Exception
     */
    @Test
    public void jdbcTestResult() throws Exception {
        //1. 注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");

        //2. 获取连接
        String url = "jdbc:mysql://localhost:3306/db1?serverTimezone=GMT%2B8";
        String username = "root";
        String password = "llw2382257";
        Connection conn =  DriverManager.getConnection(url, username, password);

        //3. 定义sql
        String sql = "SELECT * FROM users";

        //4. 获取执行sql的对象
        Statement stmt = conn.createStatement();

        //5. 执行sql
        ResultSet rs = stmt.executeQuery(sql);

        List<User> list = new ArrayList<>();
        //6. 处理结果，遍历rs中的所有数据
        while (rs.next()) {
            User user = new User();

            int id = rs.getInt(1);
            String name = rs.getString(2);
            int salary = rs.getInt("salary");

            user.setId(id);
            user.setName(name);
            user.setSalary(salary);

            list.add(user);
        }

        System.out.println(list);

        //7. 释放资源
        rs.close();
        stmt.close();
        conn.close();

    }

    @Test
    public void testGenJWT() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("name", "Tom");
        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "llwSecret") //签名算法
                .setClaims(claims) //自定义内容（载荷）
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) //设置有效期为1h
                .compact();
        System.out.println(jwt);
    }

    @Test
    public void testParseJWT() {
        Claims llwSecret = Jwts.parser()
                .setSigningKey("llwSecret")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiVG9tIiwiaWQiOjEsImV4cCI6MTY4MTM5MTgyNH0.mPdyKhvVhXcTvnZ8Qs00IrFbEgbQGlkd1Q4bwDC8Woc")
                .getBody();
        System.out.println(llwSecret);
    }



    /**
     *
     * Mybatis plus 测试
     *
     */

    @Autowired
    private UserMapperMP userMapperMP;

    // 查询全部
    @Test
    public void testGetAll() {
        List<User> users = userMapperMP.selectList(null);
        users.forEach(System.out::println);
    }

    //分页查询
    @Test
    public void testPages() {
        IPage page = new Page(1, 2);
        userMapperMP.selectPage(page, null);
        System.out.println("当前页码:" + page.getCurrent());
        System.out.println("每页数量:" + page.getSize());
        System.out.println("总共页数:" + page.getPages());
        System.out.println("总共数据:" + page.getTotal());
        System.out.println("此页数据:" + page.getRecords());
    }

    //条件查询
    @Test
    public void testGet() {
        //方式一:按条件查询
//        QueryWrapper qw = new QueryWrapper<>();
//        qw.lt("salary", 5000);
//        List<User> list = userMapperMP.selectList(qw);
//        System.out.println(list);

        //方式二:lambda格式按条件查询
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        userLambdaQueryWrapper.lt(User::getSalary, 5000);
//        userLambdaQueryWrapper.gt(User::getSalary, 3000);
//        userLambdaQueryWrapper.lt(User::getSalary, 5000).gt(User::getSalary, 3000);
        userLambdaQueryWrapper.lt(User::getSalary, 3000).or().gt(User::getSalary, 5000);
        List<User> list = userMapperMP.selectList(userLambdaQueryWrapper);
        System.out.println(list);
    }

    // 查询投影
    @Test
    public void testGet2() {
        QueryWrapper<User> qw = new QueryWrapper<User>();
        qw.select("id", "name");
        List<User> users = userMapperMP.selectList(qw);
        System.out.println(users);
    }
}
