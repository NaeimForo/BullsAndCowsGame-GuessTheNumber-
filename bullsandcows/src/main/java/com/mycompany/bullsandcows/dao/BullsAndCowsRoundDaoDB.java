/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.dao;

import com.mycompany.bullsandcows.entity.Round;
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
public class BullsAndCowsRoundDaoDB implements BullsAndCowsRoundDao {
        
    
    @Autowired
    JdbcTemplate jdbc;
    
    
    @Override
    public Round getRoundById(int roundId) {
        try{
            final String SELECT_ROUND_BY_ID = "SELECT * FROM round WHERE round_Id = ?";
            return jdbc.queryForObject(SELECT_ROUND_BY_ID, new RoundMapper(), roundId);
        }catch(DataAccessException ex){
            return null;
        }
    }
    @Transactional
    @Override
    public Round addRound(Round round) {
        final String INSERT_ROUND = "INSERT INTO round (game_Id ,guess, result)"
                + "VALUES (?,?,?)";
        jdbc.update(INSERT_ROUND, round.getGameId(), round.getGuess(), round.getResult());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        round.setRoundId(newId);
        return getRoundById(newId);
    }

    @Override
    public List<Round> getAllRoundsByGameId(int gameId) {
     
        final String sql = "SELECT * FROM round "
                    + "WHERE game_Id =? ORDER BY guess_time";
        return jdbc.query(sql, new RoundMapper() , gameId);
 
    }

    @Override
    public void deleteRoundById(int roundId) {
        final String DELETE_ROUND_BY_ID = "DELETE FROM round WHERE round_Id = ?";
        jdbc.update(DELETE_ROUND_BY_ID, roundId);
    }
    
    public static final class RoundMapper implements RowMapper<Round>{

        @Override
        public Round mapRow(ResultSet rs, int index) throws SQLException {
            
            Round round = new Round();
            round.setRoundId(rs.getInt("round_Id"));
            round.setGameId(rs.getInt("game_Id"));
            round.setGuess(rs.getString("guess"));
            round.setGuess_time(rs.getTimestamp("guess_Time").toLocalDateTime());
            String result= rs.getString("result");
            if (result == null)
                result= "inprogress";
            round.setResult(result);
            return round; 
            
            
        }
        
    
}
    
}
