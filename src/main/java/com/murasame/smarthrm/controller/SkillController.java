package com.murasame.smarthrm.controller;

import com.murasame.smarthrm.dao.SkillRepo;
import com.murasame.smarthrm.entity.Skill;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
public class SkillController {

    private final SkillRepo skillRepo;

    // 1. 新增技能
    @PostMapping("/add")
    public String addSkill(@RequestBody Skill skill) {
        if (skill.get_id() == null) {
            // 简单生成ID逻辑：实际项目中建议使用序列或UUID，这里为了演示方便使用时间戳或随机数，
            // 或者由前端保证传值。鉴于其他模块使用Integer，这里假设前端传入或手动生成。
            skill.set_id((int) (System.currentTimeMillis() / 1000));
        }
        skillRepo.save(skill);
        return "Skill added successfully: " + skill.getSkillName();
    }

    // 2. 删除技能
    @DeleteMapping("/delete/{id}")
    public String deleteSkill(@PathVariable Integer id) {
        if (skillRepo.existsById(id)) {
            skillRepo.deleteById(id);
            return "Skill deleted successfully";
        }
        return "Skill not found";
    }

    // 3. 修改技能
    @PostMapping("/update")
    public String updateSkill(@RequestBody Skill skill) {
        if (skill.get_id() != null && skillRepo.existsById(skill.get_id())) {
            skillRepo.save(skill);
            return "Skill updated successfully";
        }
        return "Skill ID not found";
    }

    // 4. 查询所有技能
    @GetMapping("/list")
    public List<Skill> listSkills() {
        return skillRepo.findAll();
    }

    // 5. 根据ID查询技能
    @GetMapping("/{id}")
    public Skill getSkill(@PathVariable Integer id) {
        Optional<Skill> skill = skillRepo.findById(id);
        return skill.orElse(null);
    }
}