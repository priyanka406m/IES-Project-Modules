package in.ashokit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.IESCreatePlanEntity;
import in.ashokit.entity.IESUserEntity;

@Repository
public interface PlanDetailsRepositoy extends JpaRepository<IESCreatePlanEntity, Integer> {
	public Optional<IESCreatePlanEntity> findByPlanId(Integer planId);
	
	public IESCreatePlanEntity findByPlanName(String planName);
}
