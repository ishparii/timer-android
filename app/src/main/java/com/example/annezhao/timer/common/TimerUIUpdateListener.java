package com.example.annezhao.timer.common;

/**
 * Created by annezhao on 10/29/15.
 */
public interface TimerUIUpdateListener {
    void updateTime(int timeValue); //a listener for the update of time
    void updateState(int stateID); //a listener for the update of state
}
