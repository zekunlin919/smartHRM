package com.murasame.smarthrm.dao;

import com.murasame.smarthrm.entity.Training;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepo extends MongoRepository<Training, Integer> {
    // 根据技能ID查询相关的培训课程
    List<Training> findBySkillId(Integer skillId);
}