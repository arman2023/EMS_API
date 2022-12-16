package com.sanket.ems.service.department;

import com.sanket.ems.dto.DepartmentDTO;
import com.sanket.ems.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDTO toDTO(Department department);

    Department toEntity(DepartmentDTO departmentDTO);

    static Department toEntity(final DepartmentDTO departmentDTO, final Department department){
        department.setDepartmentName(departmentDTO.getDepartmentName());
        department.setDescription(departmentDTO.getDescription());
        return department;
    }
}
