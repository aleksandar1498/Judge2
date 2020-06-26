package bg.softuni.judge.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.softuni.judge.domain.models.service.ExerciseServiceModel;
import bg.softuni.judge.persistence.entities.JpaExercise;
import bg.softuni.judge.persistence.repositories.ExerciseRepository;
import bg.softuni.judge.services.ExerciseService;

@Service
public class ExerciseServiceImpl implements ExerciseService {
	private ModelMapper mapper;
	private ExerciseRepository exerciseRepo;

	@Autowired
	public ExerciseServiceImpl(ModelMapper mapper, ExerciseRepository exerciseRepo) {
		this.mapper = mapper;
		this.exerciseRepo = exerciseRepo;
	}

	@Override
	public ExerciseServiceModel add(ExerciseServiceModel model) {
		System.out.println(model.toString());
		return this.mapper.map(this.exerciseRepo.save(this.mapper.map(model, JpaExercise.class)),
				ExerciseServiceModel.class);
	}

	@Override
	public List<ExerciseServiceModel> findAll() {
		return this.exerciseRepo.findAll().stream().map(e -> this.mapper.map(e, ExerciseServiceModel.class))
				.collect(Collectors.toList());
	}

	@Override
	public ExerciseServiceModel findById(String exerciseID) {
		JpaExercise exercise = this.exerciseRepo.findById(exerciseID).orElseThrow(() -> {
			return new EntityNotFoundException("Exercise with this Id is not found");
		});
		return this.mapper.map(exercise, ExerciseServiceModel.class);
	}

}
