package com.llw.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data //包括@Getter,@Setter,@ToString,@EqualsAndHashCode
@NoArgsConstructor //无参构造
@AllArgsConstructor //全参构造
@TableName("users") // Mybatis Plus注解：表名注解，标识实体类对应的表
public class User {
    private Integer id;
    private String name;
    private String password;
    private Integer dept;
    private Integer salary;
    private LocalDate birthDate;
    private String image;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    //无
//    private Integer age;
//    private Address address;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public Integer getDept() {
//        return dept;
//    }
//
//    public void setDept(Integer dept) {
//        this.dept = dept;
//    }
//
//    public Integer getSalary() {
//        return salary;
//    }
//
//    public void setSalary(Integer salary) {
//        this.salary = salary;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }
//
//    public Address getAddress() {
//        return address;
//    }
//
//    public void setAddress(Address address) {
//        this.address = address;
//    }
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", password='" + password + '\'' +
//                ", dept=" + dept +
//                ", salary=" + salary +
//                ", age=" + age +
//                ", address=" + address +
//                '}';
//    }
}
