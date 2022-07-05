/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.entity;

import java.util.Objects;

/**
 *
 * @author naeim
 */
public class Game {
    
    private int gameId;
    private String answer;
    private boolean status; 
    
      public Game() {
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }



    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
        @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + this.gameId;
        hash = 11 * hash + Objects.hashCode(this.answer);
        hash = 11 * hash + (this.status ? 1 : 0);
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
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", answer=" + answer + ", status=" + status + '}';
    }


  
    
    
}
