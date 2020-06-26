package bg.softuni.judge.utils;

import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bg.softuni.judge.domain.models.service.RoleServiceModel;
import bg.softuni.judge.enums.Roles;
import bg.softuni.judge.services.RoleService;

@Component
public class RolesEnumToRoleServiceModelConverter implements Converter<Roles, RoleServiceModel>{
	
	private RoleService roleService;
	
	@Autowired
	public RolesEnumToRoleServiceModelConverter(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public RoleServiceModel convert(MappingContext<Roles, RoleServiceModel> context) {
		Roles role = context.getSource();
		if(role != null) {
			return this.roleService.findByType(role);
		}
		return null;
	}

}
