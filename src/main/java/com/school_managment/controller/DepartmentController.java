package com.school_managment.controller;

import com.school_managment.entity.Department;
import com.school_managment.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public Department createDepartment(@RequestBody Department department){
        return departmentService.createDepart(department);
    }

    @GetMapping
    public Department getAllDepartment(){
       return departmentService.getAllDepart();
    }

    @GetMapping("/{id}")
    public Optional<Department> getDepartmentById(@PathVariable Long id){
        return departmentService.getDepartById(id);
    }


}
