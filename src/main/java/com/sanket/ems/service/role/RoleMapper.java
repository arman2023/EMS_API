package com.sanket.ems.service.role;

import com.sanket.ems.dto.RoleDTO;
import com.sanket.ems.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

//    @Mapping(target = "authorities", ignore = true)
    Role toEntity(RoleDTO roleDTO);

//    @Mapping(source = "authorities", target = "authorityDTO")
    RoleDTO toDTO(Role role);

    static Role toEntity(RoleDTO roleDTO, Role role){
        role.setRoleName(roleDTO.getRoleName());
        role.setDescription(roleDTO.getDescription());
        return role;
    }
}
