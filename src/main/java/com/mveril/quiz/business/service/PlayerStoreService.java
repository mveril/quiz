package com.mveril.quiz.business.service;


import com.mveril.quiz.business.Answer;
import com.mveril.quiz.business.Player;
import com.mveril.quiz.business.PlayerNotExistsException;
import com.mveril.quiz.business.Question;
import com.mveril.quiz.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerStoreService {

    @Autowired
    PlayerRepository playerRepository;

    public void addPlayer(Player player){
        playerRepository.save(player);
    }
    public List<Player> getPlayersOrderByScore(){
        return playerRepository.findAll(Sort.by("score").descending());
    }

    public void incrementScore(long id) throws PlayerNotExistsException {
        var oPlayer = playerRepository.findById(id);
        if (oPlayer.isPresent()){
            var player =oPlayer.get();
            player.setScore(player.getScore()+1);
            playerRepository.save(player);
        } else {
            throw new  PlayerNotExistsException(id);
        }

    }

    public boolean removePlayer(long id) {
        var result = playerRepository.existsById(id);
        if(result){
            playerRepository.deleteById(id);
        }
        return result;
    }

    public Optional<Player> getPlayerById(long id) {
        return playerRepository.findById(id);
    }

    public void add(Player player) {
        if (!playerRepository.existsById(player.getId())){
            playerRepository.save(player);
        }
    }

    public boolean update(Player player) {
        var result = playerRepository.existsById(player.getId());
        if (result){
            playerRepository.save(player);
        }
        return result;
    }
}
