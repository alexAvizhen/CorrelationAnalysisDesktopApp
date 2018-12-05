package com.bsuir.lagunovskaya.generators;

import java.util.ArrayList;
import java.util.List;


public class SimpleGenerator extends AbstractGenerator {

    public SimpleGenerator() {
    }

    @Override
    public List<Double> generate(int amountOfSignals) {
        List<Double> resultList = new ArrayList<>();
        for (int i = 0; i < amountOfSignals; i++) {
            double res = 1.0 + i % 2;
            resultList.add(res);
        }
        return resultList;
    }
}
