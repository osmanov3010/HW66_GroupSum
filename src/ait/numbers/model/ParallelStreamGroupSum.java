package ait.numbers.model;

import ait.numbers.task.OneGroupSum;

import java.util.Arrays;
import java.util.stream.Stream;

public class ParallelStreamGroupSum extends GroupSum{

    public ParallelStreamGroupSum(int[][] numberGroups) {
        super(numberGroups);
    }

    @Override
    public int computeSum() {
        // ADV Homework: reduce sum numbers of numberGroups, use Arrays.stream().parallel()

        return Arrays.stream(numberGroups).parallel()
                .flatMapToInt(Arrays::stream)
                .sum();
    }
}
