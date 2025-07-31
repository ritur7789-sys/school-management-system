package com.school_managment.service;

import com.school_managment.entity.Teacher;
import com.school_managment.kafka.KafkaProducerService;
import com.school_managment.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private KafkaProducerService kafkaProducer;

    // ✅ Constructor injection
    public TeacherService(TeacherRepository teacherRepository, PasswordEncoder passwordEncoder) {
        this.teacherRepository = teacherRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Cacheable(value = "teachers", key = "#id")
    public Teacher get(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));
    }


    public Teacher create(Teacher teacher) {
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));  // password encrypt
        Teacher saved = teacherRepository.save(teacher);

        kafkaProducer.sendEvent("New teacher created: " + saved.getEmail());  // 🔊 Kafka Event
        return saved;
    }


    public Teacher update(Long id, Teacher data) {
        Teacher existing = get(id);
        existing.setName(data.getName());
        existing.setEmail(data.getEmail());
        existing.setPassword(data.getPassword());
        return teacherRepository.save(existing);
    }

    public void delete(Long id) {
        teacherRepository.deleteById(id);
    }
}

