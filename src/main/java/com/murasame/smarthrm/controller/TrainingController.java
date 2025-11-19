package com.murasame.smarthrm.controller;

import com.murasame.smarthrm.dao.TrainingRepo;
import com.murasame.smarthrm.entity.Training;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/training")
@RequiredArgsConstructor
public class TrainingController {

    private final TrainingRepo trainingRepo;

    // 1. 新增培训
    @PostMapping("/add")
    public String addTraining(@RequestBody Training training) {
        if (training.get_id() == null) {
            training.set_id((int) (System.currentTimeMillis() / 1000));
        }
        trainingRepo.save(training);
        return "Training added successfully: " + training.getTrainName();
    }

    // 2. 删除培训
    @DeleteMapping("/delete/{id}")
    public String deleteTraining(@PathVariable Integer id) {
        if (trainingRepo.existsById(id)) {
            trainingRepo.deleteById(id);
            return "Training deleted successfully";
        }
        return "Training not found";
    }

    // 3. 修改培训
    @PostMapping("/update")
    public String updateTraining(@RequestBody Training training) {
        if (training.get_id() != null && trainingRepo.existsById(training.get_id())) {
            trainingRepo.save(training);
            return "Training updated successfully";
        }
        return "Training ID not found";
    }

    // 4. 查询所有培训
    @GetMapping("/list")
    public List<Training> listTrainings() {
        return trainingRepo.findAll();
    }

    // 5. 根据关联的技能ID查询培训课程（例如：查找所有关于Python的培训）
    @GetMapping("/bySkill/{skillId}")
    public List<Training> getTrainingBySkill(@PathVariable Integer skillId) {
        return trainingRepo.findBySkillId(skillId);
    }
}