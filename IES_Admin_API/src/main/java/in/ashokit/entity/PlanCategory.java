package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="PLAN_CATEGORY_TBL")
public class PlanCategory {
	@Id
	@GeneratedValue
	private Integer planCategoryId;

	private String planCategoryName;

}
