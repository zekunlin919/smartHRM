package com.murasame.smarthrm.dao;

import com.murasame.smarthrm.entity.Skill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepo extends MongoRepository<Skill, Integer> {
    // 可以根据需要添加自定义查询方法，例如根据技能名称查找
    Skill findBySkillName(String skillName);
}