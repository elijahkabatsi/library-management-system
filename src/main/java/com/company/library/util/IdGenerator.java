package com.company.library.util;

import com.company.library.exception.LibraryException;
import java.util.concurrent.atomic.AtomicLong;

public enum IdGenerator {
    INSTANCE;

    private final AtomicLong bookCounter = new AtomicLong(0);
    private final AtomicLong memberCounter = new AtomicLong(0);
    private static final long UPPER_BOUND_LIMIT = 999_999L;

    public String nextBookId() {
        long currentVal = bookCounter.incrementAndGet();
        validateSequenceRange(currentVal, "Book");
        return String.format("BK-%06d", currentVal);
    }

    public String nextMemberId() {
        long currentVal = memberCounter.incrementAndGet();
        validateSequenceRange(currentVal, "Member");
        return String.format("MB-%06d", currentVal);
    }

    private void validateSequenceRange(long pSequenceVal, String pEntityTypeName) {
        if (pSequenceVal > UPPER_BOUND_LIMIT) {
            throw new LibraryException(pEntityTypeName + " numeric sequence capacity exceeded.");
        }
    }

    public void resetSequenceForTesting() {
        bookCounter.set(0);
        memberCounter.set(0);
    }
}
