package bg.softuni.judge.services;

import java.util.List;

import bg.softuni.judge.domain.models.service.UserLoginServiceModel;
import bg.softuni.judge.domain.models.service.UserRegisterServiceModel;
import bg.softuni.judge.domain.models.service.UserServiceModel;

public interface UserService {
	UserServiceModel register(UserServiceModel userServiceModel);
	UserServiceModel login(UserServiceModel user);
	UserServiceModel update(UserServiceModel user);
	List<UserServiceModel> findAll();
	UserServiceModel findByUsername(String username);
}
