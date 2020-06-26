package bg.softuni.judge.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String getHome(HttpSession session){
		try {
			return session.getAttribute("user") != null ? "home" : "index";
		}catch(IllegalStateException e) {
			return "index";
		}
	}
	
}
