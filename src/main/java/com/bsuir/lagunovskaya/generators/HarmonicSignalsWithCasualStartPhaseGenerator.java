package com.bsuir.lagunovskaya.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HarmonicSignalsWithCasualStartPhaseGenerator extends AbstractGenerator {

    private double A = 50.0;
    private double f = 0.08;
    private Random random = new Random();

    @Override
    public List<Double> generate(int amountOfSignals) {
        double fi = random.nextDouble() * 2 * Math.PI; //равномерно распределенная на интервале (0, 2π) случайная фаза
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < amountOfSignals; i++) {
            double t = i + 1.0;
            double value = 2.0 * Math.PI * f * t - fi;
            double x_t = A * Math.cos(value);
            result.add(x_t);
        }
        return result;
    }
}
