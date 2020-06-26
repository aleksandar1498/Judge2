package bg.softuni.judge.services;

import java.util.List;

import bg.softuni.judge.domain.models.service.ExerciseServiceModel;

public interface ExerciseService {
	ExerciseServiceModel add(ExerciseServiceModel model);

	List<ExerciseServiceModel> findAll();

	ExerciseServiceModel findById(String exerciseID);
}
