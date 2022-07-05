/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.entity.Round;
import java.util.List;

/**
 *
 * @author naeim
 */
public interface BullsAndCowsRoundDao {
    
    Round getRoundById (int roundId);
    Round addRound (Round round); 
    List<Round> getAllRoundsByGameId (int gameId);
    void deleteRoundById(int roundId);

    
}
