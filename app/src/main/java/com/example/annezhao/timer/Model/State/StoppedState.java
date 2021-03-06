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
        smStateView.clearInput();
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

        if (!smStateView.inputEntered()){
            smStateView.toSettingTimeState();
            smStateView.actionIncrease();
        }
        else if (smStateView.inputEntered() && smStateView.inputValid()) {
            smStateView.actionSetTime();
            smStateView.actionUpdateView();
            smStateView.actionAlert();
            smStateView.toTimingState();
        }
        else{
            smStateView.actionStop();
        }
    }

}


