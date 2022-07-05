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
public class InvalidGameException extends Exception{
    public InvalidGameException(String message) {
        super(message);
    }

    public InvalidGameException(String message, Throwable cause) {
        super(message, cause);
    }
}