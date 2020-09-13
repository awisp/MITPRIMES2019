package com.mitprimes.problem1;

import com.mitprimes.Student;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class DataGenerator {

    //===========//
    // CONSTANTS //
    //===========//

    final static String BIG_INPUT = "biginput-problem1.txt";

    public static void main(String[] args) {

        try {
            generateData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void generateData() throws IOException {


        FileWriter writer = new FileWriter(BIG_INPUT);

        int length = 10000000;

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
