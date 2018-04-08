package com.promotion.winaball.testutils;

import java.util.concurrent.ThreadFactory;

public class TestThreadFactory implements ThreadFactory {
    private int threadCounter = 1;

    @Override
    public Thread newThread(final Runnable r) {
        return new Thread(r, "RedemptionAttemptThread-" + threadCounter++);
    }
}
