package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.PlanCategory;

@Repository
public interface PlanCategoryRepository extends JpaRepository<PlanCategory, Integer>{

}
