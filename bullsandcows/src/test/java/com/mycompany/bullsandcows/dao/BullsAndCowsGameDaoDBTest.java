/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.TestApplicationConfiguration;
import com.mycompany.bullsandcows.entity.Game;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;


/**
 *
 * @author naeim
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class BullsAndCowsGameDaoDBTest {
    
    @Autowired
    BullsAndCowsGameDao gameDao;
    
    @Autowired
    JdbcTemplate jdbc;
  

    
    @BeforeEach
   public void setUp() {
      jdbc.update("DELETE FROM round;");
      jdbc.update("DELETE FROM game;");
   }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addGame method, of class BullsAndCowsGameDaoDB.
     */

    @Test
    public void testAddGetGame() {
        Game game = new Game();
        game.setAnswer("1234");
        game.setStatus(false);
        game = gameDao.addGame(game);

        Game fromDao = gameDao.getGameById(game.getGameId());

        assertEquals(game, fromDao);
    }
    
    
    @Test
    public void testGetAllGames (){
        Game game1 = new Game();
        game1.setAnswer("1234");
        game1.setStatus(false);
        gameDao.addGame(game1);
            
        Game game2 = new Game();
        game2.setAnswer("5678");
        game2.setStatus(false);
        gameDao.addGame(game2);
        
        List<Game> games = gameDao.getAllGames();
        assertEquals(2, games.size());
        assertTrue(games.contains(game1));
        assertTrue(games.contains(game2));    
    }
    
    @Test 
    public void testUpdateGame(){
        Game game = new Game();
        game.setAnswer("9876");
        game.setStatus(false);
        game = gameDao.addGame(game);
        
        Game fromDao = gameDao.getGameById(game.getGameId());
        
        assertEquals(game, fromDao);
        game.setStatus(true);
        gameDao.updateGame(game);
        assertNotEquals(game,fromDao);
        fromDao = gameDao.getGameById(game.getGameId());
        assertEquals(game, fromDao);
    }
        
        
        
    


}
