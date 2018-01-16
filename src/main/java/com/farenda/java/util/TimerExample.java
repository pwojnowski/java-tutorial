package com.farenda.java.util;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerExample {

    private static class WebClawlerTask extends TimerTask {

        private int count = 0;

        @Override
        public void run() {
            System.out.println("Crawling the web count: " + ++count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TimerTask task = new WebClawlerTask();
        int delay = 100;
        int period = 200;

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, delay, period);

        TimeUnit.SECONDS.sleep(2);

        System.out.println("Stopping the timer.");
        timer.cancel();
    }
}
