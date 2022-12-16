package com.sanket.ems.service.role;

import com.sanket.ems.dao.RoleRepository;
import com.sanket.ems.dto.RoleDTO;
import com.sanket.ems.model.Role;
import com.sanket.ems.service.role.Exception.DuplicateRoleException;
import com.sanket.ems.service.role.Exception.RoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public RoleDTO getRole(String roleName){
        Role role = getRoleById(roleName);
        return RoleMapper.INSTANCE.toDTO(role);
    }

    public List<RoleDTO> getRoles(){
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> roleDTOS = roles.stream().map(RoleMapper.INSTANCE::toDTO).collect(Collectors.toList());
        return roleDTOS;
    }

    @Transactional
    public RoleDTO saveRole(final RoleDTO roleDTO){
        validateRole(roleDTO);
        Role role = RoleMapper.INSTANCE.toEntity(roleDTO);
        Role newRole = roleRepository.save(role);
        return RoleMapper.INSTANCE.toDTO(newRole);
    }

    @Transactional
    public RoleDTO updateRole(final RoleDTO roleDTO){
        Role role = getRoleById(roleDTO.getRoleName());
        Role updatedRole = RoleMapper.toEntity(roleDTO, role);
        return RoleMapper.INSTANCE.toDTO(updatedRole);
    }

    @Transactional
    public void deleteRole(final RoleDTO roleDTO){
        Role role = getRoleById(roleDTO.getRoleName());
        roleRepository.delete(role);
    }

    public void validateRole(RoleDTO roleDTO){
        boolean isRoleExist = roleRepository.findById(roleDTO.getRoleName()).isPresent();
        if(isRoleExist)
            throw new DuplicateRoleException();
    }

    public Role getRoleById(String roleName){
        return roleRepository.findById(roleName).orElseThrow(() -> new RoleNotFoundException());
    }
}
