package com.school_managment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String password;
    private String role = "ROLE_STUDENT";
}

