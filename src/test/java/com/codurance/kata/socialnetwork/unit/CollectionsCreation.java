package com.codurance.kata.socialnetwork.unit;

import java.util.*;

public class CollectionsCreation {
    public static <T> Set<T> setOf(T... elements) {
        return new HashSet<T>() {{
            addAll(Arrays.asList(elements));
        }};
    }

    public static <T> NavigableSet<T> navigableSetOf(T... elements) {
        return new TreeSet<T>() {{
            addAll(Arrays.asList(elements));
        }};
    }
}
