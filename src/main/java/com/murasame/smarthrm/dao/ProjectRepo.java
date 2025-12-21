package com.murasame.smarthrm.dao;

import com.murasame.smarthrm.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Project数据访问层
 * Spring Data MongoDB Repository接口
 */
@Repository
public interface ProjectRepo extends MongoRepository<Project, Integer> {
    boolean existsByProjName(String projName);
    // 根据项目名称模糊查询
    List<Project> findByProjNameContaining(String name);
    // 根据项目状态查询
    List<Project> findByProjStatus(Integer projStatus);

    // ========== 项目管理辅助方法 ==========

    /**
     * 根据项目状态和名称查询
     */
    List<Project> findByProjStatusAndProjNameContaining(Integer projStatus, String name);

    /**
     * 查找所有未归档的项目
     */
    List<Project> findByProjStatusOrderByStartDateDesc(Integer projStatus);

    /**
     * 按启动时间降序排列所有项目
     */
    List<Project> findAllByOrderByStartDateDesc();
}