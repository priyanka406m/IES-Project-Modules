package in.ashokit.service;

import java.util.List;

import org.javatuples.Triplet;
import org.springframework.stereotype.Service;

import in.ashokit.binding.ChildDtlsForm;
import in.ashokit.binding.EducationDtlsForm;
import in.ashokit.binding.IncomeDtlsForm;
import in.ashokit.binding.PlanSelectionForm;
import in.ashokit.entity.IESDcChildrenDtlsEntity;
import in.ashokit.entity.IESDcEducationDtlsEntity;
import in.ashokit.entity.IESDcIncomeDtlsEntity;

@Service
public interface DataCollectionService {
	
	
	public boolean addEducationDetails(EducationDtlsForm form);
	
	public boolean addChildDetails(ChildDtlsForm form);

	boolean planSeleciton(PlanSelectionForm form);

	public boolean addIncomeDetails(IncomeDtlsForm form);
	
	public Triplet<List<IESDcChildrenDtlsEntity>, List<IESDcEducationDtlsEntity>, List<IESDcIncomeDtlsEntity>> summaryData();
}
