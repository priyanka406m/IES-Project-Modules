package in.ashokit.binding;

import lombok.Data;

@Data
public class CreatePlanForm {

	private String planName;
	private String planStartDate;
	private String planEndDate;
	private String planCategory;
}
