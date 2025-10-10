package com.example.EmsBackendApplication.JuitTesting;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "vivo_junit_test")
public class VivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String modelName;

    private String camera;

    private String battery;
}
