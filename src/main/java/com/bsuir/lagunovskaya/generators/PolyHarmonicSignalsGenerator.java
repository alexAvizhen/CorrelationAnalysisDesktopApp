package com.bsuir.lagunovskaya.generators;


import java.util.ArrayList;
import java.util.List;

public class PolyHarmonicSignalsGenerator extends AbstractGenerator{

    private Double A_1 = 5.0;
    private Double A_2 = 15.0;
    private Double A_3 = 30.0;

    private Double f_1 = 0.04;
    private Double f_2 = 0.12;
    private Double f_3 = 0.05;

    private Double fi_1 = 0.0;
    private Double fi_2 = 0.7;
    private Double fi_3 = 1.2;

    @Override
    public List<Double> generate(int amountOfSignals) {
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < amountOfSignals; i++) {
            Double t = i + 1.0;
            Double x_t = x_i_t(A_1, f_1, t, fi_1);
            x_t += x_i_t(A_2, f_2, t, fi_2);
            x_t += x_i_t(A_3, f_3, t, fi_3);
            result.add(x_t);
        }
        return result;
    }

    private Double x_i_t(Double A_i, Double f_i, Double t, Double fi_i) {
        Double value = 2.0 * Math.PI * f_i * t - fi_i;
        return A_i * Math.cos(value);
    }
}
