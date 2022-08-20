package com.mveril.quiz.Controller.api;

import com.mveril.quiz.business.*;
import com.mveril.quiz.business.service.PlayerStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("api/store/players")
public class PlayerStoreController {
    @Autowired
    PlayerStoreService playerStore;

    @GetMapping()
    public List<Player> list(){
        return playerStore.getPlayersOrderByScore();
    }

    @DeleteMapping("{id}")
    public ResponseEntity remove(@PathParam("id") long playerId){
        if(playerStore.removePlayer(playerId)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping()
    public void add(@RequestBody Player player){
        playerStore.add(player);
    }

    @GetMapping("{id}")
    public ResponseEntity<Player> get(@PathParam("id") long id){
        var o = playerStore.getPlayerById(id);
        if(o.isPresent()){
            return ResponseEntity.ok(o.get());
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity put(@PathParam("id") long id, @RequestBody Player player){
        if(player.getId() != id){
            return ResponseEntity.badRequest().build();
        }
        if(playerStore.update(player)){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

