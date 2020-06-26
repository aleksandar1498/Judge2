package bg.softuni.judge.web.controllers;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bg.softuni.judge.domain.models.binding.LoggedInUser;
import bg.softuni.judge.domain.models.binding.LoginUserModel;
import bg.softuni.judge.domain.models.binding.RegisterUserModel;
import bg.softuni.judge.domain.models.service.UserLoginServiceModel;
import bg.softuni.judge.domain.models.service.UserRegisterServiceModel;
import bg.softuni.judge.domain.models.service.UserServiceModel;
import bg.softuni.judge.services.UserService;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private final ModelMapper mapper;
	private final UserService userService;

	@Autowired
	public UserController(final ModelMapper mapper, final UserService userService) {
		this.mapper = mapper;
		this.userService = userService;
	}

	@GetMapping("login")
	public ModelAndView getLogin(@ModelAttribute("user") LoginUserModel user, @RequestParam(value = "redirectURL",required = false,defaultValue = "/") String redirectURL,ModelAndView mView) {
		if(!redirectURL.equals("/")) {
			mView.addObject("redirectURL",redirectURL);
		}
		mView.setViewName("login");
		return mView;
	}

	@PostMapping("login")
	public String postLogin(@Valid @ModelAttribute("user") LoginUserModel user, BindingResult bindingResult,
			HttpSession session, @RequestParam(value = "redirectURL",required = false,defaultValue = "/") String redirectURL) {
		if (bindingResult.hasErrors()) {
			return "login";
		}
		try {
			UserServiceModel loginUser = this.userService.login(mapper.map(user, UserServiceModel.class));
			LoggedInUser sessionUser =   this.mapper.map(loginUser, LoggedInUser.class);
			sessionUser.setRoles(loginUser.getRoles().stream().map(r -> r.getRole()).collect(Collectors.toSet()));
			session.setAttribute("user", sessionUser);
		} catch (IllegalArgumentException | NoSuchElementException e) {
			bindingResult.reject("failedLogin", e.getMessage());
			return "login";
		}
		return "redirect:" + redirectURL;
	}

	@GetMapping("register")
	public String getRegister(@ModelAttribute("user") RegisterUserModel user) {
		return "register";
	}

	@PostMapping("register")
	public String postRegister(@Valid @ModelAttribute("user") RegisterUserModel user, BindingResult binding) {
		if (!user.getPassword().equals(user.getConfirmPassword()) && user.getConfirmPassword() != null
				&& user.getPassword() != null) {
			binding.rejectValue("confirmPassword", "confirmPassword", "Passwords are mismatched");
		}
		if (binding.hasErrors()) {
			return "register";
		}
		try {
			this.userService.register(mapper.map(user, UserServiceModel.class));
		} catch (IllegalArgumentException exception) {
			binding.reject("failedRegistration", "Something went wrong , please contact the IT support.");
			return "register";
		}

		return "redirect:/user/login";
	}

	@GetMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/user/login";
	}
}
