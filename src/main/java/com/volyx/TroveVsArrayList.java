package com.volyx;

import gnu.trove.list.array.TLongArrayList;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;

public class TroveVsArrayList {

    private static final int MAX = 10_000_000;

    @Benchmark
    public void troveLong() {
        TLongArrayList longs = new TLongArrayList();
        for (long i = 0; i < MAX; i++) {
            longs.add(i);
        }
    }

    @Benchmark
    public void arrayList() {
        ArrayList<Long> longs = new ArrayList<>();
        for (long i = 0; i < MAX; i++) {
            longs.add(i);
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(TroveVsArrayList.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
