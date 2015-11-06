package com.example.annezhao.timer;

import com.example.annezhao.timer.Model.Clock.ClockModel;
import com.example.annezhao.timer.Model.Clock.DefaultClockModel;
import com.example.annezhao.timer.Model.State.DefaultTimerStateMachine;
import com.example.annezhao.timer.Model.State.TimerStateMachine;
import com.example.annezhao.timer.Model.Time.DefaulTimeModel;
import com.example.annezhao.timer.Model.Time.TimeModel;
import com.example.annezhao.timer.common.TimerUIUpdateListener;

/**
 * Created by annezhao on 11/4/15.
 */
public class ConcreteTimerModelFacade implements TimerModelFacade{

    private TimerStateMachine timerStateMachine;
    private TimeModel timeModel;
    private ClockModel clockModel;

    public ConcreteTimerModelFacade(){
        timeModel = new DefaulTimeModel();
        clockModel = new DefaultClockModel();
        timerStateMachine = new DefaultTimerStateMachine(timeModel,clockModel);
        clockModel.setOnTickListener(timerStateMachine);
    }

    @Override
    public void onStart() {
        timerStateMachine.actionInit();
    }
    @Override
    public void onClick() {
        timerStateMachine.onClick();
    }

    @Override
    public void setUIUpdateListener(TimerUIUpdateListener listener) {
        timerStateMachine.setUIUpdateListener(listener);
    }
}
