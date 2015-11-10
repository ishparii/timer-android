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
    void actionCountDown();
    void actionAlert();
    void actionUpdateView();
    int actionGetTime();
    int actionGetInputTime();
    void actionSetTime();


    //state-dependent UI updates
    void updateUIRuntime();

}

