package com.llw.mapper;

import com.llw.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 部门管理
 */
@Mapper
public interface DeptMapper {
    /**
     * 查询全部部门
     * @return
     */
    @Select("select * from depts")
    List<Dept> getAllDept();

    /**
     * 根据id删除部门
     * @param id
     */
    @Delete("delete from depts where id = #{id}")
    void deleteById(Integer id);

    /**
     * 新增部门
     * @param dept
     */
    @Insert("insert into depts(name, create_time) values (#{name}, #{createTime})")
    void insert(Dept dept);
}
