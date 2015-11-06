package com.example.annezhao.timer.Model.State;

import com.example.annezhao.timer.Model.Clock.OnTickListener;
import com.example.annezhao.timer.common.TimerUIListener;
import com.example.annezhao.timer.common.TimerUIUpdateListenerSource;

/**
 * Created by annezhao on 11/3/15.
 */
public interface TimerStateMachine extends TimerUIListener, OnTickListener, TimerUIUpdateListenerSource,TimerSMStateView {
}