package in.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "IES_ELIGIBILITY_DTLS_TBL")
public class IESEligibilityCheckEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer edTraceId;

	private Integer caseNum;

	private String planName;

	private String planStatus;

	private LocalDate eligStartDate;

	private LocalDate eligEndDate;

	private Double benifitAmt;

	private String denialReason;

	@CreationTimestamp
	private LocalDate createdDate;

}
