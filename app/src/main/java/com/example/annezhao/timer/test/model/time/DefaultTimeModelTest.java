package com.example.annezhao.timer.test.model.time;

import com.example.annezhao.timer.Model.Time.DefaultTimeModel;

import org.junit.After;
import org.junit.Before;

/**
 * Created by HongbinSun on 11/12/15.
 */
public class DefaultTimeModelTest extends AbstractTimeModelTest{

    @Before
    public void setUp() throws Exception{
        setModel(new DefaultTimeModel());
    }

    @After
    public void tearDown() throws Exception{
        setModel(null);
    }
}
