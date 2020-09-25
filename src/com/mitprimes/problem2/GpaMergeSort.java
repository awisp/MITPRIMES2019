package com.mitprimes.problem2;

import com.mitprimes.Student;

public class GpaMergeSort {


    public static void main(String args[]) {
        Student[] arr = new Student[7];
        arr[0] = new Student("dsd", "afdadsf", 3.23f, 2020);
        arr[1] = new Student("dsd", "afdadsf", 3.26f, 2020);
        arr[2] = new Student("dsd", "afdadsf", 2.56f, 2020);
        arr[3] = new Student("dsd", "afdadsf", 3.64f, 2020);
        arr[4] = new Student("dsd", "afdadsf", 2.84f, 2022);
        arr[5] = new Student("dsd", "afdadsf", 4.00f, 2022);
        arr[6] = new Student("dsd", "afdadsf", 2.12f, 2022);

        GpaMergeSort mergeSort = new GpaMergeSort();
        mergeSort.sort(arr, 0, 3);

        for(Student a : arr) {
            System.out.println(a.getGpa());
        }
    }

    public void merge(Student arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        Student L[] = new Student[n1];
        Student R[] = new Student[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];


        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].getGpa() >= R[j].getGpa()) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }


    }

    public void sort(Student arr[], int l, int r)
    {
        if (l < r) {
            int m = (l + r) / 2;

            sort(arr, l, m);
            sort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }
}
