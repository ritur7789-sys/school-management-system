package com.school_managment.security;

import com.school_managment.entity.Student;
import com.school_managment.entity.Teacher;
import com.school_managment.repository.StudentRepository;
import com.school_managment.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired private TeacherRepository teacherRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // Check student by email
        Optional<Student> student = studentRepository.findByEmail(email);
        if (student.isPresent()) {
            Student s = student.get();
            return User.builder()
                    .username(s.getEmail())
                    .password(s.getPassword())
                    .roles("STUDENT")
                    .build();
        }

        // Check teacher by email
        Optional<Teacher> teacher = teacherRepository.findByEmail(email);
        if (teacher.isPresent()) {
            Teacher t = teacher.get();
            return User.builder()
                    .username(t.getEmail())
                    .password(t.getPassword())
                    .roles("TEACHER")
                    .build();
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }
}

