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
        if (smStateView.actionGetTime() < 99) {
            smStateView.actionIncrease();
        }
        count = 0; //reset count
    }

    @Override
    public void onTick() { //from the clock model, it's the background tick
        count ++;
        if(count==3){
            smStateView.toTimingState();
            count = 0;
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
