package com.example.EmsBackendApplication.JuitTesting;

import com.example.EmsBackendApplication.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VivoService {

    @Autowired
    private VivoRepository vivoRepository;

    @Autowired
    private RedisService redisService;

    public VivoEntity createMobile(VivoEntity entity)
    {
        return vivoRepository.save(entity);
    }


    public Optional<VivoEntity> findById(Long id)
    {
        VivoEntity vivoEntity = redisService.get(id.toString(), VivoEntity.class);
        if(vivoEntity!= null)
        {
            return Optional.of(vivoEntity);
        }
        else
        {
            Optional<VivoEntity> entity = vivoRepository.findById(id);
            if (entity.isPresent()) {
                redisService.set(id.toString(), entity.get(), 2L);
            }
            return entity;

        }
    }

    public List<VivoEntity> findAll()
    {
        return vivoRepository.findAll();
    }
}
