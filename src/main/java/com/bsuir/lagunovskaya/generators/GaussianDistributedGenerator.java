package com.bsuir.lagunovskaya.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GaussianDistributedGenerator extends AbstractGenerator {

    private Random random = new Random();

    @Override
    public List<Double> generate(int amountOfSignals) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < amountOfSignals; i++) {
            double x_t = 10 * random.nextGaussian();
            result.add(x_t);
        }
        return result;
    }
}
