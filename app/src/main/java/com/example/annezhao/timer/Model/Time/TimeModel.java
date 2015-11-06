package com.example.annezhao.timer.Model.Time;

/**
 * Created by annezhao on 10/29/15.
 */
public interface TimeModel {
    void resetRuntime();
    int getRuntime();
    void incRuntime();
    void decRuntime();
}
