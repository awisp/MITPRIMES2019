/**
 *  This class sorts a text file according to the graduation years of students. Additionally, it returns a Map with
 *  counts of each of the gradYears.
 *
 *  To run, run the main method of this class. Enter the input and output file names with the full path.
 *
 *  This code uses CountingSort to first organize the students by graduation year, then uses MergeSort to sort the
 *  students according to their GPA in their respective years.
 *
 *  This Big-O efficiency of this program is O(n log n). CountingSort's efficiency is O(n), and MergeSort's efficiency
 *  is O(n log n). O(n log n) is dominant, so that is the efficiency of this program. This for best, worst, and average
 *  cases, as CountingSort is always O(n) and MergeSort is O(n log n) for all cases as well.
 *
 *  The memory used in this program is 19.5N, where N is the amount of memory used for the input data. 1N is used to
 *  store the data read from the file. CountingSort uses 1N of memory to make an output array, and MergeSort uses 17.5N
 *  of memory.
 *
 *  The approaches I tried were merge sort and timsort. I tried using merge sort first and then doing count sort and
 *  count sort first and then merge sort and found that doing count sort and then merge sort was the fastest approach.
 *
 *
 * @problem 2
 * @file-name CountingSortProblem2.java
 * @programming-language Java openjdk 14.0.2
 * @development-framework IntelliJ
 * @platform Windows 10
 */

package com.mitprimes.problem2;

import com.mitprimes.Student;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CountingSortProblem2 {

    //===========//
    // CONSTANTS //
    //===========//

    final static String INPUT_FILE = "input-problem1.txt";
    final static String OUTPUT_FILE = "output-problem1.txt";

    public static void main(String[] args) {

        //=================//
        // RETRIEVING FILE //
        //=================//

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

            //===========//
            //  READING  //
            //===========//

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


            //===========//
            //  SORTING  //
            //===========//

            final long startTime = System.currentTimeMillis();

            //

            Student[] sortedArray = countingMerge(inputArray);
//            Student[] sortedArray = timCounting(inputArray, length);
//            Student[] sortedArray = mergeCounting(inputArray, length);

            //113, 150, 139 - timCounting - 1034, 990, 1002
            //78, 90, 102 - mergeCounting - 850, 863, 820
            //62, 63, 71 - countingMerge - 655, 617, 652

            final long endTime = System.currentTimeMillis();

            time = endTime - startTime;
            System.out.println("Total execution time: " + (time));


            //===========//
            //  WRITING  //
            //===========//

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

    private static Student[] timCounting(Student[] inputArray, int length) {
        TimSort sort = new TimSort();
        sort.timSort(inputArray, length);

        Student[] sortedArray = countingSort(inputArray);

        return sortedArray;
    }

    private static Student[] mergeCounting(Student[] inputArray, int length) {
        GpaMergeSort mergeSort = new GpaMergeSort();
        mergeSort.sort(inputArray, 0, length-1);

        Student[] sortedArray = countingSort(inputArray);

        return sortedArray;
    }

    private static Student[] countingMerge(Student[] inputArray) {

        Map<Integer, Integer> countMap = new HashMap<Integer, Integer>();
        Student[] sortedArray = countingSort(inputArray, countMap);

        int count[] = new int[countMap.size()];

        for(int i = 0; i < 5; i++)
            count[i] = countMap.get(i+2018);

        for(int i = 1; i < countMap.size(); i++)
            count[i] += count[i-1];


        for(int i = 0; i < count.length; i++) {
            GpaMergeSort mergeSort = new GpaMergeSort();
            if(i == 0)
                mergeSort.sort(sortedArray, 0, count[i]-1);
            else
                mergeSort.sort(sortedArray, count[i-1], count[i]-1);
        }

        return sortedArray;
    }

    private static Student[] countingSort(Student[] inputArray) {
        return countingSort(inputArray, null);
    }

    private static Student[] countingSort(Student[] inputArray, Map<Integer, Integer> countMap) {


        int length = inputArray.length;
        int range = 5;

        Student[] output = new Student[length];

        int[] count = new int[range];

        for(int i = 0; i < range; i++)
            count[i] = 0;


        for(Student student : inputArray) count[student.getGradYear() - 2018]++;

        if(countMap != null) {
            for (int i = 0; i < range; i++) {
                countMap.put(i + 2018, count[i]);
            }
        }

        for(int i = 1; i <= range-1; i++)
            count[i] += count[i-1];

        for(int i = length-1; i >= 0; i--) {
            output[count[inputArray[i].getGradYear()-2018]-1] = inputArray[i];
            --count[inputArray[i].getGradYear()-2018];
        }


        return output;
    }
}
