package in.ashokit.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;

import in.ashokit.binding.CoForm;

@Service
public interface CoService {
	public boolean getCitizenData(CoForm form) throws DocumentException, IOException;
}
