package com.bsuir.lagunovskaya.generators;

import java.util.ArrayList;
import java.util.List;


public class AnalogSignalsGenerator extends AbstractGenerator {
    @Override
    public List<Double> generate(int amountOfSignals) {
        ArrayList<Double> resultList = new ArrayList<>();
        for (int i = 0; i < amountOfSignals; i++) {
            double t = i + 1;
            double power = -1.0 * Math.pow(t - 4.0, 2.0) / 2.8;
            double y_t = 4.8 * Math.pow(Math.E, power);
            resultList.add(y_t);
        }

        return resultList;
    }
}
