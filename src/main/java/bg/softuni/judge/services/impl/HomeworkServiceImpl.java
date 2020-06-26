package bg.softuni.judge.services.impl;

import java.time.LocalDateTime;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.softuni.judge.domain.models.binding.LoggedInUser;
import bg.softuni.judge.domain.models.service.HomeworkServiceModel;
import bg.softuni.judge.domain.models.service.UserServiceModel;
import bg.softuni.judge.persistence.entities.JpaExercise;
import bg.softuni.judge.persistence.entities.JpaHomework;
import bg.softuni.judge.persistence.entities.JpaUser;
import bg.softuni.judge.persistence.repositories.HomeRepository;
import bg.softuni.judge.services.HomeworkService;
import bg.softuni.judge.utils.RolesEnumToRoleServiceModelConverter;

@Service
public class HomeworkServiceImpl implements HomeworkService {

	private HomeRepository homeRepo;
	private ModelMapper mapper;

	@Autowired
	public HomeworkServiceImpl(HomeRepository homeRepo, ModelMapper mapper) {
		this.homeRepo = homeRepo;
		this.mapper = mapper;
	}

	@Override
	public HomeworkServiceModel create(HomeworkServiceModel homework) {
		JpaHomework homeworkToSave = this.mapper.map(homework, JpaHomework.class);
		homeworkToSave.setAuthor(this.mapper.map(homework.getAuthor(), JpaUser.class));
		homeworkToSave.setExercise(this.mapper.map(homework.getExercise(), JpaExercise.class));
		return this.mapper.map(this.homeRepo.save(homeworkToSave), HomeworkServiceModel.class);
	}

}
