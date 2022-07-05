/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.servicelayer;

import com.mycompany.bullsandcows.dao.BullsAndCowsGameDaoDB;
import com.mycompany.bullsandcows.dao.BullsAndCowsRoundDaoDB;
import com.mycompany.bullsandcows.entity.Game;
import com.mycompany.bullsandcows.entity.Round;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author naeim
 */

@Service
public class ServiceLayer {
    
    @Autowired
    BullsAndCowsGameDaoDB gameDaoDB;
    
    @Autowired
    BullsAndCowsRoundDaoDB roundDaoDB;
    
    
    public Game addGame (Game game){
        return gameDaoDB.addGame(game);
    }
    
    public Game getGameById(int gameId) {
        Game game = gameDaoDB.getGameById(gameId);
        
        if (game != null  && !game.isStatus()){
            
            game.setAnswer("****");
        }
        return game;
    }
    
    public int newGame(){
        Game game = new Game();
        game.setAnswer(rGenAnswer());
        game = gameDaoDB.addGame(game);
        
        return game.getGameId();
    }
    
    private String rGenAnswer(){
        List<Integer> l= Arrays.asList(1,2,3,4,5,6,7,8,9);
        Collections.shuffle(l);
        Integer result= 1000*l.get(0) + 100*l.get(1) + 10*l.get(2) + l.get(3);
        
        return result.toString();
        
    }
    public Round makeGuess (Round round){
        String answer = gameDaoDB.getGameById(round.getGameId()).getAnswer();
        String guess = round.getGuess();
        String result = compareResult(guess, answer);
        round.setResult(result);
        
        if (guess.equals(answer)){
            Game game =getGameById(round.getGameId());
            game.setStatus(true);
            gameDaoDB.updateGame(game);
        }
        return roundDaoDB.addRound(round);
    }
    
    public String compareResult(String guess, String answer) {
        char[] guessChars = guess.toCharArray();
        char[] answerChars = answer.toCharArray();
        int exactMatch = 0;
        int partialMatch = 0;
        
        for (int i = 0; i < guessChars.length; i++) {

            if (answer.indexOf(guessChars[i]) > -1) {
                if (guessChars[i] == answerChars[i]) {
                    exactMatch++;
                } else {
                    partialMatch++;
                }
            }
        }
        
        String result = "e:" + exactMatch + ":p:" + partialMatch;
        
        return result;
    }
    
    public List <Game> getAllGames(){
        List<Game> games = gameDaoDB.getAllGames();
        
        for (Game game : games){
            if(!game.isStatus()){
                game.setAnswer("****");
            }
        }
        return games; 
    }
    
    public List<Round> getAllRoundsByGameId(int gameId){
        return roundDaoDB.getAllRoundsByGameId(gameId);
    }
    
}
