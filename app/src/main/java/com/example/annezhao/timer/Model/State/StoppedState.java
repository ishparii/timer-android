package com.example.annezhao.timer.Model.State;

import com.example.annezhao.timer.R;

/**
 * Created by annezhao on 11/4/15.
 */
public class StoppedState implements States{

    private final TimerSMStateView smStateView;

    public StoppedState(final TimerSMStateView smStateView){
        this.smStateView = smStateView;
    }

    @Override
    public void updateView() {
        smStateView.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.Stopped;
    }

    @Override
    public void onTick() {
        throw new UnsupportedOperationException("onTick");
    }

    @Override
    public void onClick() {
        smStateView.actionStart();
        smStateView.toSettingTimeState();
        smStateView.actionIncrease();
    }
}


