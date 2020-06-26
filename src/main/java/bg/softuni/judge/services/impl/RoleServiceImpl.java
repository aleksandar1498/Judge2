package bg.softuni.judge.services.impl;

import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.softuni.judge.domain.models.binding.UserAddModel;
import bg.softuni.judge.domain.models.service.RoleServiceModel;
import bg.softuni.judge.domain.models.service.UserServiceModel;
import bg.softuni.judge.enums.Roles;
import bg.softuni.judge.persistence.Role;
import bg.softuni.judge.persistence.entities.JpaRole;
import bg.softuni.judge.persistence.entities.JpaUser;
import bg.softuni.judge.persistence.repositories.RoleRepository;
import bg.softuni.judge.persistence.repositories.UserRepository;
import bg.softuni.judge.services.RoleService;
import bg.softuni.judge.services.UserService;
import bg.softuni.judge.utils.RolesConverter;
import net.bytebuddy.asm.Advice.This;

@Service
public class RoleServiceImpl implements RoleService {

	private final RoleRepository roleRepo;
	private final UserService userService;
	private final ModelMapper mapper;

	@Autowired
	public RoleServiceImpl(final RoleRepository roleRepo, final ModelMapper mapper, final UserService userService) {
		this.userService = userService;
		this.roleRepo = roleRepo;
		this.mapper = mapper;
		this.mapper.addConverter(new RolesConverter());
	}

	@PostConstruct
	public void init() {
		if (roleRepo.count() == 0) {
			roleRepo.save(new JpaRole(Roles.ADMIN));
			roleRepo.save(new JpaRole(Roles.USER));
		}
	}

	@Override
	public RoleServiceModel findByType(Roles roleType) {
		return roleRepo.findByRole(roleType).map(r -> {
			return mapper.map(r, RoleServiceModel.class);
		}).orElse(null);
	}

	@Override
	public UserServiceModel addRoleToUser(UserAddModel roleAdd) {
		UserServiceModel userService = this.userService.findByUsername(roleAdd.getUsername());
		JpaUser user = this.mapper.map(userService, JpaUser.class);
		JpaRole role = this.roleRepo.findByRole(roleAdd.getRole()).orElseThrow(() -> {
			return new EntityNotFoundException("Role not found");
		});
		if (user.getRoles().stream().anyMatch(r -> r.getId().equals(role.getId()))) {
			throw new IllegalArgumentException("User already has this role");
		}
		userService.getRoles().add(this.mapper.map(role,RoleServiceModel.class));
		return this.mapper.map(this.userService.update(userService), UserServiceModel.class);

	}

}
