package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.EnqStatusEntity;

@Repository
public interface EnqStatusRepo extends JpaRepository<EnqStatusEntity, Integer>{


}
