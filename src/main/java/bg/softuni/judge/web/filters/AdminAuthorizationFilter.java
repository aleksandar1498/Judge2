package bg.softuni.judge.web.filters;

import java.util.Set;

import bg.softuni.judge.enums.Roles;

public class AdminAuthorizationFilter extends BaseAuthorizationFilter {
	
	public AdminAuthorizationFilter() {
		super(Set.of(Roles.ADMIN));
	}


}
