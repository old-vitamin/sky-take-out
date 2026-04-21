package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 员工分页查询
     * @param employeePageQueryDTO
     * @return
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 禁止或启用用户的状态
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);

    /**
     * 查询回显,根据员工id查询员工信息
     * @param id
     * @return
     */
    Employee getById(Long id);

    /**
     * 根据回显结果修改更新员工信息
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);
}
