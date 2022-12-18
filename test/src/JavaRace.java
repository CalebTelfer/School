/*
Written by Caleb Telfer Dec.18 2022
Purpose: Sort and search experiment
Features: Sort, search and populate arrays.
 */

import java.util.ArrayList;


public class JavaRace {
    static ArrayList<Integer> numbersArray1 = new ArrayList<Integer>(); // A: Reverse Sorted Number Array
    static ArrayList<Integer> numbersArray2 = new ArrayList<Integer>();    // A: Nearly Number Strings

    static ArrayList<Integer> sortedNums = new ArrayList<Integer>();        // for part 2: searching
    static ArrayList<Integer> unsortedNums = new ArrayList<Integer>();

    public static void main(String args[]) {
        long time, time2, finalTime;
        populateINT(50000); // reverse populate array
        nearSort(50000); // nearly sort array

        System.out.println("Sorting reversed array.");
        bubbleSort(numbersArray1); // bubble sort reverse array
        insertionSort(numbersArray1);// insertion sort reverse array

        System.out.println("\nSorting partially sorted array.");
        bubbleSort(numbersArray2);
        insertionSort(numbersArray2);

        //Making fully sorted and unsorted random arrays
        System.out.println("\nSorting an array to search.");
        fillArrays(sortedNums, 50000, 1);

        fillArrays(unsortedNums, 50000, 0);

        System.out.println("\nSearching unsorted array.");
        sequentialSearch(unsortedNums);

        //Run binary search for the number 100
        time = System.currentTimeMillis();
        System.out.println("100 found at index # " +binarySearch(unsortedNums, 0, sortedNums.size()-1, 100));
        time2 = System.currentTimeMillis();
        finalTime = time2 - time;
        System.out.println("Binary search took " +finalTime+ " Milliseconds");

        System.out.println("\nSearching sorted array.");
        sequentialSearch(sortedNums);

        time = System.currentTimeMillis();
        System.out.println("100 found at index # " +binarySearch(sortedNums, 0, sortedNums.size()-1, 100));
        time2 = System.currentTimeMillis();
        finalTime = time2 - time;
        System.out.println("Binary search took " +finalTime+ " Milliseconds");


    }

    /*
     *************************************-SORTING SECTION-*****************************************************
     */

    public static void populateINT(int n) {
        //This method will populate the int array list with random numbers in reverse order.
        for (int i =0; i < n+1; i++) {
            int randNum = (int)Math.floor(Math.random()*(100-1+1)+1); // num 1-100
            numbersArray1.add(0, randNum); // reversing, so first entered num will always be last.
        }
    }


    public static void nearSort (int n) {

        for(int i = 0; i < n+1; i++) {
            int randNum = (int)Math.floor(Math.random()*(100-1+1)+1); // num 1-100.

            //every third number generated will check the previous number, if that numbers bigger, insert new number there.
            if(i%3 == 0 && i!= 0) {

                if(numbersArray2.get(numbersArray2.size()-1) > randNum) {

                    numbersArray2.add(numbersArray2.size()-1, randNum);

                }
            } else numbersArray2.add(randNum);
        }

    }

    public static void bubbleSort(ArrayList<Integer> list) {
        long time, time2, finalTime;
        time = System.currentTimeMillis();
        int arrLength = list.size();
        int temp = 0;

        for (int i=0; i < arrLength; i++) {
            for (int j = 1; j<arrLength; j++) {
                if(list.get(j-1) > list.get(j)) {

                    temp = list.get(j);
                    list.set(j, list.get(j-1));
                    list.set(j-1, temp);

                }

            }

        }
        time2 = System.currentTimeMillis();
        finalTime = time2 - time;
        System.out.println("Bubble sort took " +finalTime+ " Milliseconds");
    }


    public static void insertionSort(ArrayList<Integer> list) {
        long time, time2, finalTime;
        time = System.currentTimeMillis();
        int arrLength = list.size();

        for (int i = 1; i < arrLength; i++) {
            int key = list.get(i);
            int j = i-1;
            while (j > -1 && list.get(j) > key) {
                list.set(j+1, list.get(j));
                j--;
            }
            list.set(j+1, key);
        }

        time2 = System.currentTimeMillis();
        finalTime = time2 - time;
        System.out.println("Insertion sort took " +finalTime+ " Milliseconds");
    }

    /*
    *************************************-SEARCHING SECTION-*****************************************************
     */

    public static void fillArrays(ArrayList<Integer> list,int n, int mode) {

        for (int i =0; i < n+1; i++) {
            int randNum = (int)Math.floor(Math.random()*(100-1+1)+1); // num 1-100
            list.add(0, randNum); // reversing, so first entered num will always be last.
        }

        if(mode == 1) insertionSort(list); // sort the list as well if mode == 1.
    }

     public static void sequentialSearch(ArrayList<Integer> list) {
         long time, time2, finalTime;
         time = System.currentTimeMillis();
         int arrLength = list.size();
         int flag = 0;
         int search = 100;
         for (int i = 0; i < arrLength; i++) {
             if(search == list.get(i)) {
                 flag = i;
                 System.out.println(search + " found at index # " +flag);
                 break;
             }
         }

         time2 = System.currentTimeMillis();
         finalTime = time2 - time;
         System.out.println("Sequential search took " +finalTime+ " Milliseconds");
     }

     public static int binarySearch(ArrayList<Integer> list, int low, int high, int x) {
        if (low > high) return -1; //not found

        int mid = (low + high) / 2; // middle point

        if (list.get(mid) == x) return mid; // if middle is search key (x)

        if (list.get(mid) < x) return binarySearch(list, mid + 1, high, x); // if x is higher than mid

        if (list.get(mid) > x) return binarySearch(list, low, mid - 1, x); // if x is lower than mid
         return -1;
     }

}