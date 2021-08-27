package com.miguel.examen.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.miguel.examen.models.Network;
import com.miguel.examen.models.TvShow;
import com.miguel.examen.repositories.NetworkRepository;
import com.miguel.examen.repositories.TvShowRepository;

@Service
public class TvShowService {
	private final TvShowRepository tvShowRepository;
	private final NetworkRepository networkRepository;

	public TvShowService(TvShowRepository tvShowRepository, NetworkRepository networkRepository) {
		this.tvShowRepository = tvShowRepository;
		this.networkRepository = networkRepository;

	}

	// Crea tvShow
	public void createTvShow(TvShow tvShow) {
		tvShowRepository.save(tvShow);
	}

	// obtiene todos los tv show
	public List<TvShow> findAllTvShows() {
		return tvShowRepository.findAll();
	}

	// busca tvshow by id

	public TvShow findTvShowById(Long id) {
		Optional<TvShow> findTvShow = tvShowRepository.findById(id);
		if (findTvShow.isPresent()) {
			return findTvShow.get();
		} else {
			return null;
		}
	}

	// Crea Network
	public void createNetwork(Network network) {
		networkRepository.save(network);
	}

	// busca todas las Network
	public List<Network> findAllNetwork() {
		return networkRepository.findAll();
	}

	// busca network by Id

	public Network findNetworkById(Long id) {
		Optional<Network> findNetwork = networkRepository.findById(id);
		if (findNetwork.isPresent()) {
			return findNetwork.get();
		} else {
			return null;
		}
	}

}
