package bg.softuni.judge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import bg.softuni.judge.utils.StringToExerciseModelConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
//	@Autowired
//	private StringToExerciseModelConverter modelConverter;
//	
//	@Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addConverter(modelConverter);
//    }
}
