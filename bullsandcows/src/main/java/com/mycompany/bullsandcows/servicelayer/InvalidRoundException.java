/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.bullsandcows.servicelayer;

/**
 *
 * @author naeim
 */
public class InvalidRoundException extends Exception{
    public InvalidRoundException(String message) {
        super(message);
    }

    public InvalidRoundException(String message, Throwable cause) {
        super(message, cause);
    }
}