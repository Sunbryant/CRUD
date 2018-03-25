package com.atguigu.crud.test;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;

/*使用spring 单元测试模块@ContextConfiguration*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext-dao.xml"})
public class TestMapper {
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;
	@Test
	public void testCURD(){
		System.out.println(departmentMapper);
		
		//1.插入部门
//		departmentMapper.insertSelective(new Department(2,"开发部"));
//		departmentMapper.insertSelective(new Department(1,"测试部"));
		//2.插入员工数据
		/*Employee(Integer empId, String empName, String gender, String email, Integer dId)*/
//		employeeMapper.insertSelective(new Employee(null,"张三","m","345@qq.com",1));
//		employeeMapper.insertSelective(new Employee(null,"可比","m","789@qq.com",2));
//		
		//3.批量操作
		EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<=1000;i++){
			String uid=UUID.randomUUID().toString().substring(0,5);
			mapper.insertSelective(new Employee(null, uid, "m",uid+"@uestc",1));
		}
	}
	
	

}
