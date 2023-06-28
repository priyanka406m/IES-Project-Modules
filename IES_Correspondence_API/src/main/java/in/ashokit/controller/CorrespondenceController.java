package in.ashokit.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lowagie.text.DocumentException;

import in.ashokit.binding.CoForm;
import in.ashokit.service.CoService;

@RestController
public class CorrespondenceController {
	
	@Autowired
	private CoService coService;
	
	@GetMapping("/getCoPage")
	public String planSelectPage() {

		return "Application page loading";
	}

	@PostMapping("/getCoData")
	public String createPlan(@RequestBody CoForm form) throws DocumentException, IOException {
		boolean status = coService.getCitizenData(form);
		if (status) {
			return "App with Plan created";
		} else {
			return "Application not created";
		}

	}
}
