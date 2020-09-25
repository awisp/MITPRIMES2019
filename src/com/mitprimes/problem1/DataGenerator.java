/**
 *  This class fills a text file with a set amount of students with randomly generated parameters.
 *
 *  To run, set the variable BIG_INPUT to the name of the file that you would like to fill.
 *  Additionally, the number of student records can be changed by changing the variable LENGTH.
 *  Then, run the main method of this file.
 *
 * @problem 1
 * @file-name DataGenerator.java
 * @programming-language Java openjdk 14.0.2
 * @development-framework IntelliJ
 * @platform Windows 10
 */


package com.mitprimes.problem1;

import com.mitprimes.Student;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class DataGenerator {

    //===========//
    // CONSTANTS //
    //===========//

    final static String BIG_INPUT = "input-problem1.txt";
    final static int LENGTH = 1000000;

    public static void main(String[] args) {

        try {
            generateData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void generateData() throws IOException {

//        URL url = DataGenerator.class.getClassLoader().getResource(BIG_INPUT);
//        FileWriter writer = new FileWriter(url.getFile());
        FileWriter writer = new FileWriter(BIG_INPUT);

        int length = LENGTH;

        writer.write(Integer.toString(length));

        writer.write(System.getProperty("line.separator"));

        for(int i = 0; i < length; i++) {

            String firstName = getRandomName();
            String lastName = getRandomName();
            float gpa = generateRandomGPA();
            int gradYear = generateRandomGradYear();

            Student billy = new Student(lastName, firstName, gpa, gradYear);
            writer.write(billy.toString());
            writer.write(System.getProperty("line.separator"));
        }

        writer.close();
    }

    private static int generateRandomGradYear() {

        Random r = new Random();
        int min = 2018;
        int max = 2022;

        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        return randomNum;
    }

    private static float generateRandomGPA() {
        DecimalFormat df = new DecimalFormat("#.##");

        Random r = new Random();
        float min = 1;
        float max = 4;

        float result = min + r.nextFloat() * (max - min);
        return Float.parseFloat(df.format(result));
    }

    private static String getRandomName() {
        String chars = "abcdefghijklmnopqrstuvwxyz1ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder name = new StringBuilder();
        Random rnd = new Random();

        int randomLength = ThreadLocalRandom.current().nextInt(4, 12 + 1);

        while (name.length() < randomLength) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            name.append(chars.charAt(index));
        }
        String toString = name.toString();
        return toString;

    }

}
