// Nathanael Gaulke
// COP 4930, Spring 2023

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Assignment1 implements Runnable {
    public static final int max = (int) Math.pow(10, 8);

    public static AtomicInteger counter;

    public static Set<Integer> primes;

    public static boolean[] primesArray;

    public Assignment1() {
        ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
        primes = map.newKeySet();
        primes.add(2);
        counter = new AtomicInteger(2);
        primesArray = new boolean[max + 1];
        Arrays.fill(primesArray, true);
    }

    @Override
    public void run() {
        int i = counter.get();
        // find all primes between 2 and 10^8 (sieve approach)
        while ((i = counter.getAndIncrement()) <= max) {
            if (primesArray[i]) {
                primes.add(i);
                for (int j = 2 * i; j <= max; j += i) {
                    primesArray[j] = false;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        // start the clock
        long start = System.currentTimeMillis();
        Assignment1 obj = new Assignment1();
        Thread t1 = new Thread(obj);
        Thread t2 = new Thread(obj);
        Thread t3 = new Thread(obj);
        Thread t4 = new Thread(obj);
        Thread t5 = new Thread(obj);
        Thread t6 = new Thread(obj);
        Thread t7 = new Thread(obj);
        Thread t8 = new Thread(obj);

        // Begin running all the threads to find all the primes
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();

        // Wait for all the threads to finish running
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();
        t6.join();
        t7.join();
        t8.join();

        // Put all the primes in an array and sort. Then sum all the primes in the array
        Integer[] primeSet = new Integer[primes.size()];
        primeSet = primes.toArray(primeSet);
        Arrays.sort(primeSet);
        long sumOfPrimes = 0;
        for (Integer i : primeSet)
            sumOfPrimes += i;

        // Begin outputting
        // <execution time> <total number of primes found> <sum of all primes found>
        // <top ten maximum primes, listed in order from lowest to highest>
        PrintWriter output = new PrintWriter(new FileWriter("primes.txt"));
        long end = System.currentTimeMillis();
        double sec = (end - start) / 1000.0;
        output.println("Time: " + sec + "s; Total primes: " + primeSet.length + "; Sum of Primes: " + sumOfPrimes);
        output.print("Top 10 Primes: [");
        for (int i = primeSet.length - 10; i < primeSet.length - 1; i++)
            output.print(primeSet[i] + ", ");
        output.println(primeSet[primeSet.length - 1] + "]");
        output.close();
    }
}