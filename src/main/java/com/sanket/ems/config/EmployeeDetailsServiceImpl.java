package com.sanket.ems.config;

import com.sanket.ems.dao.EmployeeRepository;
import com.sanket.ems.model.Employee;
import com.sanket.ems.service.employee.Exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class EmployeeDetailsServiceImpl implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employeeRepository.findByEmail(username);
        if(!employee.isPresent())
            throw new EmployeeNotFoundException();
        EmployeeDetails employeeDetails = new EmployeeDetails(employee.get());
        return employeeDetails;
    }
}
