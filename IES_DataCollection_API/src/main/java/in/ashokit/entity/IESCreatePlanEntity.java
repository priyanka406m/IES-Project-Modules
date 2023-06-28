package in.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import net.bytebuddy.asm.Advice.Local;

@Data
@Entity
@Table(name="IES_PLAN_TBL")
public class IESCreatePlanEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer planId;
	
	private String planName;
	
	@CreationTimestamp
	private LocalDate planStartDate;
	
	@DateTimeFormat
	private LocalDate planEndDate;
	
	private String planCategory;
	
	private String activeSwitch;
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	@UpdateTimestamp
	private LocalDate updatedDate;
	
	private Integer createdBy;
	
	private Integer updatedBy;
	
}
