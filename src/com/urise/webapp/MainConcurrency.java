package com.urise.webapp;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainConcurrency {
    public static final int THREADS_NUMBER = 10000;
    private static int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();

//     private static final Object LOCK = new Object();
//     private static final Object LOCK1 = new Object();
//     private static final Object LOCK2 = new Object();
    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final Lock WRITE_LOCK = reentrantReadWriteLock.writeLock();
    private static final Lock READ_LOCK = reentrantReadWriteLock.readLock();

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println(
                        Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }

            private void inc() {
                synchronized (this) {
//                    counter++;
                }
            }
        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        CompletionService completionService = new ExecutorCompletionService(executorService);
//
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() ->

//            Thread thread = new Thread(() ->
            {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });
//            thread.start();
//            threads.add(thread);
        }

/*
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
*/
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
//        System.out.println(counter);
        System.out.println(mainConcurrency.atomicCounter.get());

//        deadLock(LOCK1, LOCK2);
//        deadLock(LOCK2, LOCK1);
    }

    private void inc() {
//        synchronized (this) {
//        synchronized (MainConcurrency.class) {
//        lock.lock();
//        try {
            atomicCounter.incrementAndGet();
//            counter++;
//        }finally {
//            lock.unlock();
//        }
//                wait();
//                readFile
//                ...
//            }
    }

/*
    private static synchronized void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("Waiting " + Thread.currentThread().getName());
            synchronized (lock1) {
                System.out.println(Thread.currentThread().getName() +
                        ", " + Thread.currentThread().getState() );
                System.out.println("Holdiing " + Thread.currentThread().getName());
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Waiting " + Thread.currentThread().getName());
                synchronized (lock2) {
                    System.out.println(Thread.currentThread().getName() +
                            ", " + Thread.currentThread().getState());
                    System.out.println("Holdiing " + Thread.currentThread().getName());
                }
            }
        }).start();
    }
*/
}
