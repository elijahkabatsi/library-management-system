package com.company.library.util;

import org.junit.jupiter.api.BeforeEach;
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
        IdGenerator.INSTANCE.resetSequenceForTesting();
    }

    @Test
    void shouldGenerateUniqueIdsAcrossMultipleThreads() throws InterruptedException {
        int totalWorkerThreads = 100;
        int operationsPerThread = 1000;
        int totalExpectedCount = totalWorkerThreads * operationsPerThread;

        Set<String> collectedIdentifiers = ConcurrentHashMap.newKeySet();
        ExecutorService threadExecutor = Executors.newFixedThreadPool(totalWorkerThreads);
        CountDownLatch executionLatch = new CountDownLatch(totalWorkerThreads);

        for (int threadIdx = 0; threadIdx < totalWorkerThreads; threadIdx++) {
            threadExecutor.submit(() -> {
                try {
                    for (int step = 0; step < operationsPerThread; step++) {
                        collectedIdentifiers.add(IdGenerator.INSTANCE.nextBookId());
                    }
                } finally {
                    executionLatch.countDown();
                }
            });
        }

        executionLatch.await();
        threadExecutor.shutdown();

        assertThat(collectedIdentifiers).hasSize(totalExpectedCount);
    }
}
