package me.keepkam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
class StreamExercises {
    @Test
    void oddEven() {
        List<Integer> ints = IntStream.range(0, 10).collect(ArrayList::new, List::add, List::addAll);

        Map<String, List<Integer>> collect = ints.stream().collect(Collectors.groupingBy(n -> n % 2 == 0 ? "even" : "odd"));
        log.info("{}", collect);
    }
}
