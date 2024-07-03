package user.service.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class Test2Controller {
	@GetMapping("/")
	public String index(HttpServletResponse response) {
		return "index.html";
	}
}
