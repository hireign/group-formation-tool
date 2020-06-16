package CSCI5308.GroupFormationTool.ErrorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController
{
	@GetMapping("/error")
	public String error(Model model)
	{
		return "error";
	}
	
	@GetMapping("/denied")
	public ResponseEntity denied(Model model)
	{
		return new ResponseEntity(HttpStatus.FORBIDDEN);
	}
}