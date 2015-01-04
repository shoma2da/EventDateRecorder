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

    EventDateRecorderData() {
        this(EMPTY_INITIAL_TIME, 0, 0);
    }

    EventDateRecorderData(long initialTime, long previousTime, int count) {
        mInitialTime = initialTime;
        mPreviousTime = previousTime;
        mCount = count;
    }

    EventDateRecorderData update() {
        return update(new Date());
    }

    EventDateRecorderData update(Date date) {
        long newInitialTime = mInitialTime;
        if (newInitialTime == EMPTY_INITIAL_TIME) {
            newInitialTime = date.getTime();
        }

        return new EventDateRecorderData(newInitialTime, date.getTime(), mCount + 1);
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
        return didElapsedSincePreviousRecordedDate(new Date(), seconds);
    }

    boolean didElapsedSincePreviousRecordedDate(Date date, int seconds) {
        long diffTimeInMillis = Math.abs(mPreviousTime - date.getTime());
        return seconds > (diffTimeInMillis / 1000);
    }

    boolean didElapsedSinceInitialRecordedDate(int seconds) {
        return didElapsedSinceInitialRecordedDate(new Date(), seconds);
    }

    boolean didElapsedSinceInitialRecordedDate(Date date, int seconds) {
        long diffTimeInMillis = Math.abs(mInitialTime - date.getTime());
        return seconds > (diffTimeInMillis / 1000);
    }

}
