package com.example.annezhao.timer.Model.Clock;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by annezhao on 10/29/15.
 */
public class DefaultClockModel implements ClockModel {

    private Timer timer;
    private OnTickListener listener;

    @Override
    public void start() {
        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                listener.onTick();
            }
        },1000,1000);

    }

    @Override
    public void stop() {
        timer.cancel();
    }

    @Override
    public void setOnTickListener(final OnTickListener listener) {
        this.listener = listener;
    }
}
