package com.school_managment.service;

import com.school_managment.entity.Student;
import com.school_managment.kafka.KafkaProducerService;
import com.school_managment.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private KafkaProducerService kafkaProducer;

    // ✅ Constructor injection
    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public Student create(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));  // password encrypt
        Student saved = studentRepository.save(student);

        kafkaProducer.sendEvent("New student created: " + saved.getEmail());  // 🔊 Kafka Event
        return saved;
    }

    @Cacheable(value = "students", key = "#id")
    public Student get(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    public Student update(Long id, Student student) {
        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setPassword(student.getPassword());

        return studentRepository.save(existing);
    }


}

