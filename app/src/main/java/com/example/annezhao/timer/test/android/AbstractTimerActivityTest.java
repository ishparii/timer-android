package com.example.annezhao.timer.test.android;

import android.widget.Button;
import android.widget.TextView;

import com.example.annezhao.timer.R;
import com.example.annezhao.timer.android.TimerAdapter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by annezhao on 11/13/15.
 */
public abstract class AbstractTimerActivityTest {

    /**
     * Verifies that the activity under test can be launched.
     */
    @Test
    public void testActivityCheckTestCaseSetUpProperly(){
        assertNotNull("activity launch failed", getActivity());
    }

    /**
     * Verifies the following scenario: time is 0.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioInit() throws Throwable{
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertEquals(0, getDisplayedValue());
            }
        });
    }

    /**
     * Verifies the following scenario: time is 0, click button 10 times, expect display 10.
     * Wait 3 seconds, expect display 10. Wait 4 more seconds, expect display 6.
     *
     * @throws Throwable
     */
    @Test
    public void testActivityScenarioRunning() throws Throwable{
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertEquals(0, getDisplayedValue());
                for (int i = 0; i < 10; i++) {
                    assertTrue(getButton().performClick());
                }
            }
        });
        Thread.sleep(300);
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertEquals(10, getDisplayedValue());
            }
        });
        Thread.sleep(400);
        runUiThreadTasks();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                assertEquals(6,getDisplayedValue());
            }
        });
    }



    protected abstract TimerAdapter getActivity();

    protected int textViewToInt(final TextView textView){
        final int textInt = Integer.parseInt(textView.getText().toString());
        return textInt;
    }

    protected int getDisplayedValue() {
        final TextView textView = (TextView) getActivity().findViewById(R.id.displayTime);
        return textViewToInt(textView);
    }

    protected Button getButton(){
        final Button button = (Button) getActivity().findViewById(R.id.button);
        return button;
    }

    protected void runUiThreadTasks(){

    }
}