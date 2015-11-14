package com.example.annezhao.timer.Model.State;

import com.example.annezhao.timer.Model.Clock.OnTickListener;
import com.example.annezhao.timer.common.TimerUIListener;

/**
 * Created by annezhao on 10/28/15.
 */
public interface States extends TimerUIListener, OnTickListener {
    void updateView();
    int getId();
}
