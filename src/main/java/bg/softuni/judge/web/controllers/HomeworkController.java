package bg.softuni.judge.web.controllers;

import java.time.LocalDateTime;

import javax.servlet.http.HttpSession;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bg.softuni.judge.domain.models.service.ExerciseServiceModel;
import bg.softuni.judge.domain.models.binding.CommentModel;
import bg.softuni.judge.domain.models.binding.HomeworkAddModel;
import bg.softuni.judge.domain.models.binding.LoggedInUser;
import bg.softuni.judge.domain.models.service.HomeworkServiceModel;
import bg.softuni.judge.domain.models.service.UserServiceModel;
import bg.softuni.judge.services.ExerciseService;
import bg.softuni.judge.services.HomeworkService;
import bg.softuni.judge.utils.RolesEnumToRoleServiceModelConverter;
import bg.softuni.judge.utils.StringToExerciseModelConverter;

@Controller
@RequestMapping("/homework")
public class HomeworkController {
	private ExerciseService exerciseService;
	private HomeworkService homeworkService;
	private ModelMapper mapper;
	private StringToExerciseModelConverter converter;
	private RolesEnumToRoleServiceModelConverter rolesConverter;

	@Autowired
	public HomeworkController(ExerciseService exerciseService, HomeworkService homeworkService, ModelMapper mapper,
			StringToExerciseModelConverter converter, RolesEnumToRoleServiceModelConverter rolesConverter) {
		this.exerciseService = exerciseService;
		this.homeworkService = homeworkService;
		this.mapper = mapper;
		this.mapper.addConverter(converter);
		this.mapper.addConverter(rolesConverter);
	}

	@GetMapping("add")
	public ModelAndView showHomeworkAddForm(ModelAndView mView, @ModelAttribute("homework") HomeworkAddModel homework) {
		mView.addObject("exercises", this.exerciseService.findAll());
		mView.setViewName("homework-add");
		return mView;
	}

	@PostMapping("add")
	public String addHomework(@ModelAttribute("homework") HomeworkAddModel homework, HttpSession session,
			BindingResult binding) {
		if (binding.hasErrors()) {
			return "homework-add";
		}
		
		HomeworkServiceModel homeworkService = this.mapper.map(homework, HomeworkServiceModel.class);
		homeworkService.setAuthor(this.mapper.map((LoggedInUser) session.getAttribute("user"), UserServiceModel.class));
		homeworkService.setAddedOn(LocalDateTime.now());
		
		try {
			this.homeworkService.create(homeworkService);
		} catch (Exception e) {
			binding.reject("failure", e.getMessage());
			return "homework-add";
		}

		return "redirect:/homework/add";
	}

	@GetMapping("/check")
	public String showHomeworkCheckForm(@ModelAttribute("comment") CommentModel comment) {
		return "homework-check";
	}
	
	@PostMapping("/check")
	public String checkHomework(@ModelAttribute("comment") CommentModel comment,@RequestParam(value = "homeworkId") String homeworkId,BindingResult binding,RedirectAttributes redirect) {
		if(homeworkId == null || homeworkId.trim().length() == 0) {
			binding.reject("homework","Please download a homework to check");
			return "hello";
		}
		System.out.println(comment.toString());
		return "homework-check";
	}
}
