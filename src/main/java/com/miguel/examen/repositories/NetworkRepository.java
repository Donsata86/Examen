package com.miguel.examen.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.miguel.examen.models.Network;

@Repository
public interface NetworkRepository extends CrudRepository<Network, Long>{
	List<Network> findAll();

}
