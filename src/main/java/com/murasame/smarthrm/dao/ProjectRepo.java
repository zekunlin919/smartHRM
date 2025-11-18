package com.murasame.smarthrm.dao;

import com.murasame.smarthrm.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository      // 建议统一用 @Repository 而非 @Component
public interface ProjectRepo extends MongoRepository<Project, Integer> {
}