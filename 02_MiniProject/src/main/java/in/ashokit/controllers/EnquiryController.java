package in.ashokit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.binding.DashBoardReponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.binding.EnquirySearchCriteria;
import in.ashokit.entity.StudentEnqEntity;
import in.ashokit.service.EnquiryService;

@Controller
public class EnquiryController {

	@Autowired
	private HttpSession session;

	@Autowired
	private EnquiryService enquiryService;

	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashBoardPage(Model model) {
		Integer userId = (Integer) session.getAttribute("userId");
		DashBoardReponse dashboardData = enquiryService.getDashBoardData(userId);
		model.addAttribute("dashboardData", dashboardData);
		return "dashboard";
	}
	
	@PostMapping("/addEnq")
	public String addEnquiry(@ModelAttribute("formObj")EnquiryForm form ,Model model) {
		
		boolean status  = enquiryService.saveEnquiry(form);
		if(status) {
			model.addAttribute("successMsg","Enquiry Added");
		}else {
			model.addAttribute("errMsg","Problem Occured");
		}
		
		return "add-enquiry";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		List<String> courseNames = enquiryService.getCourseNames();
		List<String> statusNames = enquiryService.getEnqStatuses();
		
		EnquiryForm formObj = new EnquiryForm();
		
		model.addAttribute("courseNames", courseNames);
		model.addAttribute("statusNames", statusNames);
		model.addAttribute("formObj", formObj);
		
		return "add-enquiry";
	}

	@GetMapping("/enquires")
	public String viewEnquiriesPage(EnquirySearchCriteria criteria, Model model) {
		initForm(model);
		model.addAttribute("searchCriteria", new EnquirySearchCriteria());
		List<StudentEnqEntity> entity = enquiryService.getEnquries();
		model.addAttribute("enquires",entity);
		return "view-enquiries";
	}

	private void initForm(Model model) {
		List<String> courseNames = enquiryService.getCourseNames();
		List<String> statusNames = enquiryService.getEnqStatuses();
		
		EnquiryForm formObj = new EnquiryForm();
		
		model.addAttribute("courseNames", courseNames);
		model.addAttribute("statusNames", statusNames);
		model.addAttribute("formObj", formObj);
		
	}
}
