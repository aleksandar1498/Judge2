package bg.softuni.judge.utils;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import bg.softuni.judge.domain.models.service.ExerciseServiceModel;
import bg.softuni.judge.services.ExerciseService;

@Component
public class StringToExerciseModelConverter implements Converter<String,ExerciseServiceModel>{

	private ExerciseService exerciseService;
	
	@Autowired
	public StringToExerciseModelConverter(ExerciseService exerciseService) {
		this.exerciseService = exerciseService;
	}


	@Override
	public ExerciseServiceModel convert(MappingContext<String, ExerciseServiceModel> context) {
		String exerciseID = context.getSource();
		if(exerciseID != null && exerciseID.trim().length() > 0) {
            ExerciseServiceModel model = this.exerciseService.findById(exerciseID);
            return model;
        } else {
            return null;
        }
	}

}
