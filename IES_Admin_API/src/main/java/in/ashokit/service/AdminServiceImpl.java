package in.ashokit.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.CaseWorkerRegistrationForm;
import in.ashokit.binding.CreatePlanForm;
import in.ashokit.entity.IESCreatePlanEntity;
import in.ashokit.entity.IESUserEntity;
import in.ashokit.entity.IESUserRolesEntity;
import in.ashokit.entity.PlanCategory;
import in.ashokit.repo.PlanCategoryRepository;
import in.ashokit.repo.PlanDetailsRepositoy;
import in.ashokit.repo.UserDetailsRepository;
import in.ashokit.util.EmailUtils;
import in.ashokit.util.PwdUtils;

@Service
public class AdminServiceImpl<IESUserResponse> implements AdminService {

	@Autowired
	private EmailUtils emailUtils;

	@Autowired
	private UserDetailsRepository repository;

	@Autowired
	private PlanCategoryRepository planRepository;

	@Autowired
	private PlanDetailsRepositoy planNamesRepo;

	@Override
	public boolean registrationForm(CaseWorkerRegistrationForm cwform) {

		IESUserEntity admin = repository.findByUserEmail(cwform.getUserEmail());
		if (admin != null) {
			return false;

		}

		IESUserEntity entity = new IESUserEntity();
		IESUserRolesEntity role = new IESUserRolesEntity();
		role.setRoleId(2);
		BeanUtils.copyProperties(cwform, entity);

		String tempPwd = PwdUtils.generateRandomPwd();
		entity.setUserPazzword(tempPwd);
		entity.setCreatedBy(1);

		entity.setActiveSwitch("Y");
		entity.setActiveStatus("LOCKED");
		entity.setUpdatedby(1);

		entity.setRole(role);

		repository.save(entity);

		String to = cwform.getUserEmail();
		String subject = "Unlock Your Account";
		StringBuffer body = new StringBuffer();
		body.append("<h1>Use below temporary password to unlock your account</h1>");
		body.append("Temporary Pwd: " + tempPwd);
		body.append("<br/>");
		body.append("<a href=\"http://localhost:8181/unlock?email=" + to + "\">Click Here To Unlcok Your Account</a>");
		emailUtils.sendEmail(to, subject, body.toString());
		return true;

	}

	@Override
	public List<String> getPlanCategoryNames() {

		List<PlanCategory> planEntity = planRepository.findAll();
		List<String> planNames = new ArrayList<>();

		for (PlanCategory entity : planEntity) {
			planNames.add(entity.getPlanCategoryName());
		}

		return planNames;
	}

	@Override
	public List<IESCreatePlanEntity> getPlanNames() {
		return planNamesRepo.findAll();
	}

	@Override
	public List<IESUserEntity> getCaseWorkerData() {
		return repository.findAll();
	}

	@Override
	public boolean savePlanData(CreatePlanForm form) {
		IESCreatePlanEntity entity = new IESCreatePlanEntity();
		BeanUtils.copyProperties(form, entity);
		String planStartDate = (String) form.getPlanStartDate();
		String planEndDate = (String) form.getPlanEndDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// convert String to LocalDate
		LocalDate localDate = LocalDate.parse(planStartDate, formatter);
		LocalDate localDate1 = LocalDate.parse(planEndDate, formatter);
		entity.setPlanStartDate(localDate);
		entity.setPlanEndDate(localDate1);
		entity.setActiveSwitch("Y");
		entity.setCreatedBy(1);
		entity.setUpdatedBy(1);

		planNamesRepo.save(entity);

		return true;
	}

	@Override
	public boolean updateUserAccount(Integer userId, CaseWorkerRegistrationForm user) {
		Optional<IESUserEntity> foundUserOpt = repository.findById(userId);
		IESUserEntity foundUser = foundUserOpt
				.orElseThrow(() -> new EntityNotFoundException("userId" + userId + "not found."));
		user.setUserEmail(foundUser.getUserEmail());
		user.setUserSsn(foundUser.getUserSsn());
		BeanUtils.copyProperties(user, foundUser);
		repository.save(foundUser);

		return true;
	}

	@Override
	public boolean updatePlanAccount(Integer planId, CreatePlanForm plan) {
		Optional<IESCreatePlanEntity> foundPlanOpt = planNamesRepo.findByPlanId(planId);
		IESCreatePlanEntity foundPlan = foundPlanOpt
				.orElseThrow(() -> new EntityNotFoundException("userId" + planId + "not found."));
	//	user.setUserEmail(foundUser.getUserEmail());
		//user.setUserSsn(foundUser.getUserSsn());
		BeanUtils.copyProperties(plan, foundPlan);
		planNamesRepo.save(foundPlan);

		return true;
	}	
	
}
