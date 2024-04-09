package me.keepkam;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

@Slf4j
class StringBuilderTest {
    private StringBuilder sb = new StringBuilder();

    @Test
    void test() throws NoSuchFieldException, IllegalAccessException {
        printState(sb);

        add("one");
        add("twoo");
        add("exceed sizteen");
    }

    @SneakyThrows
    void add(Object o) {
        sb.append(o);
        printState(sb);
    }

    @SneakyThrows
    void printState(StringBuilder sb) {
        Field count = sb.getClass().getSuperclass().getDeclaredField("count");
        count.setAccessible(true);

        Field value = sb.getClass().getSuperclass().getDeclaredField("value");
        value.setAccessible(true);

        log.info("count: {}, len: {}, value: {},", count.getInt(sb), ((byte[]) value.get(sb)).length, value.get(sb));
    }
}
