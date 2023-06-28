package in.ashokit.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "IES_CO_DTLS_TBL")
public class IESCoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer noticeId;

	private Integer CaseNum;

	private Integer edTraceId;

	@Lob
	private byte[] coPdf;

	private LocalDate printDate;

	private String noticeStatus;

	@CreationTimestamp
	private LocalDate createdDate;
}