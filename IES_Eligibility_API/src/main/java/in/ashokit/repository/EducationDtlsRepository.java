package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.IESDcEducationDtlsEntity;
import java.lang.Integer;
import java.util.List;

@Repository
public interface EducationDtlsRepository extends JpaRepository<IESDcEducationDtlsEntity, Integer> {

	public IESDcEducationDtlsEntity findByCaseNum(Integer casenum);
}
