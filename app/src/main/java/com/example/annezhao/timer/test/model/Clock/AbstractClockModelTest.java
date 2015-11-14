package com.example.annezhao.timer.test.model.Clock;


import com.example.annezhao.timer.Model.Clock.ClockModel;
import com.example.annezhao.timer.Model.Clock.OnTickListener;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by HongbinSun on 11/12/15.
 * //it is abstract because it can not be instantiated
 */
public abstract class AbstractClockModelTest {

    //setter for dependency injection
    private ClockModel model;
    protected void setModel(final ClockModel model){
        this.model = model;
    }

    //verifies that a stopped clock model does not emit any tick event
    @Test
    public void testStopped() throws InterruptedException{

        //timer and clock has different thread
        //it is a thread-safe object
        final AtomicInteger i = new AtomicInteger(0);
        model.setOnTickListener(new OnTickListener() {
            @Override
            public void onTick(){i.incrementAndGet();}
        });
        Thread.sleep(6500); //another thread
        assertEquals(0, i.get());}

    //verifies that in the clock model, if the clock is running, tick event
    //takes place very 1 second
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
