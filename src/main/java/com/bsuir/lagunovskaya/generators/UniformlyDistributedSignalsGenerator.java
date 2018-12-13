package com.bsuir.lagunovskaya.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class UniformlyDistributedSignalsGenerator extends AbstractGenerator {

    private Random random = new Random();
    private double a = 5.0;
    private double b = 55.0;

    @Override
    public List<Double> generate(int amountOfSignals) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < amountOfSignals; i++) {
            double f_t = a + random.nextDouble() * (b - a + 1);
            result.add(f_t);
        }
        return result;
    }
}
