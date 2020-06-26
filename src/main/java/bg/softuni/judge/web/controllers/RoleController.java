package bg.softuni.judge.web.controllers;

import java.util.stream.Collectors;

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

import bg.softuni.judge.domain.models.binding.UserAddModel;
import bg.softuni.judge.services.RoleService;
import bg.softuni.judge.services.UserService;

@Controller
@RequestMapping("/roles")
public class RoleController {
	private ModelMapper mapper;
	private RoleService service;
	private UserService userService;

	@Autowired
	public RoleController(ModelMapper mapper, RoleService service, UserService userService) {
		this.mapper = mapper;
		this.service = service;
		this.userService = userService;
	}

	@GetMapping("add")
	public ModelAndView showAddRoleForm(@ModelAttribute("roleAdd") UserAddModel roleAdd, ModelAndView mView) {
		mView.addObject("users",
				this.userService.findAll().stream().map(u -> u.getUsername()).collect(Collectors.toList()));
		mView.setViewName("role-add");
		return mView;
	}

	@PostMapping("add")
	public String addRole(@Valid @ModelAttribute("roleAdd") UserAddModel roleAdd, BindingResult binding) {
		if (binding.hasErrors()) {
			return "role-add";
		}
		try {
			this.service.addRoleToUser(roleAdd);
		} catch (Exception e) {
			binding.reject("failure", e.getMessage());
			return "role-add";
		}

		return "redirect:/";
	}
}
