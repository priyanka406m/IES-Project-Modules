package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.javatuples.Triplet;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.ChildDtlsForm;
import in.ashokit.binding.EducationDtlsForm;
import in.ashokit.binding.IncomeDtlsForm;
import in.ashokit.binding.PlanSelectionForm;
import in.ashokit.entity.IESApplCreateEntity;
import in.ashokit.entity.IESCreatePlanEntity;
import in.ashokit.entity.IESDcChildrenDtlsEntity;
import in.ashokit.entity.IESDcEducationDtlsEntity;
import in.ashokit.entity.IESDcIncomeDtlsEntity;
import in.ashokit.entity.IESPlanSelectionEntity;
import in.ashokit.entity.IESUserEntity;
import in.ashokit.repo.ApplicationRegRepository;
import in.ashokit.repo.ChildRepository;
import in.ashokit.repo.DataCollectionRepository;
import in.ashokit.repo.EducationDtlsRepository;
import in.ashokit.repo.IncomeDataRepository;
import in.ashokit.repo.PlanDetailsRepositoy;
import in.ashokit.repo.UserDetailsRepository;

@Service
public class DataCollectionServiceImpl implements DataCollectionService {

	@Autowired
	private ApplicationRegRepository applicationRepo;

	@Autowired
	private DataCollectionRepository dataRepo;

	@Autowired
	private UserDetailsRepository userRepo;

	@Autowired
	private PlanDetailsRepositoy planRepo;

	@Autowired
	private IncomeDataRepository incomeRepo;

	@Autowired
	private EducationDtlsRepository educationRepo;

	@Autowired
	private ChildRepository childRepo;

	@Override
	public boolean planSeleciton(PlanSelectionForm form) {

		IESApplCreateEntity appEntity = applicationRepo.findByCaseNumber(form.getCaseNum());
		Optional<IESUserEntity> userEntity = userRepo.findByuserId(form.getUserId());
		IESCreatePlanEntity planNameEntity = planRepo.findByPlanName(form.getPlanName());

		Integer planId = planNameEntity.getPlanId();
		IESPlanSelectionEntity planEntity = new IESPlanSelectionEntity();
		planEntity.setCaseNum(appEntity.getCaseNumber());
		planEntity.setCreatedBy(userEntity.get().getUserId());
		planEntity.setPlanId(planId);

		BeanUtils.copyProperties(form, planEntity);
		dataRepo.save(planEntity);
		return true;
	}

	@Override
	public boolean addIncomeDetails(IncomeDtlsForm form) {
		IESApplCreateEntity appEntity = applicationRepo.findByCaseNumber(form.getCaseNum());

		IESDcIncomeDtlsEntity incomeEntity = new IESDcIncomeDtlsEntity();
		incomeEntity.setCaseNum(appEntity.getCaseNumber());
		BeanUtils.copyProperties(form, incomeEntity);
		incomeRepo.save(incomeEntity);
		return true;
	}

	@Override
	public boolean addEducationDetails(EducationDtlsForm form) {

		IESApplCreateEntity appEntity = applicationRepo.findByCaseNumber(form.getCaseNum());

		IESDcEducationDtlsEntity educationEntity = new IESDcEducationDtlsEntity();
		educationEntity.setCaseNum(appEntity.getCaseNumber());
		BeanUtils.copyProperties(form, educationEntity);
		educationRepo.save(educationEntity);
		return true;

	}

	@Override
	public boolean addChildDetails(ChildDtlsForm form) {

		IESApplCreateEntity appEntity = applicationRepo.findByCaseNumber(form.getCaseNum());

		IESDcChildrenDtlsEntity childEntity = new IESDcChildrenDtlsEntity();
		childEntity.setCaseNum(appEntity.getCaseNumber());
		BeanUtils.copyProperties(form, childEntity);
		childRepo.save(childEntity);
		return true;
	}

	@Override
	public Triplet<List<IESDcChildrenDtlsEntity>,List<IESDcEducationDtlsEntity>,List<IESDcIncomeDtlsEntity>> summaryData() {
		IESApplCreateEntity appEntity = applicationRepo.findByCaseNumber(1);
		List<IESDcChildrenDtlsEntity> childDetails = childRepo.findAll();
		List<IESDcEducationDtlsEntity> eductionDtls = educationRepo.findAll();
		List<IESDcIncomeDtlsEntity> incomeDtls = incomeRepo.findAll();
		return Triplet.with(childDetails, eductionDtls, incomeDtls);
	}

}
