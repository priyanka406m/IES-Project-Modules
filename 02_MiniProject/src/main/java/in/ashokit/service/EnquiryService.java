package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.DashBoardReponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.binding.EnquirySearchCriteria;
import in.ashokit.entity.StudentEnqEntity;

public interface EnquiryService {

	public DashBoardReponse getDashBoardData(Integer userId) ;

	public List<String> getCourseNames();

	public List<String> getEnqStatuses();
	
	public boolean saveEnquiry(EnquiryForm form);

	public List<StudentEnqEntity> getEnquries();
	
/*	public String upsertEnquiry(EnquiryForm form);

	

	public EnquiryForm getEnquiry(Integer enqId);*/
}
