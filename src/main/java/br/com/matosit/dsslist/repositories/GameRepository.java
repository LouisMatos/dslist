package br.com.matosit.dsslist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.matosit.dsslist.entities.Game;

@Repository
public interface GameRepository extends JpaRepository<Game,Long>{

}
