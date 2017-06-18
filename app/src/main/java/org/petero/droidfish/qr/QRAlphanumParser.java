package org.petero.droidfish.qr;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

/**
 * Created by lordvulkan on 7/03/17.
 */

public class QRAlphanumParser {

    public static String parseToPGN(String string){
        String result = string;
        if (!result.contains("[")) {
            String resultAux = result;

            resultAux = resultAux.toLowerCase();
            resultAux = resultAux.replaceAll("z", "1/2-1/2");
            resultAux = resultAux.replaceAll("y", "0-1");
            resultAux = resultAux.replaceAll("s", "1-0");
            resultAux = resultAux.replaceAll("w", "O-O");
            resultAux = resultAux.replaceAll("v", "O-O-O");
            resultAux = resultAux.replaceAll("l", "B");
            resultAux = resultAux.replaceAll("%", "=");
            resultAux = resultAux.replaceAll(":", "#");
            resultAux = resultAux.replaceAll("k", "K");
            resultAux = resultAux.replaceAll("q", "Q");
            resultAux = resultAux.replaceAll("r", "R");
            resultAux = resultAux.replaceAll("n", "N");

            result = "";

            String[] resultArray = resultAux.split("\\s+");
            for (int i = 0; i < resultArray.length; i++) {
                if (i % 2 == 0) {
                    result += " " + (i / 2 + 1) + ".";
                }
                result += " " + resultArray[i];
            }
            result = result.replaceFirst(" ", "");
        }
        return result;
    }

    public static String parseToAlphanum(String string){
        Pattern pattern = Pattern.compile("\\[.*]");
        String result = pattern.matcher(string).replaceAll("");
        result = result.replaceAll("\\{.*\\}","");
        result = result.replaceAll("O-O-O","V");
        result = result.replaceAll("O-O","W");
        result = result.replaceAll("1-0","S");
        result = result.replaceAll("0-1","Y");
        result = result.replaceAll("1/2-1/2","Z");
        result = result.replaceAll("B","L");
        result = result.replaceAll("=","%");
        result = result.replaceAll("#",":");
        result = result.toUpperCase();

        pattern = Pattern.compile("\\d+\\.\\s+");
        result = pattern.matcher(result).replaceAll("");
        result = result.replaceAll("\\n","");

        return  result;
    }
}
