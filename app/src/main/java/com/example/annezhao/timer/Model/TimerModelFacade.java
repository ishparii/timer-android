package com.example.annezhao.timer.Model;

import android.os.Parcelable;

import com.example.annezhao.timer.common.TimerUIListener;
import com.example.annezhao.timer.common.TimerUIUpdateListenerSource;

/**
 * Created by annezhao on 11/4/15.
 */
public interface TimerModelFacade extends TimerUIListener, TimerUIUpdateListenerSource, Parcelable {
    void onStart();
}
