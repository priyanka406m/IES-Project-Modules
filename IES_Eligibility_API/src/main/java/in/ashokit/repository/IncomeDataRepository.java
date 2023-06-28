package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.IESDcIncomeDtlsEntity;

@Repository
public interface IncomeDataRepository extends JpaRepository<IESDcIncomeDtlsEntity, Integer>{
	
	public IESDcIncomeDtlsEntity findByCaseNum(Integer caseNum);

}
