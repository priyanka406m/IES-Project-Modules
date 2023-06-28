package in.ashokit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "IES_USER_ROLES_TBL")
public class IESUserRolesEntity {
	@Id
	@GeneratedValue
	private Integer roleId;
	private String roleName;
	
}
