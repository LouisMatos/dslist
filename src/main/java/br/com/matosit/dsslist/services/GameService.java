package br.com.matosit.dsslist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.matosit.dsslist.dto.GameDTO;
import br.com.matosit.dsslist.dto.GameMinDTO;
import br.com.matosit.dsslist.projections.GameMinProjection;
import br.com.matosit.dsslist.repositories.GameRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GameService {

	@Autowired
	private GameRepository gameRepository;

	@Transactional(readOnly = true)
	public List<GameMinDTO> findAll() {
		log.info("Buscando todos os Jogos!!");
		return gameRepository.findAll().stream().map(x -> new GameMinDTO(x)).toList();
	}

	@Transactional(readOnly = true)
	public GameDTO findById(Long id) {
		return new GameDTO(gameRepository.findById(id).get());
	}
	
	
	@Transactional(readOnly = true)
	public List<GameMinDTO> findByGameList(Long listId) {
		List<GameMinProjection> games = gameRepository.searchByList(listId);
		return games.stream().map(GameMinDTO::new).toList();
	}
}
