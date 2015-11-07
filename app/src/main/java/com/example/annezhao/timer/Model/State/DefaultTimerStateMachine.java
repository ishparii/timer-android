package com.example.annezhao.timer.Model.State;

import com.example.annezhao.timer.Model.Clock.ClockModel;
import com.example.annezhao.timer.Model.Time.TimeModel;
import com.example.annezhao.timer.common.TimerUIUpdateListener;

/**
 * Created by annezhao on 11/4/15.
 */
public class DefaultTimerStateMachine implements TimerStateMachine{

    private final TimeModel timeModel;
    private final ClockModel clockModel;

    public DefaultTimerStateMachine (final TimeModel timeModel, final ClockModel clockModel){
        this.timeModel = timeModel;
        this.clockModel = clockModel;
    }

    //internal state of adaptor component, required for the State pattern;
    private States state;
    private TimerUIUpdateListener uiUpdateListener; //updateTime(int timeValue), updateState(int stateId)

    @Override
    public void setUIUpdateListener(final TimerUIUpdateListener uiUpdateListener){
        this.uiUpdateListener = uiUpdateListener;
    }

    protected void setState(final States state){
        this.state = state;
        uiUpdateListener.updateState(state.getId());
    }

    // forward event uiUpdateListener methods to the current state
    // these must be synchronized because events can come from the
    // UI thread or the timer thread
    @Override
    public synchronized void onClick() {
        state.onClick();
    }
    @Override
    public synchronized void onTick() {
        state.onTick();
    }

    @Override
    public void updateUIRuntime() {
        uiUpdateListener.updateTime(timeModel.getRuntime());
    }


    //known states
    private final States STOPPED = new StoppedState(this);
    private final States INCREASING = new SettingTimeState(this);
    private final States TIMING = new TimingState(this);
    private final States ALERTING = new AlertingState(this);


    //transition
    @Override
    public void toStoppedState() {
        setState(STOPPED);
    }

    @Override
    public void toSettingTimeState() {
        setState(INCREASING);
    }

    @Override
    public void toTimingState() {
        setState(TIMING);
    }

    @Override
    public void toAlertingState() {
        setState(ALERTING);
    }


    //actions
    @Override public void actionInit() { toStoppedState(); actionReset(); }
    @Override public void actionReset() { timeModel.resetRuntime(); actionUpdateView(); }
    @Override public void actionStart(){
        clockModel.start();
    }
    @Override public void actionStop() { clockModel.stop(); /*timeModel.resetRuntime();*/ }
    @Override public void actionIncrease(){ timeModel.incRuntime(); actionUpdateView(); }

    @Override public int actionTime(){
        timeModel.decRuntime();  //countdown
        actionUpdateView();
        return timeModel.getRuntime();
    }

    @Override
    public void actionAlert(){
        // TODO: 11/3/15 add alerting function

    }

    @Override
    public void actionUpdateView(){
        state.updateView();
    }

}
