package com.stefanpetcu.matrixwordfinder.application.utils.wordfinder;

public interface WordFinder<T, U> {
    T firstCoordinatedOf(String word, U source);
}
