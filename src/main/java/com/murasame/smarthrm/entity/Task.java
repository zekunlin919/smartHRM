package com.murasame.smarthrm.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class Task {
	@Id               // ← 告诉 Spring Data 这是主键
	@Field("_id")     // ← 强制映射文档字段 "_id"
	private Integer _id;
	private Integer projId;     // 外键约束 项目内的任务
	private String taskName;
	private Integer managerId;
	private Integer taskStatus;//0-未完成 1-已完成
}
