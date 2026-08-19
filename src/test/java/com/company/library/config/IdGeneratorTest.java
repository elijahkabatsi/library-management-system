package com.company.library.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

class IdGeneratorTest {

    @BeforeEach
    void setUp() {
        IdGenerator.getInstance().resetForTests();
    }

    @Test
    @DisplayName("Spawns 100 threads generating 1,000 IDs each and verifies exactly 100,000 distinct IDs")
    void shouldGenerateDistinctIdsConcurrently() throws InterruptedException {
        int threadCount = 100;
        int idsPerThread = 1_000;
        int totalExpectedIds = threadCount * idsPerThread;

        Set<String> generatedIds = ConcurrentHashMap.newKeySet();
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    IdGenerator generator = IdGenerator.getInstance();
                    for (int j = 0; j < idsPerThread; j++) {
                        generatedIds.add(generator.nextBookId());
                    }
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        assertThat(generatedIds).hasSize(totalExpectedIds);
    }
}