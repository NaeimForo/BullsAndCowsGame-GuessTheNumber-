/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.controller;

import com.mycompany.bullsandcows.dao.BullsAndCowsGameDao;
import com.mycompany.bullsandcows.dao.BullsAndCowsRoundDao;
import com.mycompany.bullsandcows.entity.Game;
import com.mycompany.bullsandcows.entity.Round;
import com.mycompany.bullsandcows.servicelayer.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

        
        

/**
 *
 * @author naeim
 */
@RestController
@RequestMapping("/guessthenumber")
public class Controller {
    
    
    @Autowired
    ServiceLayer service;
   
    @Autowired 
    BullsAndCowsGameDao gameDao;
    
    @Autowired
    BullsAndCowsRoundDao roundDao;
    
    
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int startGame(){
        return  service.newGame();
    }
    
    @PostMapping("/guess")
    public ResponseEntity makeGuess(@RequestBody Round round){
        Game game = service.getGameById(round.getGameId());
         
        if (game == null) {
            return new ResponseEntity("Game not found.", HttpStatus.NOT_FOUND);
        }
        roundDao.addRound(round);
        return ResponseEntity.ok(game);
       
    }
    
    @GetMapping("game")
    public List<Game> getAllGames(){
        return service.getAllGames();
    }
    
    @GetMapping("/game/{game_Id}")
    public ResponseEntity getGameById (@PathVariable("game_Id") int  gameId){
        Game result = service.getGameById(gameId);
        if (result == null){
            return new ResponseEntity("Game not Found." ,HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    @GetMapping("/rounds/{game_Id}")
    public ResponseEntity getAllRoundsByGameId (@PathVariable ("game_Id") int gameId){
        Game game = service.getGameById(gameId);
       
        if(game == null){
            return new ResponseEntity("Game does Not exist" ,HttpStatus.NOT_FOUND );
        }
       
        
        List<Round> rounds = service.getAllRoundsByGameId(gameId);
        
        if(rounds == null || rounds.isEmpty()){
            return new ResponseEntity("Round does Not exist" ,HttpStatus.NOT_FOUND );
        }
         return ResponseEntity.ok(rounds);
    }
}
    
       
