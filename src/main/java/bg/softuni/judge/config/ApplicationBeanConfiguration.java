package bg.softuni.judge.config;

import java.util.Set;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import bg.softuni.judge.enums.Roles;
import bg.softuni.judge.web.filters.AdminAuthorizationFilter;
import bg.softuni.judge.web.filters.BaseAuthorizationFilter;

@Configuration
public class ApplicationBeanConfiguration {
	@Bean
	public ModelMapper getModelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
		return mapper;
	}

	// Configure the AuthFilter to the specific path , since the @WebFilter is
	// ignored by Spring
	@Bean
	public FilterRegistrationBean<BaseAuthorizationFilter> commonFilter() {
		FilterRegistrationBean<BaseAuthorizationFilter> authFilterBean = new FilterRegistrationBean<BaseAuthorizationFilter>();
		authFilterBean.setFilter(new BaseAuthorizationFilter(Set.of(Roles.USER, Roles.ADMIN)));
		authFilterBean.addUrlPatterns("/home");
		return authFilterBean;
	}

	@Bean
	public FilterRegistrationBean<AdminAuthorizationFilter> adminFilter() {
		FilterRegistrationBean<AdminAuthorizationFilter> authFilterBean = new FilterRegistrationBean<AdminAuthorizationFilter>();
		authFilterBean.setFilter(new AdminAuthorizationFilter());
		authFilterBean.addUrlPatterns("/exercises/*", "/roles/*");
		return authFilterBean;
	}
}
