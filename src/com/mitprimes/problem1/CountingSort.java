package com.mitprimes.problem1;

import com.mitprimes.Student;

import java.io.*;

public class CountingSort {

    //===========
    // CONSTANTS
    //===========

    final static String INPUT_FILE = "input-problem1.txt";
    final static String OUTPUT_FILE = "output-problem1.txt";

    public static void main(String[] args) {

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(INPUT_FILE));
            String line = reader.readLine();

            int length = Integer.parseInt(line);
            Student[] inputArray = new Student[length];

            for(int i = 0; i < length; i++) {
                line = reader.readLine();

                String[] temp = line.split("\\s+");
                temp[0] = temp[0].replace(",", "");

                System.out.println(line);
                inputArray[i] = new Student(temp[0], temp[1],
                        Float.parseFloat(temp[2]), Integer.parseInt(temp[3]));
            }
            reader.close();

            Student[] sortedArray = countingSort(inputArray);

            FileWriter writer = new FileWriter(OUTPUT_FILE);

            for(Student a : sortedArray) {
                writer.write(a.toString());
                writer.write(System.getProperty("line.separator"));
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private static Student[] countingSort(Student[] inputArray) {

        int length = inputArray.length;
        int range = 5;

        Student[] output = new Student[length];

        int[] count = new int[range];

        for(int i = 0; i < range; i++)
            count[i] = 0;

        for(int i = 0; i < length; i++)
            count[inputArray[i].getGradYear()-2018]++;

        for(int i = 1; i <= range-1; i++)
            count[i] += count[i-1];

        for(int i = length-1; i >= 0; i--) {
            output[count[inputArray[i].getGradYear()-2018]-1] = inputArray[i];
            --count[inputArray[i].getGradYear()-2018];
        }

        return output;
    }
}
