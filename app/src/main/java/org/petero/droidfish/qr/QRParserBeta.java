package org.petero.droidfish.qr;

import java.util.HashMap;

/**
 * Created by lordvulkan on 7/03/17.
 */

public class QRParserBeta {
    public static final HashMap<String,String> map;
    static{
        map = new HashMap<>();
        //espacio
        map.put(" ","00000");
        //Digitos del 1 al 8
        map.put("1","00001");
        map.put("2","00010");
        map.put("3","00011");
        map.put("4","00100");
        map.put("5","00101");
        map.put("6","00110");
        map.put("7","00111");
        map.put("8","01000");
        //Posiciones de la A a la H
        map.put("A","01010");
        map.put("B","01011");
        map.put("C","01100");
        map.put("D","01101");
        map.put("E","01110");
        map.put("F","01111");
        map.put("G","10000");
        map.put("H","10001");
        //Piezas K,Q,R,L(B),N
        map.put("K","10010");
        map.put("Q","10011");
        map.put("R","10100");
        map.put("L","10101");
        map.put("N","10110");
        //Símbolos x,+,*,#, enroques y resultados
        map.put("X","10111");
        map.put("+","11000");
        map.put("*","11001");
        map.put(":","11010");
        map.put("V","11011");
        map.put("W","11100");
        map.put("S","11101");
        map.put("Y","11110");
        map.put("Z","11111");
    }
}
