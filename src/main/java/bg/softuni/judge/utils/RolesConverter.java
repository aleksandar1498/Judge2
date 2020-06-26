package bg.softuni.judge.utils;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

import bg.softuni.judge.domain.models.service.RoleServiceModel;
import bg.softuni.judge.persistence.Role;
import bg.softuni.judge.persistence.entities.JpaRole;
public class RolesConverter implements Converter<RoleServiceModel,Role>{


	@Override
	public Role convert(MappingContext<RoleServiceModel, Role> context) {
		RoleServiceModel role = context.getSource();
		if(role == null) {
			return null;
		}
		return new JpaRole(role.getId(), role.getRole());
	}

}
