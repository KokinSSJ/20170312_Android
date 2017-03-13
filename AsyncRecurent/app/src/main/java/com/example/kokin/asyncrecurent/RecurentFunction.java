package com.example.kokin.asyncrecurent;

/**
 * Created by Kokin on 2017-03-12.
 */
public class RecurentFunction {

    public static Integer function(Integer number){
        if(number<=2){
            return 2;
        }

        return 3*function(number-1)+2*function(number-2);

    }
}
