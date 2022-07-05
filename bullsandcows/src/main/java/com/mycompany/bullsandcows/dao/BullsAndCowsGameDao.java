/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.entity.Game;
import java.util.List;

/**
 *
 * @author naeim
 */
public interface BullsAndCowsGameDao {
    
    Game addGame (Game game);
    
    Game getGameById(int gameId);
    
    void updateGame (Game game);
    
   void deleteGameById(int gameId);

    
    List <Game> getAllGames();
    
}
