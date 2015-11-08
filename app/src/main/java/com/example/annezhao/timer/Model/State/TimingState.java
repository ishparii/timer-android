package com.example.annezhao.timer.Model.State;

import com.example.annezhao.timer.R;

/**
 * Created by annezhao on 11/4/15.
 */
public class TimingState implements States {

    private final TimerSMStateView smStateView;
    //private int timeDisplayed = 0;

    public TimingState(final TimerSMStateView smStateView){
        this.smStateView = smStateView;
    }


    @Override
    public void onClick() {
        smStateView.actionStop();
        smStateView.toStoppedState();
        smStateView.actionReset();
    }

    @Override
    public void onTick() {
        //timeDisplayed = smStateView.actionTime();
        smStateView.actionCountDown();
        if(smStateView.actionGetTime() == 0) {
            smStateView.toAlertingState();
        }
    }

    @Override
    public void updateView() {
        smStateView.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.Timing;
    }
}

