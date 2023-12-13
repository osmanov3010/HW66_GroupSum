package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorGroupSum extends GroupSum {
    public ExecutorGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        // Homework: reduce sum numbers of numberGroups, use ExecutorService

        ExecutorService executorService = Executors.newWorkStealingPool();

        OneGroupSum[] tasks = new OneGroupSum[numberGroups.length];

        for (int i = 0; i < numberGroups.length; i++) {
            tasks[i] = new OneGroupSum(numberGroups[i]);
        }

        for (int i = 0; i < tasks.length; i++) {
            executorService.execute(tasks[i]);
        }

        executorService.shutdown();

        try {
            executorService.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Arrays.stream(tasks)
                .mapToInt(OneGroupSum::getSum)
                .sum();
    }
}
