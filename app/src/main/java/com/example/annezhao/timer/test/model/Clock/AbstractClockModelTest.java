package com.example.annezhao.timer.test.model.Clock;


import static org.junit.Assert.AssertEquals;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import com.example.annezhao.timer.Model.Clock.ClockModel;
import com.example.annezhao.timer.Model.Clock.OnTickListener;

import dalvik.annotation.TestTargetClass;

/**
 * Created by HongbinSun on 11/12/15.
 */
public abstract class AbstractClockModelTest {

    //setter for dependency injection.

    private ClockModel model;
    protected void setModel(final ClockModel model){
        this.model= model;
    }

    //verifies that a stopped clock model does not emit any tick event.

    @Test
    public void teststopped() throws InterruptedException{
        //timer and clock has different thread

        final AtomicInteger i = new AtomicInteger(0);
        model.setOnTickListener(new OnTickListener() {
            @Override
            public void onTick(){i.incrementAndGet();}
        });
        model.start();
        Thread.sleep(6500);
        assertEquals(0, i.get());}

    @Test
    public void testRunning() throws InterruptedException{
        final AtomicInteger j = new AtomicInteger(0);
        model.setOnTickListener(new OnTickListener() {
            @Override
            public void onTick() {
                j.incrementAndGet();
            }
        });
        model.start();
        Thread.sleep(6500);
        model.stop();
        assertEquals(6, j.get());
    }
}
