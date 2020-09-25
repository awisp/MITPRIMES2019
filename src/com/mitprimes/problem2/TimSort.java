package com.mitprimes.problem2;

import com.mitprimes.Student;

public class TimSort {

    static int RUN = 32;

    public void timSort(Student[] arr, int n) {

        // Sort individual subarrays of size RUN
        for (int i = 0; i < n; i += RUN)
        {
            insertionSort(arr, i, Math.min((i + RUN-1), (n - 1)));
        }

        // start merging from size RUN (or 32). It will merge
        // to form size 64, then 128, 256 and so on ....
        for (int size = RUN; size < n; size = 2 * size)
        {

            // pick starting point of left sub array. We
            // are going to merge arr[left..left+size-1]
            // and arr[left+size, left+2*size-1]
            // After every merge, we increase left by 2*size
            for (int left = 0; left < n; left += 2 * size)
            {

                // find ending point of left sub array
                // mid+1 is starting point of right sub array
                int mid = left + size - 1;
                int right = Math.min((left + 2 * size - 1), (n - 1));

                if(mid > right) {
                    continue;
                }
                // merge sub array arr[left.....mid] &
                // arr[mid+1....right]
                merge(arr, left, mid, right);
            }
        }
    }

    public void insertionSort(Student[] arr, int left, int right)
    {
        for (int i = left + 1; i <= right; i++)
        {
            Student temp = arr[i];
            int j = i - 1;
            while (j >= left && arr[j].getGpa() < temp.getGpa()) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
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
}
