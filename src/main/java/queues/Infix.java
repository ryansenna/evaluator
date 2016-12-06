/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queues;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryan Sena
 */
public class Infix {
    private List<String> queue;
    private String number;
    private String symbol;
    
    public Infix(){
        super();
        queue = new ArrayList<>();
    }
}
