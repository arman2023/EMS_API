package com.sanket.ems.service.employee;

import com.sanket.ems.dao.EmployeeRepository;
import com.sanket.ems.dto.EmployeeDTO;
import com.sanket.ems.dto.RoleDTO;
import com.sanket.ems.model.Department;
import com.sanket.ems.model.Employee;
import com.sanket.ems.model.Role;
import com.sanket.ems.service.department.DepartmentService;
import com.sanket.ems.service.employee.Exception.DuplicateEmployeeException;
import com.sanket.ems.service.employee.Exception.EmployeeNotFoundException;
import com.sanket.ems.service.role.RoleMapper;
import com.sanket.ems.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    RoleService roleService;

    @Transactional
    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO){
        validateEmployee(employeeDTO);
        Employee employee = EmployeeMapper.INSTANCE.toEntity(employeeDTO);
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        Department department = departmentService.getDepartmentById(employeeDTO.getDepartment().getDepartmentName());
        employee.setDepartmentFk(department);
        roleService.assignRole(employee, new RoleDTO("USER",""));
        Employee newEmployee = employeeRepository.save(employee);
        return EmployeeMapper.INSTANCE.toDTO(newEmployee);
    }

    @Transactional
    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO){
        Employee employee = getEmployeeById(employeeDTO.getEmployeeId());
        Employee updatedEmployee = EmployeeMapper.toEntity(employeeDTO, employee);
        passwordEncoder.encode(employee.getPassword());
        Department department = departmentService.getDepartmentById(employeeDTO.getDepartment().getDepartmentName());
        updatedEmployee.setDepartmentFk(department);
        return EmployeeMapper.INSTANCE.toDTO(updatedEmployee);
    }

    @Transactional
    public void deleteEmployee(EmployeeDTO employeeDTO){
        Employee employee = getEmployeeById(employeeDTO.getEmployeeId());
        employeeRepository.delete(employee);
    }

    @Transactional
    public List<EmployeeDTO> getEmployeeList(){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS = employees.stream().map(EmployeeMapper.INSTANCE::toDTO).collect(Collectors.toList());
        return employeeDTOS;
    }

    public void validateEmployee(EmployeeDTO employeeDTO){
        final boolean isEmployeeExist = employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent();
        if(isEmployeeExist)
            throw new DuplicateEmployeeException();
    }

    public Employee getEmployeeById(Integer employeeId){
        return employeeRepository.findById(employeeId).orElseThrow(EmployeeNotFoundException::new);
    }

    public void assignRole(final String roleName, final EmployeeDTO employeeDTO){
        Role roleToAssign = roleService.getRoleById(roleName);
        RoleDTO roleDTO = RoleMapper.INSTANCE.toDTO(roleToAssign);
        Employee employee = getEmployeeById(employeeDTO.getEmployeeId());
        roleService.assignRole(employee, roleDTO);
    }

    public void unAssignRole(final String roleName, final EmployeeDTO employeeDTO){
        Role roleToAssign = roleService.getRoleById(roleName);
        RoleDTO roleDTO = RoleMapper.INSTANCE.toDTO(roleToAssign);
        Employee employee = getEmployeeById(employeeDTO.getEmployeeId());
        roleService.unAssignRole(employee, roleDTO);
    }
}
