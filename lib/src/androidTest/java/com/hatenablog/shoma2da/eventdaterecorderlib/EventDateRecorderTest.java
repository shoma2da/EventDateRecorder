package com.hatenablog.shoma2da.eventdaterecorderlib;

import android.test.AndroidTestCase;

/**
 * Created by shoma2da on 15/01/04.
 */
public class EventDateRecorderTest extends AndroidTestCase {

    private EventDateRecorder mRecorder;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mRecorder = EventDateRecorder.load(getContext(), "sample");
    }

    public void test問題なくRecordできる() {
        try {
            mRecorder.record();
            assertTrue(true);
        } catch (Exception e) {
            fail();
        }
    }

}
