package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.IESDcChildrenDtlsEntity;

@Repository
public interface ChildRepository extends JpaRepository<IESDcChildrenDtlsEntity, Integer>{

}
