package in.ashokit.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.EligibilityForm;
import in.ashokit.entity.IESApplCreateEntity;
import in.ashokit.entity.IESCreatePlanEntity;
import in.ashokit.entity.IESDcChildrenDtlsEntity;
import in.ashokit.entity.IESDcEducationDtlsEntity;
import in.ashokit.entity.IESDcIncomeDtlsEntity;
import in.ashokit.entity.IESEligibilityCheckEntity;
import in.ashokit.entity.IESPlanSelectionEntity;
import in.ashokit.repository.ApplicationRegRepository;
import in.ashokit.repository.ChildRepository;
import in.ashokit.repository.DataCollectionRepository;
import in.ashokit.repository.EducationDtlsRepository;
import in.ashokit.repository.EligibilityDtlsRepository;
import in.ashokit.repository.IncomeDataRepository;
import in.ashokit.repository.PlanDetailsRepositoy;
import in.ashokit.util.AgeCalculatorUtil;

@Service
public class EligibilityServiceImpl implements EligibiliyService {

	@Autowired
	private DataCollectionRepository dataRepo;

	@Autowired
	private IncomeDataRepository incomeRepo;

	@Autowired
	private EducationDtlsRepository edRepo;

	@Autowired
	private ChildRepository childRepo;

	@Autowired
	private PlanDetailsRepositoy planRepo;

	@Autowired
	private EligibilityDtlsRepository eligibilityRepo;

	@Autowired
	private ApplicationRegRepository regRepo;

	@Autowired
	private AgeCalculatorUtil ageCalUtil;

