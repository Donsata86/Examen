package com.miguel.examen.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.miguel.examen.models.TvShow;

@Repository
public interface TvShowRepository extends CrudRepository<TvShow, Long>{

	List<TvShow> findAll();

}
