package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.IESAppCreateForm;
import in.ashokit.service.ApplicationRegService;

@RestController
public class IESApplCreateController {
	@Autowired
	private ApplicationRegService service;

	@GetMapping("/create")
	public String signUpPage() {

		return "signup_success";
	}

	@PostMapping("/createApp")
	public String createAppl(@RequestBody IESAppCreateForm form) {
		boolean status = service.createApplRegForm(form);
		if (status) {
			return "Application created";
		} else {
			return "Application not created";
		}

	}
	
}
