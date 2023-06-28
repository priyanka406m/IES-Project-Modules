package in.ashokit.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CaseWorkerRegistrationForm {

	
	private String fullName;
	private String userEmail;
	private String userGender;
	private Long userPhno;
	private LocalDate userDob;
	private Long userSsn;
}
