package com.jgmartinez.adventofcode2021;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day3 {

    public static void main(String[] args) throws Exception{

        String[][] measurements = getListOfElements("resources/day3_input.txt");

        List<char[]> rates = getRates(measurements);

        System.out.println("Challenge 1: " + Long.parseLong(new String(rates.get(0)),2) * Long.parseLong(new String(rates.get(1)),2));
        System.out.println("Challenge 2: " + getLifeSupportRate(measurements));
    }


    public static long getLifeSupportRate(String[][] measurements){

        String [][] oxygen = calculateLSRate(measurements, getRates(measurements).get(0), 0, 0);
        String [][] co2 = calculateLSRate(measurements, getRates(measurements).get(1), 1, 0);

        return Long.parseLong(matrixToString(oxygen), 2)*Long.parseLong(matrixToString(co2), 2);
    }

    private static String[][] calculateLSRate(String[][] measurements, char[] rate, int rateType, int index){

        List<String[]> newMeasurements = new ArrayList<>();
        for (String[] measurement : measurements) {
            if (Integer.parseInt(measurement[index]) == rate[index] - '0')
                newMeasurements.add(measurement);
        }

        if (index<rate.length && newMeasurements.size()>1){
            String [][] newMeasurementsMatrix = listToMatrix(newMeasurements);
            return calculateLSRate(newMeasurementsMatrix, getRates(newMeasurementsMatrix).get(rateType), rateType, ++index);
        }
        return listToMatrix(newMeasurements);
    }

    public static List<char[]> getRates (String[][] measurements){
        char[] gamma = new char[measurements[0].length], epsilon = new char[measurements[0].length];
        int aux;

        List<char[]> result = new ArrayList<>();
        for (int i=0; i < measurements[0].length; i++){
            aux = 0;
            for (String[] measurement : measurements) {
                aux += Integer.parseInt(measurement[i]);
            }
            if (aux >= measurements.length/2.0) {
                gamma[i] = '1';
                epsilon[i] = '0';
            }
            else {
                gamma[i] = '0';
                epsilon[i] = '1';
            }
        }
        result.add(gamma);
        result.add(epsilon);
        return result;
    }

    private static String matrixToString (String[][] matrix){
        StringBuilder result = new StringBuilder();
        for (int i=0; i< matrix[0].length;i++){
            result.append(matrix[0][i]);
        }
        return result.toString();
    }

    private static String[][] listToMatrix (List<String[]> list){
        String[][] result = new String[list.size()][list.get(0).length];
        for (int i=0; i<list.size(); i++){
            System.arraycopy(list.get(i), 0, result[i], 0, list.get(i).length);
        }
        return result;
    }

    private static String[][] getListOfElements (String file) throws IOException {
        String[][] result;
        try (Stream<String> lines = Files.lines(Paths.get(file))) {
            result = lines.map(l-> l.split("")).toArray(String[][]::new);
        }
        return result;
    }
}