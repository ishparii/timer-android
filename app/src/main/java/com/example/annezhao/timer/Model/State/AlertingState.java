package com.example.annezhao.timer.Model.State;

import com.example.annezhao.timer.R;

/**
 * Created by annezhao on 11/4/15.
 */
public class AlertingState implements States {

    private final TimerSMStateView smStateView;

    public AlertingState(final TimerSMStateView smStateView){

        this.smStateView = smStateView;
    }

    @Override
    public void onClick() {
        smStateView.actionStop();
        smStateView.toStoppedState();
    }

    @Override
    public void onTick() {
        smStateView.actionAlert();
        //throw new UnsupportedOperationException("OnTick"); //infinity alarm
    }

    @Override
    public void updateView() {
        smStateView.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.Alerting;
    }
}
