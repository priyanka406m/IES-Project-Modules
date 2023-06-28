package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.EligibilityForm;
import in.ashokit.service.EligibiliyService;

@RestController
public class EligibilityDtlsController {

	@Autowired
	private EligibiliyService eligibilityService;
	
	@GetMapping("/checkEligibility")
	public String planSelectPage() {

		return "Application page loading";
	}

	@PostMapping("/checkEligDtls")
	public String createPlan(@RequestBody EligibilityForm form) {
		String status = eligibilityService.checkEligibility(form);
		if (status.equals("success")) {
			return "Approved for the plan";
		} else {
			return status;
		}

	}

}
