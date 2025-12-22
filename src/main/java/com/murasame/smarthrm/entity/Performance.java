package com.murasame.smarthrm.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "Performance")
public class Performance {
	@Id               // ← 告诉 Spring Data 这是主键
	@Field("_id")     // ← 强制映射文档字段 "_id"
	private Integer _id;
	private Integer point;
	private LocalDateTime testTime;
}
