package com.sanket.ems.service.employee;

import com.sanket.ems.dto.EmployeeDTO;
import com.sanket.ems.model.Employee;
import com.sanket.ems.service.department.DepartmentMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    @Mapping(source = "departmentFk", target = "department")
    EmployeeDTO toDTO(Employee employee);

    @Mapping(target = "departmentFk", ignore = true)
    Employee toEntity(EmployeeDTO employeeDTO);

    static Employee toEntity(final EmployeeDTO employeeDTO, final Employee employee){
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPassword(employeeDTO.getPassword());
        employee.setAddress(employeeDTO.getAddress());
        return employee;
    }
}
