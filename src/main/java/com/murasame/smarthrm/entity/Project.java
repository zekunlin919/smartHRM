package com.murasame.smarthrm.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "Project")
public class Project {

	@Id
	private Integer id;                 // _id

	@Field("projName")
	private String projName;

	@Field("members")
	private List<Member> members;

	@Field("reqSkill")
	private List<ReqSkill> reqSkill;

	@Field("projStatus")
	private Integer projStatus;//0-未归档 1-已归档

	@Field("startDate")
	private LocalDateTime startDate;

	/* ===== 嵌套对象 ===== */
	@Data
	public static class Member {
		@Field("empId")
		private Integer empId;
	}

	@Data
	public static class ReqSkill {
		@Field("skillId")
		private Integer skillId;
	}
}