package in.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.IESCoEntity;

@Repository
public interface CoRepository extends JpaRepository<IESCoEntity, Integer>{

}
