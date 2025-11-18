package com.murasame.smarthrm.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

// 数据库实体
@Data
@Document(collection = "Employee") // 对应MongoDB中的集合名
public class Employee {
	@Id               // ← 告诉 Spring Data 这是主键
	@Field("_id")     // ← 强制映射文档字段 "_id"
	private Integer _id;
	private String empName;
	private Integer depId;
	private List<Map<String, Integer>> skillList;  // 员工拥有技能: [{技能id, 熟练度}...]
    private List<Map<String, Integer>> projects;                 // 员工参与项目: [项目id...]
	private LocalDateTime joinDate;                 // 加入时间
}
