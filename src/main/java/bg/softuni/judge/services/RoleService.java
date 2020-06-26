package bg.softuni.judge.services;

import javax.validation.Valid;

import bg.softuni.judge.domain.models.binding.UserAddModel;
import bg.softuni.judge.domain.models.service.RoleServiceModel;
import bg.softuni.judge.domain.models.service.UserServiceModel;
import bg.softuni.judge.enums.Roles;

public interface RoleService {
	RoleServiceModel findByType(Roles roleType);
	UserServiceModel addRoleToUser(UserAddModel roleAdd);
}
