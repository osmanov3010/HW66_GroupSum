package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;

public class ThreadGroupSum extends GroupSum {
    public ThreadGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        // Homework: reduce sum numbers of numberGroups, use Threads

        OneGroupSum[] tasks = new OneGroupSum[numberGroups.length];

        for (int i = 0; i < numberGroups.length; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]);
        }


        Thread[] threads = new Thread[numberGroups.length];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Arrays.stream(tasks)
                .mapToInt(OneGroupSum::getSum)
                .sum();
    }
}
