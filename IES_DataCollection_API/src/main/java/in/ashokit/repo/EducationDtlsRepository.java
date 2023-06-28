package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.IESDcEducationDtlsEntity;

@Repository
public interface EducationDtlsRepository extends JpaRepository<IESDcEducationDtlsEntity, Integer> {

}
