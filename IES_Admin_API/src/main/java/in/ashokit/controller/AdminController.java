package in.ashokit.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.CaseWorkerRegistrationForm;
import in.ashokit.binding.CreatePlanForm;
import in.ashokit.entity.IESUserEntity;
import in.ashokit.entity.IESCreatePlanEntity;
import in.ashokit.service.AdminService;

@RestController
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private AdminService service;

	@GetMapping("/dashboard")
	public String dashBoardPage() {
		return "successs";

	}

	@GetMapping("/signup")
	public String signUpPage() {

		return "signup_success";
	}

	@PostMapping("/signup")
	public String handleSearch(@RequestBody CaseWorkerRegistrationForm form) {
		boolean status = service.registrationForm(form);
		if (status) {
			return "signup success";
		} else {
			return "error";
		}

	}

	@PostMapping("/addPlan")
	public String addEnquiry(@RequestBody CreatePlanForm form) {

		boolean status = service.savePlanData(form);
		if (status) {
			return "plan added success";
		} else {
			return "problem occured";
		}

	}

	@GetMapping("/planNames")
	public String getPlanCategoryNames() {
		List<String> planNames = service.getPlanCategoryNames();
		CreatePlanForm form = new CreatePlanForm();
		return "plan category names loading";

	}

	@GetMapping("/viewCwRecords")
	public String viewCwDataPage(@RequestBody IESUserEntity entity) {

		List<IESUserEntity> recordsEntity = service.getCaseWorkerData();
		CaseWorkerRegistrationForm form = new CaseWorkerRegistrationForm();
		logger.info(recordsEntity.toString());
		return "view-cw-records loading";
	}

	@GetMapping("/viewPlanRecords")
	public String viewPlanDataPage(@RequestBody IESCreatePlanEntity entity) {
		List<IESCreatePlanEntity> planEntity = service.getPlanNames();
		logger.info(planEntity.toString());
		CreatePlanForm form = new CreatePlanForm();

		return "view-plan-records";
	}

	@GetMapping("/updateAcc")
	public String handleUpdateAccount(@RequestBody CaseWorkerRegistrationForm form) {
		boolean status = service.updateUserAccount(7, form);
		logger.info(form.toString());
		if (status) {
			return "update success";
		} else {
			return "error";
		}

	}

	@GetMapping("/updatePlanAcc")
	public String handleUpdatePlanAccount(@RequestBody CreatePlanForm form) {
		boolean status = service.updatePlanAccount(1, form);
		logger.info(form.toString());
		if (status) {
			return "update success";
		} else {
			return "error";
		}

	}

}
