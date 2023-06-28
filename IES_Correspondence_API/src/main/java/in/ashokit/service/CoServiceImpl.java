package in.ashokit.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;

import in.ashokit.binding.CoForm;
import in.ashokit.entity.IESApplCreateEntity;
import in.ashokit.entity.IESCoEntity;
import in.ashokit.entity.IESEligibilityCheckEntity;
import in.ashokit.repository.ApplicationRegRepository;
import in.ashokit.repository.CoRepository;
import in.ashokit.repository.EligibilityDtlsRepository;
import in.ashokit.util.EmailUtils;
import in.ashokit.util.PdfGeneratorUtil;

@Service
public class CoServiceImpl implements CoService {

	@Autowired
	private ApplicationRegRepository applicationRepo;

	@Autowired
	private EligibilityDtlsRepository eligibilityDtlsRepository;

	@Autowired
	private CoRepository coRepository;

	@Autowired
	private PdfGeneratorUtil pdf;

	@Autowired
	private EmailUtils email;

	@Override
	public boolean getCitizenData(CoForm form) throws DocumentException, IOException {

		IESApplCreateEntity appEntity = applicationRepo.findByCaseNumber(form.getCaseNum());
		IESCoEntity coEntity = new IESCoEntity();

		if (appEntity.getCaseNumber() != null) {
			IESEligibilityCheckEntity eligibilityEntity = eligibilityDtlsRepository
					.findByCaseNum(appEntity.getCaseNumber());

			BeanUtils.copyProperties(form, coEntity);
			File f = new File("correspondence.pdf");
			pdf.getCitizenData(eligibilityEntity, f);

			coEntity.setCoPdf(pdf.getCitizenData(eligibilityEntity, f));
			coEntity.setEdTraceId(eligibilityEntity.getEdTraceId());
			coEntity.setNoticeStatus("P");
			coEntity.setPrintDate(LocalDate.now().plusDays(2));

			List<IESCoEntity> coRepo = coRepository.findAll();
			if (coRepo != null && !coRepo.isEmpty()) {

				LocalDate createdDate = null;

				LocalDate printDate = null;

				for (IESCoEntity iesCoEntity : coRepo) {
					createdDate = iesCoEntity.getCreatedDate();
					printDate = iesCoEntity.getPrintDate();
				}

				if (printDate.isAfter(createdDate)) {

				}
				coRepository.save(coEntity);
			}

			coRepository.save(coEntity);
			String subject = "Test mail subject";
			String body = "<h1>Test mail body</h1>";
			String to = "arigetoniyopriyanka@gmail.com";

			email.sendEmail(to, subject, body, f);
			f.delete();

		}

		return true;

	}
	public boolean sendEmail() {
		return true;
	}
}
