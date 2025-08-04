package com.school_managment.service;

import com.school_managment.entity.Department;
import com.school_managment.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {


    @Autowired
    private DepartmentRepository departmentRepository;
    public Department createDepart(Department department){
        return departmentRepository.save(department);
    }

    public Department getAllDepart(){
        return (Department) departmentRepository.findAll();
    }

    public Optional<Department> getDepartById(Long id){
        return departmentRepository.findById(id);
    }
}

