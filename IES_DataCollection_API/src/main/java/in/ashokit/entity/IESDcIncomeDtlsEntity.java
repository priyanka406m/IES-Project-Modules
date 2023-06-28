package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="IES_DC_INCOME_DTLS_TBL")
public class IESDcIncomeDtlsEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer incomeId;

	private Double salaryIncome;

	private Double rentIncome;

	private Double propertyIncome;

	private Integer caseNum;

}
