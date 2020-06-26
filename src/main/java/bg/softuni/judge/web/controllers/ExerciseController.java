package bg.softuni.judge.web.controllers;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bg.softuni.judge.domain.models.binding.ExerciseAddModel;
import bg.softuni.judge.domain.models.service.ExerciseServiceModel;
import bg.softuni.judge.services.ExerciseService;

@Controller
@RequestMapping("/exercises")
public class ExerciseController {
	private ExerciseService service;
	private ModelMapper mapper;

	@Autowired
	public ExerciseController(ExerciseService service, ModelMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping("add")
	public String getExerciseAdd(@ModelAttribute("exercise") ExerciseAddModel exercise) {
		return "exercise-add";
	}

	@PostMapping
	public String addExercise(@Valid @ModelAttribute("exercise") ExerciseAddModel exercise, BindingResult binding) {
		if (binding.hasErrors()) {
			return "exercise-add";
		}

		try {
			this.service.add(this.mapper.map(exercise, ExerciseServiceModel.class));
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			binding.reject("failedExerciseCreation", e.getMessage());
			return "exercise-add";
		}

		return "redirect:/";
	}
}
