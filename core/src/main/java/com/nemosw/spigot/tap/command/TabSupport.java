package com.nemosw.spigot.tap.command;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public final class TabSupport
{

    private static Predicate<String> createFilter(String filter)
    {
        return s -> s.regionMatches(true, 0, filter, 0, filter.length());
    }

    public static <T> List<String> complete(Iterable<T> iterable, Function<? super T, String> function, String filter)
    {
        return StreamSupport.stream(iterable.spliterator(), false).map(function).filter(createFilter(filter)).collect(Collectors.toList());
    }

    public static List<String> complete(Iterable<String> iterable, String filter)
    {
        return StreamSupport.stream(iterable.spliterator(), false).filter(createFilter(filter)).collect(Collectors.toList());
    }

    private TabSupport()
    {}

}
