package com.tripworld.utility;

import com.tripworld.exceptions.NoRecordFoundException;

import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Utility {

    public static <T> Collector<T, ?, T> toSingleton() {
        return Collectors.collectingAndThen(
                Collectors.toList(),
                list -> {
                    if (list.size() < 1) {
                        throw new NoRecordFoundException();
                    }
                    return list.get(0);
                }
        );
    }
}
