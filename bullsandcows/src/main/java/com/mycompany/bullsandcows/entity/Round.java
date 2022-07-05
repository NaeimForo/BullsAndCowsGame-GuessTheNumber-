/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author naeim
 */
public class Round {
    
    private int roundId;
    private int gameId;
    private LocalDateTime guess_time;
    private String guess;
    private String result; 

    public Round() {
    }
    

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public LocalDateTime getGuess_time() {
        return guess_time;
    }

    public void setGuess_time(LocalDateTime guess_time) {
        this.guess_time = guess_time;
    }

    public String getGuess() {
        return guess;
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.roundId;
        hash = 97 * hash + this.gameId;
        hash = 97 * hash + Objects.hashCode(this.guess_time);
        hash = 97 * hash + Objects.hashCode(this.guess);
        hash = 97 * hash + Objects.hashCode(this.result);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Round other = (Round) obj;
        if (this.roundId != other.roundId) {
            return false;
        }
        if (this.gameId != other.gameId) {
            return false;
        }
        if (!Objects.equals(this.guess, other.guess)) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.guess_time, other.guess_time)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Round{" + "roundId=" + roundId + ", gameId=" + gameId + ", guess_time=" + guess_time + ", guess=" + guess + ", result=" + result + '}';
    }
    
    
    
}
