package in.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "IES_USER_TBL")
public class IESUserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	private String fullName;

	@Column(unique = true)
	private String userEmail;

	private String userPazzword;

	@Column(unique = true)
	private Long userPhno;

	private String userGender;

	private LocalDate userDob;

	@Column(unique = true)
	private Long userSsn;

	private String activeSwitch;

	private String activeStatus;

	@CreationTimestamp
	private LocalDate createdDate;

	@UpdateTimestamp
	private LocalDate updatedDate;

	private Integer createdBy;

	private Integer updatedby;

	@ManyToOne
	@JoinColumn(name = "roleId")
	private IESUserRolesEntity role;

}
