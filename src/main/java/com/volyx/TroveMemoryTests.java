package com.volyx;

import gnu.trove.map.TIntIntMap;
import gnu.trove.map.custom_hash.TObjectIntCustomHashMap;
import gnu.trove.map.hash.TCustomHashMap;
import gnu.trove.map.hash.TIntIntHashMap;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TIntHashSet;
import gnu.trove.set.hash.TLinkedHashSet;
import gnu.trove.strategy.IdentityHashingStrategy;

import java.util.*;

public class TroveMemoryTests {
    //you need to allocate 1.7G+ heap for this constant
    private static final int SIZE = 10 * 1000 * 1000;
    private static final float FILL_FACTOR = 0.75f;

    public static void main(String[] args) throws IllegalAccessException {
        //testing sets memory consumption
        testJdkHashSet();
        testJdkLinkedHashSet();
        testTHashSet();
        testTLinkedHashSet();
        testTIntHashSet();

        //testing map memory consumption
        testJdkHashMap();
        testJdkIdentityHashMap();
        testTroveIdentityHashMap();

        //testing numeric collections memory consumption
        testJdkIntIntHashMap();
        testTroveIntIntHashMap();
        testJdkIntHashSet();
        testTroveIntHashSet();
        testJdkIntLinkedHashSet();
        testTroveIntLinkedHashSet();

    }

    private static void testJdkIntIntHashMap() {
        final long start = getUsedMemory();
        final Map<Integer, Integer> map = new HashMap<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            map.put(i, i);

        System.out.println("HashMap<Integer, Integer> = " + getUsedMemoryEnd(start) + 'M');
        if (map.size() == 1) System.out.println(1);
    }

    private static void testTroveIntIntHashMap() {
        final long start = getUsedMemory();
        final TIntIntMap map = new TIntIntHashMap(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            map.put(i, i);

        System.out.println("TIntIntHashMap = " + getUsedMemoryEnd(start) + 'M');
        if (map.size() == 1) System.out.println(1);
    }

    private static void testJdkIntHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new HashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);

        System.out.println("HashSet<Integer> = " + getUsedMemoryEnd(start) + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testTroveIntHashSet() {
        final long start = getUsedMemory();
        final TIntSet set = new TIntHashSet(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);

        System.out.println("TIntHashSet = " + getUsedMemoryEnd(start) + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testJdkIntLinkedHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new LinkedHashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);

        System.out.println("LinkedHashSet<Integer> = " + getUsedMemoryEnd(start) + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testTroveIntLinkedHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new TLinkedHashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);

        System.out.println("TLinkedHashSet<Integer> = " + getUsedMemoryEnd(start) + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static long getUsedMemoryEnd(long start) {
        return (getUsedMemory() - start) / 1024 / 1024;
    }

    private static long getUsedMemory() {
        System.gc();
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    private static void testJdkHashMap() {
        final long start = getUsedMemory();
        final Map<String, Integer> map = new HashMap<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            map.put("string #" + i, i);

        System.out.println("HashMap<String, Integer> = " + getUsedMemoryEnd(start) + 'M');
        if (map.size() == 1) System.out.println(1);
    }

    private static void testJdkIdentityHashMap() {
        final long start = getUsedMemory();
        final Map<String, Integer> map = new IdentityHashMap<>(SIZE);
        for (int i = 0; i < SIZE; ++i)
            map.put("string #" + i, i);

        System.out.println("IdentityHashMap<String, Integer> = " + getUsedMemoryEnd(start) + 'M');
        if (map.size() == 1) System.out.println(1);
    }

    private static void testTroveIdentityHashMap() {
        final long start = getUsedMemory();
        //INSTANCE is available from Trove 3.1a1
        final TObjectIntCustomHashMap<String> map = new TObjectIntCustomHashMap<>(IdentityHashingStrategy.INSTANCE, SIZE, FILL_FACTOR, -1);
        final TCustomHashMap<String, String> map2 = new TCustomHashMap<>(IdentityHashingStrategy.INSTANCE);
        for (int i = 0; i < SIZE; ++i)
            map.put("string #" + i, i);

        System.out.println("TObjectIntCustomHashMap<String> = " + getUsedMemoryEnd(start) + 'M');
        if (map.size() == 1) System.out.println(1);
    }


    private static void testJdkHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new HashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);

        System.out.println("HashSet<Integer> = " + getUsedMemoryEnd(start) + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testJdkLinkedHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new LinkedHashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);

        System.out.println("LinkedHashSet<Integer> = " + getUsedMemoryEnd(start) + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testTHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new THashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);

        System.out.println("THashSet<Integer> = " + getUsedMemoryEnd(start) + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testTLinkedHashSet() {
        final long start = getUsedMemory();
        final Set<Integer> set = new TLinkedHashSet<>(SIZE, FILL_FACTOR);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);

        System.out.println("TLinkedHashSet<Integer> = " + getUsedMemoryEnd(start) + 'M');
        if (set.size() == 1) System.out.println(1);
    }

    private static void testTIntHashSet() {
        final long start = getUsedMemory();
        final TIntSet set = new TIntHashSet(SIZE, FILL_FACTOR, -1);
        for (int i = 0; i < SIZE; ++i)
            set.add(i);
        System.out.println("TIntHashSet<Integer> = " + getUsedMemoryEnd(start) + 'M');
        if (set.size() == 1) System.out.println(1);
    }
}
