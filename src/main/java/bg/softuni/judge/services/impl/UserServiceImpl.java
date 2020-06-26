package bg.softuni.judge.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.softuni.judge.common.constants.ExceptionMessages;
import bg.softuni.judge.domain.models.service.RoleServiceModel;
import bg.softuni.judge.domain.models.service.UserLoginServiceModel;
import bg.softuni.judge.domain.models.service.UserRegisterServiceModel;
import bg.softuni.judge.domain.models.service.UserServiceModel;
import bg.softuni.judge.enums.Roles;
import bg.softuni.judge.persistence.Role;
import bg.softuni.judge.persistence.entities.JpaRole;
import bg.softuni.judge.persistence.entities.JpaUser;
import bg.softuni.judge.persistence.repositories.RoleRepository;
import bg.softuni.judge.persistence.repositories.UserRepository;
import bg.softuni.judge.services.RoleService;
import bg.softuni.judge.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepo;
	private final ModelMapper mapper;
	private final RoleRepository roleRepo;
	

	@Autowired
	public UserServiceImpl(UserRepository userRepo, ModelMapper mapper,RoleRepository roleRepo) {
		this.userRepo = userRepo;
		this.mapper = mapper;
		this.roleRepo = roleRepo;
		//this.mapper.addConverter(this.convertToRole);
	}

	@Override
	public UserServiceModel login(UserServiceModel user) {
		JpaUser resultUser = userRepo.findByUsername(user.getUsername()).orElseThrow(() -> {
			return new NoSuchElementException(ExceptionMessages.INVALID_CREDENTIALS);
		});

		if (!resultUser.getPassword().equals(user.getPassword())) {
			throw new IllegalArgumentException(ExceptionMessages.INVALID_CREDENTIALS);
		}
		return mapper.map(resultUser, UserServiceModel.class);

	}

	@Override
	public UserServiceModel register(UserServiceModel register) {
		if (!register.getPassword().equals(register.getConfirmPassword())) {
			throw new IllegalArgumentException(ExceptionMessages.PASSWORDS_DO_NOT_MATCH);
		}
		JpaUser userToSave = mapper.map(register, JpaUser.class);
		userToSave.setRoles(getRolesForUser());
		JpaUser saved = userRepo.save(userToSave);

		return mapper.map(saved, UserServiceModel.class); 	
	}

	private Set<Role> getRolesForUser() {
		return Set.of(this.roleRepo.findByRole(userRepo.count() == 0 ? Roles.ADMIN : Roles.USER).orElse(null));
	}

	@Override
	public UserServiceModel findByUsername(String username) {
		JpaUser user = this.userRepo.findByUsername(username).orElseThrow(() -> {
			return new EntityNotFoundException(ExceptionMessages.INVALID_USER_USERNAME);
		});
		return this.mapper.map(user,UserServiceModel.class);
	}

	@Override
	public List<UserServiceModel> findAll() {
		return this.userRepo.findAll().stream().map(u -> this.mapper.map(u,UserServiceModel.class)).collect(Collectors.toList());
	}

	@Override
	public UserServiceModel update(UserServiceModel user) {
		JpaUser userToSave = this.userRepo.findByUsername(user.getUsername()).orElseThrow(() -> {
			return new EntityNotFoundException(ExceptionMessages.INVALID_USER_USERNAME);
		});
		userToSave.setRoles(user.getRoles().stream().map(r -> this.mapper.map(r,JpaRole.class)).collect(Collectors.toSet()));
		userRepo.save(userToSave);
		return mapper.map(userToSave, UserServiceModel.class); 	
	}

}
