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
@Table(name = "IES_APP_REG_TBL")
public class IESApplCreateEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer caseNumber;

	private String city;

	private String dob;

	private String fullname;

	private String gender;

	private String houseNum;

	private String ssn;

	private String state;

	private Integer createdBy;

	@CreationTimestamp
	private LocalDate createdDate;

}
