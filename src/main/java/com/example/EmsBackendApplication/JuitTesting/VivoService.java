package com.example.EmsBackendApplication.JuitTesting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VivoService {

    @Autowired
    private VivoRepository vivoRepository;

    public VivoEntity createMobile(VivoEntity entity)
    {
        return vivoRepository.save(entity);
    }

    public Optional<VivoEntity> findById(Long id)
    {
        return vivoRepository.findById(id);
    }

    public List<VivoEntity> findAll()
    {
        return vivoRepository.findAll();
    }
}
