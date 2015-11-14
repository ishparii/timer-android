package com.example.annezhao.timer.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.annezhao.timer.Model.Clock.ClockModel;
import com.example.annezhao.timer.Model.Clock.DefaultClockModel;
import com.example.annezhao.timer.Model.State.DefaultTimerStateMachine;
import com.example.annezhao.timer.Model.State.TimerStateMachine;
import com.example.annezhao.timer.Model.Time.DefaultTimeModel;
import com.example.annezhao.timer.Model.Time.TimeModel;
import com.example.annezhao.timer.common.TimerUIUpdateListener;
import com.example.annezhao.timer.R;


/**
 * Created by annezhao on 11/4/15.
 */
public class ConcreteTimerModelFacade implements TimerModelFacade {

    private TimerStateMachine timerStateMachine;
    private TimeModel timeModel;
    private ClockModel clockModel;

    public ConcreteTimerModelFacade(){
        timeModel = new DefaultTimeModel();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(timeModel.getRuntime());
        dest.writeInt(timerStateMachine.getStatesId());
        //dest.writeParcelable(timerStateMachine.getState(), 0);
    }

    public static final Parcelable.Creator<ConcreteTimerModelFacade> CREATOR
            = new Parcelable.Creator<ConcreteTimerModelFacade>() {
        public ConcreteTimerModelFacade createFromParcel(Parcel in) {
            return new ConcreteTimerModelFacade(in);
        }

        public ConcreteTimerModelFacade[] newArray(int size) {
            return new ConcreteTimerModelFacade[size];
        }
    };

    private ConcreteTimerModelFacade(Parcel in) {
        timeModel = new DefaultTimeModel();
        clockModel = new DefaultClockModel();
        timerStateMachine = new DefaultTimerStateMachine(timeModel,clockModel);
        clockModel.setOnTickListener(timerStateMachine);
        readFromParcel(in);
        //timeModel.setRuntime(in.readInt());
        //timerStateMachine.setState((States) in.readParcelable(States.class.getClassLoader()));
        timerStateMachine.actionUpdateView();
        timerStateMachine.updateUIRuntime();
    }

    private void readFromParcel(Parcel in) {
        timeModel.setRuntime(in.readInt());
        int currentState = in.readInt();
        switch (currentState) {
            case R.string.Stopped:
                timerStateMachine.toStoppedState();
            case R.string.SettingTime:
                timerStateMachine.toSettingTimeState();
            case R.string.Timing:
                timerStateMachine.toTimingState();
            case R.string.Alerting:
                timerStateMachine.toAlertingState();
            default:
                break;
        }

    }
}
