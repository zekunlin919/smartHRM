package com.murasame.smarthrm.dao;

import com.murasame.smarthrm.entity.Department;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepo extends MongoRepository<Department, Integer> {
}