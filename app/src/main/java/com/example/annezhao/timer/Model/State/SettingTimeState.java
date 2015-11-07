package com.example.annezhao.timer.Model.State;

import com.example.annezhao.timer.R;

/**
 * Created by annezhao on 11/4/15.
 */
public class SettingTimeState implements States {

    private final TimerSMStateView smStateView;
    private int count = 0;

    public SettingTimeState(final TimerSMStateView smStateView){
                this.smStateView = smStateView;
        }

    @Override
    public void onClick() {
        smStateView.actionIncrease();
        smStateView.toStoppedState();
        //count = 0;
        //if(count<=3){  }
    }

    @Override
    public void onTick() { //from the clock model, it's the background tick
        count ++;
        if(count==3){
            smStateView.toTimingState();
        }
    }

    @Override
    public void updateView() {
        smStateView.updateUIRuntime();
    }

    @Override
    public int getId() {
        return R.string.SettingTime;
    }
}
