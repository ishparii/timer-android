package com.example.annezhao.timer.android;

import com.example.annezhao.timer.test.android.AbstractTimerActivityTest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by annezhao on 11/13/15.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml", emulateSdk = 18)

public class TimerActivityRobolectric extends AbstractTimerActivityTest {
    private TimerAdapter activity;

    @Before
    public void setUp(){
        activity = Robolectric.buildActivity(TimerAdapter.class).create().start().visible().get();
    }

    @Override
    protected TimerAdapter getActivity(){
        return activity;
    }

    @Override
    protected void runUiThreadTasks(){
        Robolectric.runUiThreadTasks();
    }
}
