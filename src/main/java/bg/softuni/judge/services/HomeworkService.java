package bg.softuni.judge.services;

import bg.softuni.judge.domain.models.binding.LoggedInUser;
import bg.softuni.judge.domain.models.service.HomeworkServiceModel;

public interface HomeworkService {
	HomeworkServiceModel create(HomeworkServiceModel homework);

}
