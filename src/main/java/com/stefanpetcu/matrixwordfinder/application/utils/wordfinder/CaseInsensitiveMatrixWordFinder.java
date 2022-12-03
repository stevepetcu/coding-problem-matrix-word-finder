package com.stefanpetcu.matrixwordfinder.application.utils.wordfinder;

import com.stefanpetcu.matrixwordfinder.application.utils.wordfinder.dto.WordFirstAndLastCharCoordinates;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

public class CaseInsensitiveMatrixWordFinder implements WordFinder<WordFirstAndLastCharCoordinates, Character[][]> {
    private static final WordFirstAndLastCharCoordinates WORD_NOT_FOUND =
            new WordFirstAndLastCharCoordinates(new SimpleEntry<>(-1, -1), new SimpleEntry<>(-1, -1));

    @Override
    public WordFirstAndLastCharCoordinates firstCoordinatedOf(String word, Character[][] charMatrix) {
        return findFirstOccurrenceInCharMatrixRows(word.toLowerCase(), charMatrix)
                .orElse(findFirstOccurrenceInCharMatrixCols(word, charMatrix).orElse(WORD_NOT_FOUND));
    }

    private Optional<WordFirstAndLastCharCoordinates> findFirstOccurrenceInCharMatrixRows(String word, Character[][] matrix) {
        if (matrix.length == 0) {
            return Optional.empty();
        }

        if (matrix[0].length >= word.length()) {
            for (int i = 0; i < matrix.length; i++) {
                var rowLowerCaseString = Arrays.stream(matrix[i]).map(c -> c.toString().toLowerCase()).collect(Collectors.joining());
                var indexOfWordInRow = rowLowerCaseString.indexOf(word); // is this cheating? ;)
                if (indexOfWordInRow != -1) {
                    return Optional.of(new WordFirstAndLastCharCoordinates(
                            new SimpleEntry<>(i, indexOfWordInRow),
                            new SimpleEntry<>(i, indexOfWordInRow + word.length() - 1)
                    ));
                }
            }
        }

        return Optional.empty();
    }

    private Optional<WordFirstAndLastCharCoordinates> findFirstOccurrenceInCharMatrixCols(String word, Character[][] matrix) {
        if (matrix.length >= word.length()) {
            for (int i = 0; i < matrix[0].length; i++) {
                var columnChars = new ArrayList<Character>();
                for (Character[] characters : matrix) {
                    columnChars.add(characters[i]);
                }
                var colLowerCaseString = columnChars.stream().map(c -> c.toString().toLowerCase()).collect(Collectors.joining());
                var indexOfWordInCol = colLowerCaseString.indexOf(word);
                if (indexOfWordInCol != -1) {
                    return Optional.of(new WordFirstAndLastCharCoordinates(
                            new SimpleEntry<>(indexOfWordInCol, i),
                            new SimpleEntry<>(indexOfWordInCol + word.length() - 1, i)
                    ));
                }
            }
        }

        return Optional.empty();
    }
}
