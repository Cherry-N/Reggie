package com.longxingyu.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.longxingyu.reggie.common.R;
import com.longxingyu.reggie.pojo.Employee;
import com.longxingyu.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * {@code @Create:}  2023-06-05  20 : 26
 * {@code @Author:} Cherry
 * {@code @ToUser:} Be Happy EveryDay
 * ------------------------------------
 * {@code @note:}
 */

@SuppressWarnings({"all"})
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登陆
     *
     * @param httpServletRequest
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest httpServletRequest, @RequestBody Employee employee) {
        //处理逻辑如下

        // 1、将页面提交的密码password进行md5加密处理
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //2、根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Employee> employeeLambdaQueryWrapper = new LambdaQueryWrapper<>();
        employeeLambdaQueryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(employeeLambdaQueryWrapper);

        //3、如果没有查询到则返回登录失败结果
        if (emp == null) {
            return R.error("登陆失败");
        }

        //4、密码比对，如果不一致则返回登录失败结果
        if (!emp.getPassword().equals(password)) {
            return R.error("密码错误");
        }

        //5、查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (emp.getStatus() == 0) {
            return R.error("账号已禁用");
        }

        // 6、登录成功，将员工id存入Session并返回登录成功结果
        httpServletRequest.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }

    /**
     * 退出登录
     *
     * @param httpServletRequest
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest httpServletRequest) {
        httpServletRequest.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }

    /**
     * 新增员工
     *
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> save(HttpServletRequest httpServletRequest, @RequestBody Employee employee) {
        log.info("新增员工，员工信息为：{}", employee.toString());

        //设置初始密码 为123456 需要进行md5加密
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
//
//        //获取当前登陆用户的id
//        Long empId = (Long) httpServletRequest.getSession().getAttribute("employee");
//
//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);

        employeeService.save(employee);

        return R.success("创建用户成功");
    }

    /**
     * 员工分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        log.info("page = {},pageSize = {},name = {}", page, pageSize, name);

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        objectLambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        //添加排序条件
        objectLambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        employeeService.page(pageInfo, objectLambdaQueryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 根据id修改员工信息
     *
     * @param httpServletRequest
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest httpServletRequest, @RequestBody Employee employee) {
        log.info(employee.toString());

//        Long empId = (Long) httpServletRequest.getSession().getAttribute("employee");
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empId);

        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }

    /**
     * 根据id查询员工信息
     * * @param id
     *
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable String id) {
        log.info("根据id查询员工对象");
        Employee employee = employeeService.getById(id);
        if (employee != null) {
            return R.success(employee);
        }
        return R.error("没有查询到该用户信息");
    }
}

