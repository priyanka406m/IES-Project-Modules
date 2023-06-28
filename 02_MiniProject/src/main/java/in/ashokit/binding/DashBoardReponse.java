package in.ashokit.binding;

import lombok.Data;

@Data
public class DashBoardReponse {
	
	private String userName;

	private Integer totalEnquiriesCnt;
	
	private Integer enrolledCnt;
	
	private Integer lostCnt;
}
