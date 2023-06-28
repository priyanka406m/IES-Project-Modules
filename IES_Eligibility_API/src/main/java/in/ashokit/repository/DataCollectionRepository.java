package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.IESPlanSelectionEntity;

@Repository
public interface DataCollectionRepository extends JpaRepository<IESPlanSelectionEntity, Integer> {

	public IESPlanSelectionEntity findByCaseNum(Integer caseNum);

}
