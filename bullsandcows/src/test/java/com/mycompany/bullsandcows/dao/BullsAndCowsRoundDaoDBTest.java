/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.TestApplicationConfiguration;
import com.mycompany.bullsandcows.entity.Game;
import com.mycompany.bullsandcows.entity.Round;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




/**
 *
 * @author naeim
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class BullsAndCowsRoundDaoDBTest {
    @Autowired
    BullsAndCowsRoundDao roundDao;
   
    @Autowired
    BullsAndCowsGameDao gameDao;
    
 
    
    public BullsAndCowsRoundDaoDBTest() {
    }
    
    
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getRoundById method, of class BullsAndCowsRoundDaoDB.
     */
    @Test
   
    public void testAddGetAllRounds() {
        
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(false);
        gameDao.addGame(game);
        
        
        Round round = new Round();
        
        round.setGameId(game.getGameId());
        round.setGuess("4321");
        round.setResult("e:0:p:4");
        Round testRound = roundDao.addRound(round);
        Round daoRound = roundDao.getRoundById(testRound.getRoundId());
       

        
        Round round2 = new Round();
        
        round2.setGameId(game.getGameId());
        round2.setGuess("5678");
        round2.setResult("e:0:p:0");
        Round testRound2 =roundDao.addRound(round2); 
        Round daoRound2 = roundDao.getRoundById(testRound2.getRoundId());
        
      
        
        List<Round> roundList = roundDao.getAllRoundsByGameId(game.getGameId());
        
        assertEquals(2, roundList.size());
        
        assertEquals(testRound, daoRound);
        assertEquals(testRound2, daoRound2);
        

      
        
        
        
        
        
        
    }
       
                
   }
