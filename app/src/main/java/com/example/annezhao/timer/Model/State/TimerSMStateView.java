package com.example.annezhao.timer.Model.State;

/**
 * Created by annezhao on 11/3/15.
 */
public interface TimerSMStateView {
    //transitions
    void toSettingTimeState();
    void toTimingState();
    void toAlertingState();
    void toStoppedState();

    //actions
    void actionInit();
    void actionReset();
    void actionStart();
    void actionStop();
    void actionIncrease();
    int actionTime();
    void actionAlert();
    void actionUpdateView();


    //state-dependent UI updates
    void updateUIRuntime();

}