	@Override
	public String checkEligibility(EligibilityForm form) {

		boolean isCaseNumPresent = false;
		String planName = null;
		List<IESEligibilityCheckEntity> findByCaseNum = eligibilityRepo.findAll();

		for (IESEligibilityCheckEntity iesEligibilityCheckEntity : findByCaseNum) {
			if (iesEligibilityCheckEntity.getCaseNum() == form.getCaseNum()) {
				planName = iesEligibilityCheckEntity.getPlanName();
				isCaseNumPresent = true;
				break;
			}
		}

		if (isCaseNumPresent) {
			return "Already exists wih " + planName + " plan";
		} else {

			IESPlanSelectionEntity dataEntity = dataRepo.findByCaseNum(form.getCaseNum());
			IESApplCreateEntity findByCaseNumber = regRepo.findByCaseNumber(form.getCaseNum());
			IESDcEducationDtlsEntity educationalEntity = edRepo.findByCaseNum(form.getCaseNum());
			List<IESDcChildrenDtlsEntity> childEntity = childRepo.findAll();

			/*Getting child Age*/
			
			List<Integer> childAgeList = new ArrayList<>();
			int childAge = 0;

			for (IESDcChildrenDtlsEntity iesDcChildrenDtlsEntity : childEntity) {
				if (iesDcChildrenDtlsEntity.getCaseNum() == form.getCaseNum()) {
					LocalDate childDob = iesDcChildrenDtlsEntity.getChildDob();
					LocalDate currentDate = LocalDate.now();
					Period age = Period.between(childDob, currentDate);
					childAge = age.getYears();
					boolean addChildDob = childAgeList.add(childAge);
				}
			}

			Integer planId = dataEntity.getPlanId();
			Integer caseNo = dataEntity.getCaseNum();

			Optional<IESCreatePlanEntity> planDtls = planRepo.findByPlanId(planId);
			IESDcIncomeDtlsEntity incomeEntity = incomeRepo.findByCaseNum(caseNo);
			IESEligibilityCheckEntity eligEntity = new IESEligibilityCheckEntity();

			/* Getting Income details */

			Double salaryIncome = incomeEntity.getSalaryIncome();
			Double propertyIncome = incomeEntity.getPropertyIncome();
			Double rentIncome = incomeEntity.getRentIncome();
			Double totalIncome = salaryIncome + propertyIncome + rentIncome;

			/* Getting Citizen Age Details */

			String dob = findByCaseNumber.getDob();
			LocalDate dateOfBirth = LocalDate.parse(dob);
			LocalDate currentDate = LocalDate.now();
			Period age = Period.between(dateOfBirth, currentDate);
			String formatAge = ageCalUtil.formatAge(age);
			int comparisonResult = formatAge.compareTo("65");

			/* Getting educational details */

			String highestDegree = educationalEntity.getHighestDegree();

			/* Getting Citizen kids Details */

			if (planId == 2 && incomeEntity != null) {

				if (totalIncome < 30000) {
					eligEntity.setBenifitAmt(10000.0);
					eligEntity.setEligStartDate(LocalDate.of(2023, 8, 01));
					eligEntity.setEligEndDate(LocalDate.now().plusYears(5));
					eligEntity.setPlanName(planDtls.get().getPlanName());
					eligEntity.setPlanStatus("APPROVED");
					eligEntity.setDenialReason("NoN");
				} else {
					eligEntity.setBenifitAmt(0.0);
					eligEntity.setEligStartDate(null);
					eligEntity.setEligEndDate(null);
					eligEntity.setPlanName("Non");
					eligEntity.setPlanStatus("Denied");
					eligEntity.setDenialReason("income greater than the limit");
				}
			}
			if (planId == 3 && incomeEntity != null) {
				if (totalIncome < 20000) {
					eligEntity.setBenifitAmt(10000.0);
					eligEntity.setEligStartDate(LocalDate.of(2023, 8, 01));
					eligEntity.setEligEndDate(LocalDate.now().plusYears(5));
					eligEntity.setPlanName(planDtls.get().getPlanName());
					eligEntity.setPlanStatus("APPROVED");
					eligEntity.setDenialReason("NoN");
				} else {
					eligEntity.setDenialReason("Denied");
				}

			}
			if (planId == 4) {

				if (comparisonResult <= 0) {
					eligEntity.setBenifitAmt(0.0);
					eligEntity.setEligStartDate(null);
					eligEntity.setEligEndDate(null);
					eligEntity.setPlanName("Non");
					eligEntity.setPlanStatus("DENIED");
					eligEntity.setDenialReason("Age less the 65");
				} else {
					eligEntity.setBenifitAmt(10000.0);
					eligEntity.setEligStartDate(LocalDate.of(2023, 8, 01));
					eligEntity.setEligEndDate(LocalDate.now().plusYears(5));
					eligEntity.setPlanName("Non");
					eligEntity.setPlanName(planDtls.get().getPlanName());
					eligEntity.setPlanStatus("APPROVED");
					eligEntity.setDenialReason("Non");

				}

			}
			if (planId == 5) {
				if (salaryIncome == null || salaryIncome == 0 && !highestDegree.equals("others")) {
					eligEntity.setBenifitAmt(10000.0);
					eligEntity.setEligStartDate(LocalDate.of(2023, 8, 01));
					eligEntity.setEligEndDate(LocalDate.now().plusYears(5));
					eligEntity.setPlanName("Non");
					eligEntity.setPlanName(planDtls.get().getPlanName());
					eligEntity.setPlanStatus("APPROVED");
					eligEntity.setDenialReason("Non");
				} else {
					eligEntity.setBenifitAmt(0.0);
					eligEntity.setEligStartDate(null);
					eligEntity.setEligEndDate(null);
					eligEntity.setPlanName("Non");
					eligEntity.setPlanStatus("DENIED");
					eligEntity.setDenialReason("Highest Degree will be Graduation");
				}
			}

			if (planId == 1) {
				int count = 0;
				for (int i = 0; i < childAgeList.size(); i++) {
					Integer childAges = childAgeList.get(i);
					if (childAges <= 16) {
						count++;
					}
				}
				if (totalIncome < 5000 && count >= 1) {
					eligEntity.setBenifitAmt(10000.0);
					eligEntity.setEligStartDate(LocalDate.of(2023, 8, 01));
					eligEntity.setEligEndDate(LocalDate.now().plusYears(5));
					eligEntity.setPlanName("Non");
					eligEntity.setPlanName(planDtls.get().getPlanName());
					eligEntity.setPlanStatus("APPROVED");
					eligEntity.setDenialReason("Non");

				} else {
					eligEntity.setBenifitAmt(0.0);
					eligEntity.setEligStartDate(null);
					eligEntity.setEligEndDate(null);
					eligEntity.setPlanName("Non");
					eligEntity.setPlanStatus("DENIED");
					eligEntity.setDenialReason("Highest Degree will be Graduation");
				}

			}
			BeanUtils.copyProperties(form, eligEntity);
			eligibilityRepo.save(eligEntity);

			return "success";

		}

	}

}
