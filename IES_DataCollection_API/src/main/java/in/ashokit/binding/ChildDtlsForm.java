package in.ashokit.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ChildDtlsForm {

	private String childName;
	
	private LocalDate childDob;
	
	private String childSsn;
	
	private Integer caseNum;
}
