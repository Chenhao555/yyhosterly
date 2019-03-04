package haohost.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ToAter {

	@GetMapping("/")
	public String index() {
		return "/tupian2";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/login";
	}
}
