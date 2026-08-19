package com.company.library.config;

import com.company.library.exception.ConfigurationException;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {
    private static final long MAX_ID = 999_999L;
    private static final IdGenerator INSTANCE = new IdGenerator();

    private final AtomicLong bookSeq = new AtomicLong(0);
    private final AtomicLong memberSeq = new AtomicLong(0);

    private IdGenerator() {}

    public static IdGenerator getInstance() {
        return INSTANCE;
    }

    public String nextBookId() {
        long next = bookSeq.incrementAndGet();
        if (next > MAX_ID) {
            throw new ConfigurationException("Book ID numeric sequence exhausted (max: " + MAX_ID + ")");
        }
        return String.format("BK-%06d", next);
    }

    public String nextMemberId() {
        long next = memberSeq.incrementAndGet();
        if (next > MAX_ID) {
            throw new ConfigurationException("Member ID numeric sequence exhausted (max: " + MAX_ID + ")");
        }
        return String.format("MB-%06d", next);
    }

    public void resetForTests() {
        bookSeq.set(0);
        memberSeq.set(0);
    }
}