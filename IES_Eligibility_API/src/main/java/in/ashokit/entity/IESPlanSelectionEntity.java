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

import lombok.Data;

@Data
@Entity
@Table(name = "IES_DC_PLAN_SELECTION_TBL")
public class IESPlanSelectionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planSelectionId;

	private Integer planId;

	private Integer caseNum;

	@CreationTimestamp
	private LocalDate createdDate;

	private Integer createdBy;

}
