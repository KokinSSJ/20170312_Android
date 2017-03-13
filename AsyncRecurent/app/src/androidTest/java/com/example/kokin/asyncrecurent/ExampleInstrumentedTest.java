package com.example.kokin.asyncrecurent;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.kokin.asyncrecurent", appContext.getPackageName());
    }

    @Test
    public void recurentFunctionTest(){
        assertEquals(new Integer(34), RecurentFunction.function(4));
        assertEquals(new Integer(122), RecurentFunction.function(5));
//        assertEquals(new Integer(122), RecurentFunction.function(6));

        int i =200;
        while (i-- >0){
            assertTrue(RecurentFunction.function(i)%2==0);
        }
    }
}
