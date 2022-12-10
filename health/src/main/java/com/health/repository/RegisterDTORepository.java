package com.health.repository;


import java.util.List;
import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;


import com.health.entity.HealthProVO;
import com.health.entity.HealthUseVO;
import com.health.entity.RegisterDTO;

public interface RegisterDTORepository extends JpaRepository<RegisterDTO, Integer> {
	
	Long countBypId(HealthProVO pId);
	
	@Query(value ="select * from register where pid = ?1 and healthUseNo = ?2", nativeQuery = true)
	Optional<RegisterDTO> findByPidAndHealthUseNo(int pid, int healthUseNo);
	
	@Query(value ="select * from register where healthUseNo = ?1", nativeQuery = true)
	List<RegisterDTO> findByhealthUseNo(int healthUseNo);

}
