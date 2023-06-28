package in.ashokit.controller;

import java.util.List;

import org.javatuples.Triplet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.ChildDtlsForm;
import in.ashokit.binding.EducationDtlsForm;
import in.ashokit.binding.IncomeDtlsForm;
import in.ashokit.binding.PlanSelectionForm;
import in.ashokit.entity.IESDcChildrenDtlsEntity;
import in.ashokit.entity.IESDcEducationDtlsEntity;
import in.ashokit.entity.IESDcIncomeDtlsEntity;
import in.ashokit.service.DataCollectionService;

@RestController
public class DataCollectionController {

	@Autowired
	private DataCollectionService dataService;
	
	private static final Logger logger = LoggerFactory.getLogger(DataCollectionController.class);

	@GetMapping("/createPlanPage")
	public String planSelectPage() {

		return "Application page loading";
	}

	@PostMapping("/createPlanPage")
	public String createPlan(@RequestBody PlanSelectionForm form) {
		boolean status = dataService.planSeleciton(form);
		if (status) {
			return "App with Plan created";
		} else {
			return "Application not created";
		}

	}

	@GetMapping("/createIncomePage")
	public String IncomeDataPage() {

		return "Application page loading";
	}

	@PostMapping("/createIncomePage")
	public String saveIncomeData(@RequestBody IncomeDtlsForm form) {
		boolean status = dataService.addIncomeDetails(form);
		if (status) {
			return "Income Details Added";
		} else {
			return "Error occured";
		}

	}

	@GetMapping("/createEducationDtlsPage")
	public String EducationDataPage() {

		return "Application page loading";
	}

	@PostMapping("/saveEducationDtls")
	public String saveEducationData(@RequestBody EducationDtlsForm form) {
		boolean status = dataService.addEducationDetails(form);
		if (status) {
			return "Education Details Added";
		} else {
			return "Error occured";
		}

	}

	@GetMapping("/createChildDtlsPage")
	public String ChildDataPage() {

		return "Application page loading";
	}

	@PostMapping("/saveChildDtlsPage")
	public String saveChildData(@RequestBody ChildDtlsForm form) {
		boolean status = dataService.addChildDetails(form);
		if (status) {
			return "Child Details Added";
		} else {
			return "Error occured";
		}

	}
	@GetMapping("/viewSummeryData")
	public String viewPlanDataPage() {
		Triplet<List<IESDcChildrenDtlsEntity>, List<IESDcEducationDtlsEntity>, List<IESDcIncomeDtlsEntity>> summaryData = dataService.summaryData();
		logger.info(summaryData.toString());

		return "Summary Details Loading...";
	}



}
