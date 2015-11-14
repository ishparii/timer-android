package com.example.annezhao.timer.Model.Time;

import static com.example.annezhao.timer.common.Constants.*;

/**
 * Created by annezhao on 10/30/15.
 */

public class DefaultTimeModel implements TimeModel {

    private int runningTime = 0;

    @Override
    public void resetRuntime() {
        runningTime = 0;
    }

    @Override
    public int getRuntime() {
        return runningTime;
    }

    @Override
    public void setRuntime(int inputRunTime) {
        runningTime = inputRunTime;
    }

    @Override
    public void incRuntime() {
        runningTime = runningTime + SEC_PER_TICK;
    }

    @Override
    public void decRuntime() {
        runningTime = runningTime - SEC_PER_TICK;
    }
}
