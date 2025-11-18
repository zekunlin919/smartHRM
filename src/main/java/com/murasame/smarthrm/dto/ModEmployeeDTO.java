package com.murasame.smarthrm.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ModEmployeeDTO {
    @NotNull(message = "员工ID不能为空")
    private Integer id;

    @NotNull(message = "员工姓名不能为空")
    private String name;

    @NotNull(message = "部门ID不能为空")
    private String department;

    private String skills; // 格式：skillId:熟练度,skillId:熟练度

    private String projects; // 格式：项目ID,项目ID

    private LocalDateTime joinDate;
}
