package com.atguigu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.bean.EmployeeExample.Criteria;
import com.atguigu.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {
	@Autowired
	EmployeeMapper employeeMapper;
	//获取全部员工信息
	public List<Employee> getAll() {
		return employeeMapper.selectByExampleWithDept(null);
	}
	//添加一个员工
	public int insertOne(Employee employee){
		return employeeMapper.insertSelective(employee);
	}
	//查询员工
	public Employee getemp(Integer id) {
		return employeeMapper.selectByPrimaryKey(id);
	}
	//编辑一个员工信息
	public void update(Employee employee){
		employeeMapper.updateByPrimaryKeySelective(employee);
	}
	//删除单个员工资料
	public void deleteEmpById(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
		
	}
	//删除全部或者全部不选。
	public void deleteEmpsByExample(List<Integer> ids) {
		EmployeeExample ex=new EmployeeExample();
		Criteria criteria=ex.createCriteria();
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(ex);
		
	}
		
	
}
