package in.ashokit.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.ashokit.entity.IESUserEntity;
import in.ashokit.entity.IESCreatePlanEntity;

@Repository
public interface UserDetailsRepository extends JpaRepository<IESUserEntity, Integer>{
	
	public Optional<IESUserEntity> findByuserId(Integer userId);
	
	public IESUserEntity findByUserEmail(String userEmail);
	
	public IESUserEntity findByUserEmailAndUserPazzword(String userEmail, String userPazzword);
	


}
