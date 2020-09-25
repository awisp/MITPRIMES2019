/**
 *  This class sorts a text file according to the graduation years of students.
 *
 *  To run, run the main method of this class. Enter the input and output file names with the full path.
 *
 * @problem 1
 * @file-name CountingSort.java
 * @programming-language Java openjdk 14.0.2
 * @development-framework IntelliJ
 * @platform Windows 10
 */

package com.mitprimes.problem1;

import com.mitprimes.Student;

import java.io.*;
import java.util.Scanner;

public class CountingSortProblem1 {

    //===========//
    // CONSTANTS //
    //===========//

    final static String INPUT_FILE = "input-problem1.txt";
    final static String OUTPUT_FILE = "output-problem1.txt";

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter input file name: ");
        String inputFile = input.nextLine();
        System.out.print("Enter output file name: ");
        String outputFile = input.nextLine();

        if(inputFile.isEmpty()) inputFile = INPUT_FILE;
        if(outputFile.isEmpty()) outputFile = OUTPUT_FILE;

        File inputF = new File(inputFile);
        File outputF = new File(outputFile);

        if(!inputF.exists()){
            System.out.println("The input file " + inputFile + " does not exist.");
            main(args);
        }
        if(!outputF.exists()){
            System.out.println("The output file " + outputFile + " does not exist.");
            main(args);
        }

        long time;

        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(inputF));
            String line = reader.readLine();

            int length = Integer.parseInt(line);
            Student[] inputArray = new Student[length];

            for(int i = 0; i < length; i++) {
                line = reader.readLine();

                String[] temp = line.split("\\s+");

                if(temp.length != 4) {
                    System.out.println("Invalid file format; please check your file and try again.");
                    System.exit(1);
                }

                temp[0] = temp[0].replace(",", "");

                inputArray[i] = new Student(temp[0], temp[1],
                        Float.parseFloat(temp[2]), Integer.parseInt(temp[3]));
            }
            reader.close();




            final long startTime = System.currentTimeMillis();

            Student[] sortedArray = countingSort(inputArray);

            final long endTime = System.currentTimeMillis();

            time = endTime - startTime;
            System.out.println("Total execution time: " + (time));



            FileWriter writer = new FileWriter(outputF);

            writer.write(Integer.toString(length));
            writer.write(System.getProperty("line.separator"));

            for(Student a : sortedArray) {
                writer.write(a.toString());
                writer.write(System.getProperty("line.separator"));
            }

            writer.write(Long.toString(time));

            writer.close();

        } catch (IOException e) {
            System.out.println("Invalid file format; please check your file and try again.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("Invalid file format; please check your file and try again.");
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

        for(Student student : inputArray) count[student.getGradYear() - 2018]++;

        for(int i = 1; i <= range-1; i++)
            count[i] += count[i-1];

        for(int i = length-1; i >= 0; i--) {
            output[count[inputArray[i].getGradYear()-2018]-1] = inputArray[i];
            --count[inputArray[i].getGradYear()-2018];
        }


        return output;
    }
}
