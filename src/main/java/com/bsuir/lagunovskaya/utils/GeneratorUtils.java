package com.bsuir.lagunovskaya.utils;

import com.bsuir.lagunovskaya.generators.AbstractGenerator;
import com.bsuir.lagunovskaya.generators.AperiodicSignalsGenerator;
import com.bsuir.lagunovskaya.generators.GaussianDistributedGenerator;
import com.bsuir.lagunovskaya.generators.HarmonicSignalsGenerator;
import com.bsuir.lagunovskaya.generators.HarmonicSignalsWithCasualStartPhaseGenerator;
import com.bsuir.lagunovskaya.generators.PolyHarmonicSignalsGenerator;
import com.bsuir.lagunovskaya.generators.SimpleGenerator;
import com.bsuir.lagunovskaya.generators.UniformlyDistributedSignalsGenerator;


public class GeneratorUtils {
    public static AbstractGenerator getGeneratorByGenerationType(String generationType) {
        if (GeneratorTypes.GENERATOR_1.equals(generationType)) {
            return new HarmonicSignalsGenerator();
        }
        if (GeneratorTypes.GENERATOR_2.equals(generationType)) {
            return new PolyHarmonicSignalsGenerator();
        }
        if (GeneratorTypes.GENERATOR_3.equals(generationType)) {
            return new AperiodicSignalsGenerator();
        }
        if (GeneratorTypes.GENERATOR_4.equals(generationType)) {
            return new HarmonicSignalsWithCasualStartPhaseGenerator();
        }
        if (GeneratorTypes.GENERATOR_5.equals(generationType)) {
            return new UniformlyDistributedSignalsGenerator();
        }
        if (GeneratorTypes.GENERATOR_6.equals(generationType)) {
            return new GaussianDistributedGenerator();
        }

        return new SimpleGenerator();
    }
}
