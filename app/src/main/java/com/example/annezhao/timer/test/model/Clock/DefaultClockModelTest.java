package com.example.annezhao.timer.test.model.Clock;

import org.junit.Before;
import org.junit.After;

import com.example.annezhao.timer.Model.Clock.DefaultClockModel;
/**
 * Created by HongbinSun on 11/12/15.
 * //concrete testcase subclass for the default clock model implementation.
 */
public class DefaultClockModelTest extends AbstractClockModelTest{

    @Before
    public void setUp() throws Exception{
        setModel(new DefaultClockModel());
    }

    @After
    public void tearDown() throws Exception{
        setModel(null);
    }
}
