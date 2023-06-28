package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.IESEligibilityCheckEntity;

@Repository
public interface EligibilityDtlsRepository extends JpaRepository<IESEligibilityCheckEntity, Integer> {
	public IESEligibilityCheckEntity findByCaseNum(Integer caseNum);
}
