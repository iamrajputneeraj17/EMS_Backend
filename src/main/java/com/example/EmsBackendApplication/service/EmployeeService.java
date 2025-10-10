package com.example.EmsBackendApplication.service;



import com.example.EmsBackendApplication.DTO.EmployeeDTO;
import com.example.EmsBackendApplication.entity.Employee;
import com.example.EmsBackendApplication.exception.ResourceNotFoundException;
import com.example.EmsBackendApplication.mapper.EmployeeMapper;
import com.example.EmsBackendApplication.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO)
    {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
    }


    public EmployeeDTO empById(Long empId)
    {
        Employee foundEmp = employeeRepository.findById(empId).
                orElseThrow(()-> new ResourceNotFoundException("No User found! "+ empId));

        return EmployeeMapper.mapToEmployeeDTO(foundEmp);
    }

    public List<EmployeeDTO> getAllEmp()
    {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee)->
                EmployeeMapper.mapToEmployeeDTO(employee)).collect(Collectors.toList());
    }

    public EmployeeDTO updateEmployee(Long empId, EmployeeDTO employeeDTO)
    {
        Employee foundEmp = employeeRepository.findById(empId).
                orElseThrow(()-> new ResourceNotFoundException("No User found! "+ empId));
        foundEmp.setFirstName(employeeDTO.getFirstName());
        foundEmp.setLastName(employeeDTO.getLastName());
        foundEmp.setEmail(employeeDTO.getEmail());
        Employee savedEmployee = employeeRepository.save(foundEmp);

        return EmployeeMapper.mapToEmployeeDTO(savedEmployee);
    }

    public void deleteEmployee(Long empId)
    {
        Employee foundEmp = employeeRepository.findById(empId).
                orElseThrow(()-> new ResourceNotFoundException("No User found! "+ empId));
        employeeRepository.deleteById(empId);
    }


}
