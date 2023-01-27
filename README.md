## Assignment 1

This project is for the Parallel and Distributed Systems class at the University of Central Florida, Spring 2023

## How to Run

1. `cd` to the file directory
2. Comping using `javac Assignment1.java`
3. Run using `java Assignment1`
4. The created "primes.txt" will include the output

## Approach and Reasoning

A modified version of the sieve of Eratosthenes is used to find all the prime numbers from 1 to 10^8. Each thread calls upon the same Runnable object, thus ensuring that they are each working on the same problem with the same set of fields/values. To ensure that only one thread is checking if a number is prime and to make sure we always move forward with our work, an Atomic Integer is used to ensure that only one thread is working on one prime at the same time. A concurrent HashSet is also used to for storing these prime numbers. The HashSet is then converted into an array, all its numbers are added together and the array is sorted. Sorting the array allows us to take the top 10 values of the array. We must sort the array first since the HashSet will be unordered even if all the primes were put in sequentially.

## Experimentation

When running this program with one thread, the estimated amount of time it took to find all primes and all other computation was around ~16 seconds. With 8 threads, the program was completed in roughly ~11 seconds.
