/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.entity.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author naeim
 */
@Repository
public class BullsAndCowsGameDaoDB implements BullsAndCowsGameDao {
    
    @Autowired
    JdbcTemplate jdbc;


    
    
    @Transactional
    @Override
    public Game addGame(Game game) {
        final String INSERT_GAME = "INSERT INTO game (answer) VALUES (?)";
        
        jdbc.update(INSERT_GAME, game.getAnswer());
        
        int newGameId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newGameId);
        return game;
    }

    @Override
    public Game getGameById(int gameId) {
        try {
            final String SELECT_GAME_BY_ID = "SELECT * FROM game WHERE game_Id = ?";
            return jdbc.queryForObject(SELECT_GAME_BY_ID, new GameMapper(), gameId);
        }catch(DataAccessException ex){
            return null;
        }
         
    }

    @Override
    public void updateGame(Game game) {
        final String UPDATE_GAME = "UPDATE game SET status = ?  WHERE game_Id = ?";
        jdbc.update(UPDATE_GAME, game.isStatus(), game.getGameId());
                
    }

    @Override
    public List<Game> getAllGames() {
        final String SELECT_ALL_GAMES = "SELECT * FROM game";
        return jdbc.query(SELECT_ALL_GAMES, new GameMapper());
        
    }

    @Override
    public void deleteGameById(int gameId) {
        final String DELETE_GAME_BY_ID = "DELETE FROM game WHERE game_Id =?";
        jdbc.update(DELETE_GAME_BY_ID, gameId);
    }

    
    public static final class GameMapper implements RowMapper<Game>{

        @Override
        public Game mapRow(ResultSet rs, int rowNum) throws SQLException {
            Game game = new Game();
            
            game.setGameId(rs.getInt("game_Id"));
            game.setAnswer(rs.getString("answer"));
            game.setStatus(rs.getBoolean("status"));
            return game; 
            
        }
    
}
}
