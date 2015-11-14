package com.example.annezhao.timer.test.model.time;


import static com.example.annezhao.timer.common.Constants.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.annezhao.timer.Model.Time.TimeModel;

/**
 * Created by HongbinSun on 11/12/15.
 * //testcase superclass for the time model abstraction.
 * this is a simple unit test of an object without dependencies.
 */
public class AbstractTimeModelTest {
    private TimeModel model;

    //setter for dependency injection. Usually invoked by concrete testcase subclass.
    protected void setModel(final TimeModel model){
        this.model = model;
    }

    //verifies that runtime is initially 0;
    @Test
    public void testPreconditions(){
        assertEquals(0, model.getRuntime());
    }

    //verifies that runtime is incremented correctly
    @Test
    public void testIncrementRuntimeOne(){
        final int dt = model.getRuntime();
        model.incRuntime();
        assertEquals(dt + SEC_PER_TICK, model.getRuntime());
    }

    //verifies that runtime is decremented correctly
    @Test
    public void testDecrementRuntimeOne(){

        final int dt = model.getRuntime();
        model.decRuntime();
        assertEquals(dt - SEC_PER_TICK, model.getRuntime());
    }


    //verifies that runtime turns over correctly
    @Test
    public void testIncrementRuntimeMany(){
        final int dt = model.getRuntime();
        for (int i = 0; i < 99; i++){
            model.incRuntime();
        }
        assertEquals(dt + 99 * SEC_PER_TICK, model.getRuntime());
    }

    //verifies that runtime turns over correctly
    @Test
    public void testDecrementRuntimeMany(){
        final int dt = model.getRuntime();
        for (int j = 0; j < 99; j++){
            model.decRuntime();
        }
        assertEquals(dt - 99 * SEC_PER_TICK, model.getRuntime());
    }
}
