package com.problems;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MiscTests {
    private static String key1 = "Aa";
    private static String key2 = "BB";

    @Test
    public void testHashes() {
        System.out.println("h1=" + key1.hashCode());
        System.out.println("h2=" + key2.hashCode());
        Assertions.assertEquals(key1.hashCode(), key2.hashCode());
    }

    @Test
    public void testMap() {
        final Map<String, Object> map = new HashMap<>();
        map.put(key1, "abc");
        map.put(key2, "xyz");
        System.out.println("v1=" + map.get(key1));
        System.out.println("v2=" + map.get(key2));
    }
}
