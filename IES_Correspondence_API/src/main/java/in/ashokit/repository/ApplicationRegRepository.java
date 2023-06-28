package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.IESApplCreateEntity;

@Repository
public interface ApplicationRegRepository extends JpaRepository<IESApplCreateEntity, Integer>{
	
	public IESApplCreateEntity findBySsn(String ssn);
	
	public IESApplCreateEntity findByCaseNumber(Integer integer);
	
}
