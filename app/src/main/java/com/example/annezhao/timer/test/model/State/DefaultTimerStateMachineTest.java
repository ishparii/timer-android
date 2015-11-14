package com.example.annezhao.timer.test.model.State;

import com.example.annezhao.timer.Model.State.DefaultTimerStateMachine;

import org.junit.After;
import org.junit.Before;

/**
 * Created by HongbinSun on 11/12/15.
 */
public class DefaultTimerStateMachineTest extends AbstractTimerStateMachineTest
{
    @Before
    public void setUp() throws Exception{
        super.setUp();
        setModel(new DefaultTimerStateMachine(getDependency(), getDependency()));
    }

    @After
    public void tearDown(){
        setModel(null);
        super.tearDown();
    }
}
