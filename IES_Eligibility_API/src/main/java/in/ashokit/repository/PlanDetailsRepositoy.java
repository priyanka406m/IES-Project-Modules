package in.ashokit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.IESCreatePlanEntity;

@Repository
public interface PlanDetailsRepositoy extends JpaRepository<IESCreatePlanEntity, Integer> {
	public Optional<IESCreatePlanEntity> findByPlanId(Integer planId);
	
	public IESCreatePlanEntity findByPlanName(String planName);
}
