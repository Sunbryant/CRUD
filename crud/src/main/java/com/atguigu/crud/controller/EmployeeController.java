package com.atguigu.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Message;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	//以Josn形式返回查询到的页面信息。
	@RequestMapping("/emps")
	@ResponseBody
	public Message getEmpsOnJosn(@RequestParam(value="pn",defaultValue="1") Integer pn,Model model){
		//分页查询先要导入pagehelper,然后分页规则
				PageHelper.startPage(pn, 20);
				List<Employee> emp=employeeService.getAll();
				//将分页信息包装,连续显示5页。
				PageInfo page=new PageInfo(emp,20);
				//通过Model将分页信息带到前端
				model.addAttribute("pageInfo",page);
		return Message.messageSuccess().add("pageInfo", page);
	
	}
	
	//保存新增员工信息
		@RequestMapping(value="/emp",method=RequestMethod.POST)
		@ResponseBody
		public Message insertEmp(Employee employee){
			employeeService.insertOne(employee);
			return Message.messageSuccess();
			
		}
		//查询员工信息
		@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
		@ResponseBody
		public Message getEmp(@PathVariable("id") Integer id){
			Employee employee=employeeService.getemp(id);
			return Message.messageSuccess().add("emp", employee);
		}
		//修改后，更新保存到数据库。
		@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
		@ResponseBody
		public Message updateEmp(Employee employee){
			employeeService.update(employee);
			return Message.messageSuccess();
			
		}
		/*	//删除单个员工信息。
		@ResponseBody
		@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
		public Message deleteEmp(@PathVariable("id")Integer id){
			employeeService.deleteEmpById(id);
			return Message.messageSuccess();
		}*/
		
		//全选or全不选的删除方式
		@ResponseBody
		@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
		public Message deleteEmps(@PathVariable("ids") String ids){
			//多选或者全选删除
			if(ids.contains("-")){
			String[] str_id=ids.split("-");
			List<Integer> list_ids=new ArrayList<>();
			for (String id : str_id) {
				list_ids.add(Integer.parseInt(id));
			}
			employeeService.deleteEmpsByExample(list_ids);
			}else{ 
				//单个删除
				Integer id=Integer.parseInt(ids);
				employeeService.deleteEmpById(id);
			}
			return Message.messageSuccess();
		}
		/*@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1") Integer pn,Model model){
		//分页查询先要导入pagehelper,然后分页规则
		PageHelper.startPage(pn, 5);
		List<Employee> emp=employeeService.getAll();
		//将分页信息包装,连续显示5页。
		PageInfo page=new PageInfo(emp,5);
		//通过Model将分页信息带到前端
		model.addAttribute("pageInfo",page);
		return "list";
	}*/
}
