package in.ashokit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.StudentEnqEntity;

@Repository
public interface StudentEnqRepo extends JpaRepository<StudentEnqEntity, Integer> {

	public Optional<StudentEnqEntity> findByEnqId();
}
