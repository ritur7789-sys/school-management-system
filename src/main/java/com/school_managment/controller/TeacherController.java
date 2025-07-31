package com.school_managment.controller;

import com.school_managment.entity.Teacher;
import com.school_managment.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<Teacher> getAll() {
        return teacherService.getAllTeachers();
    }

    @PostMapping
    public Teacher create(@RequestBody Teacher teacher) {
        return teacherService.create(teacher);
    }

    @GetMapping("/{id}")
    public Teacher get(@PathVariable Long id) {
        return teacherService.get(id);
    }

    @PutMapping("/{id}")
    public Teacher update(@PathVariable Long id, @RequestBody Teacher teacher) {
        return teacherService.update(id, teacher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teacherService.delete(id);
    }
}

