package in.ashokit.service;

import org.springframework.stereotype.Service;

import in.ashokit.binding.EligibilityForm;

@Service
public interface EligibiliyService {
	public String checkEligibility(EligibilityForm form);
}
