package com.stefanpetcu.matrixwordfinder.application.utils.wordfinder;

import com.stefanpetcu.matrixwordfinder.application.utils.wordfinder.dto.WordFirstAndLastCharCoordinates;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;


class CaseInsensitiveMatrixWordFinderTest {
    private final WordFinder<WordFirstAndLastCharCoordinates, Character[][]> wordFinder = new CaseInsensitiveMatrixWordFinder();

    static private Stream<Arguments> wordMatricesContainingFooAndCoordinates() {
        return Stream.of(
                arguments(new Character[][]{},
                        new WordFirstAndLastCharCoordinates(new SimpleEntry<>(-1, -1), new SimpleEntry<>(-1, -1))),
                arguments(new Character[][]{
                        new Character[]{'F', 'O'},
                        new Character[]{'A', 'S'}
                }, new WordFirstAndLastCharCoordinates(new SimpleEntry<>(-1, -1), new SimpleEntry<>(-1, -1))),
                arguments(new Character[][]{
                        new Character[]{'F', 'O', 'S'},
                        new Character[]{'A', 'S', 'D'}
                }, new WordFirstAndLastCharCoordinates(new SimpleEntry<>(-1, -1), new SimpleEntry<>(-1, -1))),
                arguments(new Character[][]{
                        new Character[]{'F', 'O', 'O'}
                }, new WordFirstAndLastCharCoordinates(new SimpleEntry<>(0, 0), new SimpleEntry<>(0, 2))),
                arguments(new Character[][]{
                        new Character[]{'F',},
                        new Character[]{'O',},
                        new Character[]{'O',}
                }, new WordFirstAndLastCharCoordinates(new SimpleEntry<>(0, 0), new SimpleEntry<>(2, 0))),
                arguments(new Character[][]{
                        new Character[]{'B', 'A', 'S', 'D'},
                        new Character[]{'A', 'F', 'o', 'O'},
                        new Character[]{'C', 'B', 'a', 'R'}
                }, new WordFirstAndLastCharCoordinates(new SimpleEntry<>(1, 1), new SimpleEntry<>(1, 3))),
                arguments(new Character[][]{
                        new Character[]{'B', 'A', 'A', 'A', 'S', 'D'},
                        new Character[]{'A', 'F', 'F', 'F', 'o', 'O'},
                        new Character[]{'C', 'B', 'B', 'B', 'a', 'R'}
                }, new WordFirstAndLastCharCoordinates(new SimpleEntry<>(1, 3), new SimpleEntry<>(1, 5))),
                arguments(new Character[][]{
                        new Character[]{'A', 'F', 'A', 'b'},
                        new Character[]{'A', 'F', 'A', 'b'},
                        new Character[]{'B', 'O', 's', 'D'},
                        new Character[]{'C', 'o', 'G', 'G'},
                        new Character[]{'C', 'o', 'G', 'G'}
                }, new WordFirstAndLastCharCoordinates(new SimpleEntry<>(1, 1), new SimpleEntry<>(3, 1)))
        );
    }

    @ParameterizedTest
    @MethodSource("wordMatricesContainingFooAndCoordinates")
    void findWordInMatrix_returns_expected_word_coords(Character[][] charMatrix, WordFirstAndLastCharCoordinates expectedWordCoordinates) {
        var wordCoordinates = wordFinder.firstCoordinatedOf("foo", charMatrix);
        assertEquals(expectedWordCoordinates, wordCoordinates);
    }
}
