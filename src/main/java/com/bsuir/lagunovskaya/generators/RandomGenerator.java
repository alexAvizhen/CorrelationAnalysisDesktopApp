package com.bsuir.lagunovskaya.generators;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomGenerator extends AbstractGenerator{

    public RandomGenerator() {
    }

    @Override
    public List<Double> generate(int amountOfSignals) {
        Random random = new Random();
        ArrayList<Double> resultList = new ArrayList<>();
        for (int i = 0; i < amountOfSignals; i++) {
            resultList.add(random.nextDouble() * 100);
        }
        return resultList;
    }


}
