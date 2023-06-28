package in.ashokit.service;

import org.springframework.stereotype.Service;

import in.ashokit.binding.IESAppCreateForm;

@Service
public interface ApplicationRegService {

	public boolean createApplRegForm(IESAppCreateForm form);
	

}
