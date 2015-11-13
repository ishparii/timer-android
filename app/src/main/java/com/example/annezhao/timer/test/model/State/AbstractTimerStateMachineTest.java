package com.example.annezhao.timer.test.model.State;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import com.example.annezhao.timer.Model.Clock.ClockModel;
import com.example.annezhao.timer.Model.Clock.OnTickListener;
import com.example.annezhao.timer.Model.State.TimerStateMachine;
import com.example.annezhao.timer.Model.Time.TimeModel;
import com.example.annezhao.timer.R;
import com.example.annezhao.timer.common.TimerUIUpdateListener;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by HongbinSun on 11/12/15.
 */
public abstract class AbstractTimerStateMachineTest {

    private TimerStateMachine model;

    private UnifiedMockDependency dependency;

    @Before
    public void setUp() throws Exception {
        dependency = new UnifiedMockDependency();
    }

    @After
    public void tearDown() {
        dependency = null;
    }

    //setter for dependency injection. Usually invoked by concrete testcase subclass.

    protected void setModel(final TimerStateMachine model) {
        this.model = model;
        if (model == null) //if not in any state, meaning not entered into initial(stopped) state yet
            return;
        this.model.setUIUpdateListener(dependency);
        this.model.actionInit();
    }

    protected UnifiedMockDependency getDependency() {
        return dependency;
    }

    //verifies that we're initially in the stopped state (and told the listener about it)

    @Test
    public void testPreconditions() {
        assertEquals(R.string.Stopped, dependency.getState());
    }

    //verifies the following scenarios: time is 0, press start/stop 15 times,
    //expect time 15.

    @Test
    public void testScenarioSet() {
        assertTimeEquals(0);
        //directly invoke the button press event handler methods
        onClickRepeat(15);
        assertTimeEquals(15);
    }

    //verifies the following scenario:time is 0, press start/stop 17 times, wait 2s,
    //expect time 17, wait 3s, expect time 15, wait 2s, expect 13. wait 13s, expect 0.
    // press start/stop, expect dependency stopped. 

    @Test
    public void testScenarioRun(){
        assertTimeEquals(0);
        //directly invoke the button press event handler methods
        onClickRepeat(17);
        assertEquals(R.string.SettingTime, dependency.getState());
        assertTrue(dependency.isStarted());
        onTickRepeat(2);
        assertTimeEquals(17);
        assertEquals(R.string.SettingTime, dependency.getState());
        assertTrue(dependency.isStarted());
        onTickRepeat(3);
        assertTimeEquals(15);
        assertEquals(R.string.Timing, dependency.getState());
        assertTrue(dependency.isStarted());
        onTickRepeat(2);
        assertTimeEquals(13);
        assertEquals(R.string.Timing, dependency.getState());
        assertTrue(dependency.isStarted());
        onTickRepeat(13);
        assertTimeEquals(0);
        assertEquals(R.string.Alerting, dependency.getState());
        assertTrue(dependency.isStarted());
        model.onClick();
        assertFalse(dependency.isStarted());
    }



    //checks whether the model has invoked the expected time-keeping
    //methods on the mock object
    //create shortcut for onClick, onTick and assertEquals methods

    protected void onClickRepeat (final int n){for (int i=0; i<n; i++){model.onClick();}}

    protected void onTickRepeat (final int j){for (int i=0; i<j; i++){model.onTick();}}

    protected void assertTimeEquals(final int t){assertEquals(t, dependency.getTime());}
}
    //manually implemented mock object that unifieds the three dependencies of the
    //timer state machine model. The three dependencies correspond to the three interfaces that
    //this mock object implements.
    //

 class UnifiedMockDependency implements TimeModel, ClockModel,TimerUIUpdateListener{

        private int timeValue =-1, stateID =-1;
        private int runningTime =0;
        private boolean started = false;

        public int getTime() {return timeValue;}
        public int getState() {return stateID;}
        public boolean isStarted() {return started;}

        @Override
        public void updateTime(final int timeValue){
            this.timeValue = timeValue;
        }

        @Override
        public void updateState(final int stateID){
            this.stateID = stateID;
        }

        @Override
        public void playAlarmSound() {//// TODO: 11/13/15 how to test playSound

        }

        @Override
        public boolean inputEntered() {
            return false;
        }

        @Override
        public boolean inputValid() {
            return false;
        }

        @Override
        public int getInputRunTIme() {
            return 0;
        }

        @Override
        public void clearInput() {runningTime=0;

        }

        @Override
        public void setOnTickListener(OnTickListener listener){
            throw new UnsupportedOperationException();
        }

        @Override
        public void start() {started = true;}

        @Override
        public void stop() {started = false;}

        @Override
        public void resetRuntime(){runningTime = 0;}

        @Override
        public int getRuntime(){return runningTime;}

        @Override
        public void setRuntime(int inputRunTime){runningTime=inputRunTime;}

        @Override
        public void incRuntime(){runningTime++;}

        @Override
        public void decRuntime(){runningTime--;}

    }



