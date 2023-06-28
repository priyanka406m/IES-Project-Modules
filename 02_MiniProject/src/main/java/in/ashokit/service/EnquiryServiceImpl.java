package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.DashBoardReponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.binding.EnquirySearchCriteria;
import in.ashokit.entity.CourseEntity;
import in.ashokit.entity.EnqStatusEntity;
import in.ashokit.entity.StudentEnqEntity;
import in.ashokit.entity.UserDtlsEntity;
import in.ashokit.repo.CourseRepo;
import in.ashokit.repo.EnqStatusRepo;
import in.ashokit.repo.StudentEnqRepo;
import in.ashokit.repo.UserDtlsRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private UserDtlsRepo userDtlsRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private EnqStatusRepo enqStatusRepo;

	@Autowired
	private StudentEnqRepo enqRepo;

	@Autowired
	private HttpSession session;

	@Override
	public DashBoardReponse getDashBoardData(Integer userId) {

		DashBoardReponse response = new DashBoardReponse();
		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);

		if (findById.isPresent()) {
			UserDtlsEntity userEntity = findById.get();
			List<StudentEnqEntity> enquiries = userEntity.getEnquiries();
			String name = userEntity.getName();
			Integer totalCnt = enquiries.size();

			Integer enrolledCnt = enquiries.stream().filter(e -> e.getEnquiryStatus().equals("ENROLLED"))
					.collect(Collectors.toList()).size();
			Integer lostCnt = enquiries.stream().filter(e -> e.getEnquiryStatus().equals("LOST"))
					.collect(Collectors.toList()).size();
			response.setTotalEnquiriesCnt(totalCnt);
			response.setEnrolledCnt(enrolledCnt);
			response.setLostCnt(lostCnt);
			response.setUserName(name);
		}

		return response;
	}

	@Override
	public List<String> getCourseNames() {

		List<CourseEntity> courseEntity = courseRepo.findAll();
		List<String> names = new ArrayList<>();

		for (CourseEntity entity : courseEntity) {
			names.add(entity.getCourseName());
		}

		return names;
	}

	@Override
	public List<String> getEnqStatuses() {
		List<EnqStatusEntity> enqStatus = enqStatusRepo.findAll();
		List<String> names = new ArrayList<>();

		for (EnqStatusEntity entity : enqStatus) {
			names.add(entity.getStatusName());

		}

		return names;
	}

	@Override
	public boolean saveEnquiry(EnquiryForm form) {
		StudentEnqEntity entity = new StudentEnqEntity();
		BeanUtils.copyProperties(form, entity);
		Integer user = (Integer) session.getAttribute("userId");
		UserDtlsEntity userEntity = userDtlsRepo.findById(user).get();
		entity.setUser(userEntity);
		enqRepo.save(entity);

		return true;
	}

	@Override
	public List<StudentEnqEntity> getEnquries() {
		Integer user = (Integer)session.getAttribute("userId");
		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(user); 		
		if(findById.isPresent()) {
			UserDtlsEntity userDtlsEntity = findById.get();
			List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
			return enquiries;
		}
		return null;
		
	}
	
	


}
