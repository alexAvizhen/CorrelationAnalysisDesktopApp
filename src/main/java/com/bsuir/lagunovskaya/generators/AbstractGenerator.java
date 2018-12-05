package com.bsuir.lagunovskaya.generators;

import java.util.List;

public abstract class AbstractGenerator {

    //простая реализация-заглушка, чтобы определить свой, более слоржный генератор, необходимо унаследоваться и переопредить этот метод
    abstract public List<Double> generate(int amountOfSignals);
}
