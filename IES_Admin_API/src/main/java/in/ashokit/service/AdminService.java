package in.ashokit.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import in.ashokit.binding.CaseWorkerRegistrationForm;
import in.ashokit.binding.CreatePlanForm;
import in.ashokit.entity.IESUserEntity;
import in.ashokit.entity.IESCreatePlanEntity;

@Service
public interface AdminService<IESUserResponse> {

	public boolean registrationForm(CaseWorkerRegistrationForm cwform);

	public List<String> getPlanCategoryNames();

	public List<IESCreatePlanEntity> getPlanNames();

	public List<IESUserEntity> getCaseWorkerData();

	public boolean savePlanData(CreatePlanForm form);

	boolean updateUserAccount(Integer userId, CaseWorkerRegistrationForm user);

	boolean updatePlanAccount(Integer planId, CreatePlanForm user);

}
