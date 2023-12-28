package com.llw.mapper;

import com.llw.pojo.User;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper //在运行时，会自动生成该接口的实现对象（代理对象），并且将该对象交给IOC容器管理
public interface UserMapper {

    /**
     * 基于注解的Mybatis
     */

    // 添加
    @Options(useGeneratedKeys = true, keyProperty = "id") //第一句用于插入数据后直接返回该数据的主键值，第二句为封装到user对象的id属性中
    @Insert("INSERT INTO users(name, password, dept, salary, birth_date, create_time, update_time)"+
            " values (#{name},#{password},#{dept},#{salary},#{birthDate},#{createTime},#{updateTime})")
    public void insert(User user);

    // 删除
    @Delete("DELETE FROM users WHERE id = #{id}")
    public void deleteById(Integer id);

    // 修改
//    @Update("UPDATE users SET password = #{password}, salary = #{salary}, update_time = #{updateTime} WHERE id = #{id}")
//    public void update(User user);

    // 查询
    @Select("SELECT * FROM users")
    public List<User> getAllUsers();

    //方法1：给字段起别名，让别名与实体类属性一致
//    @Select("SELECT id, name, password, dept, salary, " +
//            "birth_date birthDate, create_time createTime, update_time updateTime FROM users WHERE id = #{id}")
//    public User getById(Integer id);

    //方法2:通过@Results注解，@Result手动映射封装
//    @Results({
//            @Result(column = "birth_date", property = "birthDate"),
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })
//    @Select("SELECT * FROM users WHERE id = #{id}")
//    public User getById(Integer id);

    //方法3:开启mybatis驼峰命名自动映射开关（推荐）
    @Select("SELECT * FROM users WHERE id = #{id}")
    public User getById(Integer id);

    //条件查询员工信息
    @Select("select * from users where name like concat('%', #{name}, '%') and dept = #{dept} and" +
            " birth_date between #{begin} and #{end} order by update_time desc ")
    public List<User> getUsersList(String name, Integer dept, LocalDate begin, LocalDate end);


    /**
     * 通过mybatis-XML配置文件实现
     */
    //动态条件查询
    public List<User> getUsersList2(String name, Integer dept, LocalDate begin, LocalDate end);

    //动态更新员工
    public void update(User user);

    //批量删除员工
    public void deleteByIds(List<Integer> ids);


//    /**
//     * 查询总记录数
//     * @return
//     */
//    @Select("select count(*) from users")
//    public Long count();

//    /**
//     * 分页查询，获取列表数据
//     * @param start
//     * @param pageSize
//     * @return
//     */
//    @Select("select * from users limit #{start}, #{pageSize}")
//    public List<User> page(Integer start, Integer pageSize);

//    /**
//     * 员工信息查询
//     * @return
//     */
//    @Select("select * from users")
//    public List<User> page();

    /**
     * 员工信息条件查询
     * @return
     */
    //@Select("select * from users")
    List<User> page(String name, Integer dept, LocalDate begin, LocalDate end);

    /**
     * 根据Id和密码查询用户
     * @param user
     * @return
     */
    @Select("select * from users where id = #{id} and password = #{password}") // #{}中的参数为User对象user的属性
    User getByIdAndPassword(User user);

    /**
     * 根据部门id删除该部门下的员工数据
     * @param dept
     */
    @Delete("delete from users where dept = #{dept}")
    void deleteByDeptId(Integer dept);


}
