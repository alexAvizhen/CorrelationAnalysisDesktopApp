package com.bsuir.lagunovskaya.utils;

import com.bsuir.lagunovskaya.generators.AbstractGenerator;
import com.bsuir.lagunovskaya.generators.AnalogSignalsGenerator;
import com.bsuir.lagunovskaya.generators.RandomGenerator;
import com.bsuir.lagunovskaya.generators.SimpleGenerator;


public class GeneratorUtils {
    public static AbstractGenerator getGeneratorByGenerationType(String generationType) {
        if (GeneratorTypes.GENERATOR_1.equals(generationType)) {
            return new AnalogSignalsGenerator();
        }
        if (GeneratorTypes.GENERATOR_2.equals(generationType)) {
            return new RandomGenerator();
        }
        if (GeneratorTypes.GENERATOR_9.equals(generationType)) {
            return new AnalogSignalsGenerator();
        }


        return new SimpleGenerator();
    }
}
