package me.keepkam;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Comparator.naturalOrder;
import static java.util.stream.IntStream.rangeClosed;
import static org.assertj.core.api.Assertions.assertThat;

class MapTests {
    private static final Logger log = Logger.getLogger(MapTests.class.getName());
    private final Random random = new Random();
    private final int testRuns = 10;
    private final int collectionSize = 5;

    @Test
    void HashMap_doesNotMaintain_SortingOrder() {
        // populate the map and a separate key list
        var testMap = new HashMap<String, Integer>(collectionSize);
        var keysList = new ArrayList<String>(collectionSize);

        rangeClosed(1, collectionSize).forEach(i -> {
            var randomString = randomLowercaseString();
            keysList.add(randomString);
            testMap.put(randomString, i);
        });

        // compare the sorted keysList with keys retrieved from the map
        keysList.sort(naturalOrder());
        var keys = testMap.keySet().toArray(new String[collectionSize]);

        boolean ordered = true;
        for (int i = 0; i < keys.length; i++) {
            ordered = ordered && keysList.get(i).equals(keys[i]);
//            log.log(Level.INFO, "%s %s".formatted(keysList.get(i), keys[i]));
        }

        assertThat(ordered).isFalse();
    }

    @Test
    void TreeMap_maintains_SortingOrder() {
        // populate the map and a separate key list
        // use a TreeMap instead of HashMap
        var testMap = new TreeMap<String, Integer>();
        var keysList = new ArrayList<String>(collectionSize);

        rangeClosed(1, collectionSize).forEach(i -> {
            var randomString = randomLowercaseString();
            keysList.add(randomString);
            testMap.put(randomString, i);
        });

        // compare the sorted keysList with keys retrieved from the map
        keysList.sort(naturalOrder());
        var keys = testMap.keySet().toArray(new String[collectionSize]);

        boolean ordered = true;
        for (int i = 0; i < keys.length; i++) {
            ordered = ordered && keysList.get(i).equals(keys[i]);
//            log.log(Level.INFO, "%s %s".formatted(keysList.get(i), keys[i]));
        }

        assertThat(ordered).isTrue();
    }

    @Test
    void HashMap_doesNotMaintain_InsertionOrder() {
        // populate the map and a separate key list
        var testMap = new HashMap<String, Integer>(collectionSize);
        var keysList = new ArrayList<String>(collectionSize);

        rangeClosed(1, collectionSize).forEach(i -> {
            var randomString = randomLowercaseString();
            keysList.add(randomString);
            testMap.put(randomString, i);
        });

        // compare the sorted keysList with keys retrieved from the map
        var keys = testMap.keySet().toArray(new String[collectionSize]);

        boolean ordered = true;
        for (int i = 0; i < keys.length; i++) {
            ordered = ordered && keysList.get(i).equals(keys[i]);
            log.log(Level.INFO, "%s %s".formatted(keysList.get(i), keys[i]));
        }

        assertThat(ordered).isFalse();
    }

    @Test
    void LinkedHashMap_maintains_InsertionOrder() {
        // populate the map and a separate key list
        // use a LinkedHashMap instead of HashMap
        var testMap = new LinkedHashMap<String, Integer>();
        var keysList = new ArrayList<String>(collectionSize);

        rangeClosed(1, collectionSize).forEach(i -> {
            var randomString = randomLowercaseString();
            keysList.add(randomString);
            testMap.put(randomString, i);
        });

        // compare the order of keysList with keys retrieved from the map
        var keys = testMap.keySet().toArray(new String[collectionSize]);

        boolean ordered = true;
        for (int i = 0; i < keys.length; i++) {
            ordered = ordered && keysList.get(i).equals(keys[i]);
//            log.log(Level.INFO, "%s %s".formatted(keysList.get(i), keys[i]));
        }

        assertThat(ordered).isTrue();
    }

    private String randomLowercaseString() {
        int a = 'a';
        int z = 'z';
        int string_length = 5;
        byte[] randomBytes = new byte[5];

        return random.ints(a, z+1).limit(string_length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}