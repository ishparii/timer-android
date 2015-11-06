package com.example.annezhao.timer;

import com.example.annezhao.timer.common.TimerUIListener;
import com.example.annezhao.timer.common.TimerUIUpdateListener;
import com.example.annezhao.timer.common.TimerUIUpdateListenerSource;

/**
 * Created by annezhao on 11/4/15.
 */
public interface TimerModelFacade extends TimerUIListener, TimerUIUpdateListenerSource {
    void onStart();
}
