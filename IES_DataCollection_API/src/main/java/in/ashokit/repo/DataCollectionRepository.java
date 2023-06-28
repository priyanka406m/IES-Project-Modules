package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.IESPlanSelectionEntity;

@Repository
public interface DataCollectionRepository extends JpaRepository<IESPlanSelectionEntity, Integer> {

	public IESPlanSelectionEntity findByCaseNum(Integer caseNum);

	public IESPlanSelectionEntity findByCaseNumAndPlanId(Integer caseNum, Integer planId);

}
