package br.ufrn.shopminer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.ufrn.shopminer.model.ExtendedSite;

@Repository
public interface ExtendedSiteRepository extends JpaRepository<ExtendedSite, Integer>{

	ExtendedSite findById(int id);
	
	@Query( value="select * from extended_site e where e.config = :cid", nativeQuery = true)
	List<ExtendedSite> findAllByConfig(@Param("cid") Integer cid);
	
}
