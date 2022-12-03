package com.stefanpetcu.matrixwordfinder.application.utils.wordfinder.dto;

import java.util.Map.Entry;

public record WordFirstAndLastCharCoordinates(Entry<Integer, Integer> firstCharCoords,
                                              Entry<Integer, Integer> lastCharCoords) {
}
