/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategy;

import beans.Operator;
import java.util.Comparator;

/**
 *
 * @author 1333612
 */
public class OperatorCompare implements Comparator<Operator>{

    @Override
    public int compare(Operator o1, Operator o2) {
        return o1.compareTo(o2);
    }
    
}
