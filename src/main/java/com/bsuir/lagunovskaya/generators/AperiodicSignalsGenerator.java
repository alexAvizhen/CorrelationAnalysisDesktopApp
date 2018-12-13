package com.bsuir.lagunovskaya.generators;

import java.util.ArrayList;
import java.util.List;

public class AperiodicSignalsGenerator extends AbstractGenerator {

    private Double a = 0.15;
    private Double b = 0.17;

    @Override
    public List<Double> generate(int amountOfSignals) {
        ArrayList<Double> resultList = new ArrayList<>();
        for (int i = 0; i < amountOfSignals; i++) {
            double t = i + 1;
            double power1 = -t * a;
            double power2 = -t * b;
            double s_t = 200.0 * (Math.pow(Math.E, power1) - Math.pow(Math.E, power2));
            resultList.add(s_t);
        }

        return resultList;
    }
}
