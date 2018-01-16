package com.farenda.java.util.stream;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class CreatingStreams {

    public static void main(String[] args) {
        CreatingStreams streams = new CreatingStreams();

        streams.ofIndividualElements();
        streams.fromArray();
        streams.gotcha();
        streams.fromCollection();
        streams.ofChars();
        streams.buildStream();
        streams.iterateStream();
        streams.generateStream();
    }

    public <T> Stream<T> emptyStream(Collection<T> coll) {
        return coll != null ? coll.stream() : Stream.empty();
    }

    public void ofIndividualElements() {
        Stream<Object> items = Stream.of(new Object(), 2, "foo");
        items.forEach(System.out::print);
        System.out.println();
    }

    public void fromArray() {
        Integer[] nums = {1, 2, 3};
        Stream.of(nums).forEach(System.out::print);
        System.out.println();

        Arrays.stream(nums).forEach(System.out::print);
        System.out.println();
    }

    public void gotcha() {
        int[] nums = {1, 2, 3};
        System.out.print("Stream.of(nums): ");
        Stream.of(nums).forEach(System.out::println);

        System.out.print("Arrays.stream(nums): ");
        Arrays.stream(nums).forEach(System.out::print);
        System.out.println();
    }

    public void fromCollection() {
        Collection<String> names = asList("foo", "bar");
        Stream<String> namesStream = names.stream();

        Set<String> abc = new HashSet<>(asList("a", "b", "c"));
        Stream<String> idStream = abc.stream();

        List<Integer> fibos = asList(1, 1, 2, 3, 5);
        Stream<Integer> fiboStream = fibos.stream();

        Map<Integer,String> usersById = new HashMap<>();
        Stream<Entry<Integer, String>> userStream
                = usersById.entrySet().stream();
    }

    public void ofChars() {
        String transformed = "Hello".codePoints()
                .filter(c -> c > 90)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
        System.out.println(transformed);
    }

    public void buildStream() {
        Stream<String> built = Stream.<String>builder()
                .add("x")
                .add("y")
                .add("z")
                .build();

        built.forEach(System.out::print);
        System.out.println();
    }

    public void iterateStream() {
        Stream<Integer> evenNumbers = Stream.iterate(0, p -> p + 2);
        evenNumbers.limit(5).forEach(System.out::print);
        System.out.println();
    }

    public void generateStream() {
        Stream<Integer> constantly = Stream.generate(() -> 1);
        constantly.limit(10).forEach(System.out::print);
        System.out.println();
    }
}
