package tws.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tws.domain.Employee;

@Mapper
public interface EmployeeMapper {

	@Insert("insert into employee values (#{employee.id},#{employee.name},#{employee.age})")
	void insert(@Param("employee")Employee employee);
	
	@Select("select * from employee")
	List<Employee> queryList();
	
	@Select("select * from employee where id = #{id}")
	Employee queryEmployeeById(@Param("id") int id);
	
	@Update("update employee set name=#{employee.name},age=#{employee.age} where id=#{employee.id}")
	void update(@Param("employee")Employee employee);
	
	@Select("delete from employee where id = #{id}")
	void deleteEmployeeById(@Param("id") int id);
}
