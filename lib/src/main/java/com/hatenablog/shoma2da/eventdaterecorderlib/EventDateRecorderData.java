package com.hatenablog.shoma2da.eventdaterecorderlib;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by shoma2da on 15/01/04.
 */
public class EventDateRecorderData {

    static final long EMPTY_INITIAL_TIME = 0;

    final long mInitialTime;
    final long mPreviousTime;
    final int mCount;

    EventDateRecorderData(long initialTime, long previousTime, int count) {
        mInitialTime = initialTime;
        mPreviousTime = previousTime;
        mCount = count;
    }

    EventDateRecorderData update() {
        Date now = new Date();

        long newInitialTime = mInitialTime;
        if (newInitialTime == EMPTY_INITIAL_TIME) {
            newInitialTime = now.getTime();
        }

        return new EventDateRecorderData(newInitialTime, now.getTime(), mCount + 1);
    }

    boolean didRecorded() {
        return mCount != 0;
    }

    Date previousDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mPreviousTime);
        return calendar.getTime();
    }

    Date initialDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mInitialTime);
        return calendar.getTime();
    }

    boolean didElapsedSincePreviousRecordedDate(int seconds) {
        Date now = new Date();
        long diffTimeInMillis = Math.abs(mPreviousTime - now.getTime());
        return seconds < (diffTimeInMillis / 1000);
    }

    boolean didElapsedSinceInitialRecordedDate(int seconds) {
        Date now = new Date();
        long diffTimeInMillis = Math.abs(mInitialTime - now.getTime());
        return seconds < (diffTimeInMillis / 1000);
    }

}
