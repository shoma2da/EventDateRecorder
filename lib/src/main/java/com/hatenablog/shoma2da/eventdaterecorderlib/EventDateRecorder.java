package com.hatenablog.shoma2da.eventdaterecorderlib;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

/**
 * Created by shoma2da on 15/01/04.
 */
public class EventDateRecorder {

    private static final String FILE_NAME = "com.hataneblog.shoma2da.eventdaterecorder.file";
    private static final String KEY_INITIAL_TIME_SUFFIX = "_initialtime";
    private static final String KEY_PREVIOUS_TIME_SUFFIX = "_previoustime";
    private static final String KEY_COUNT_SUFFIX = "_count";

    private Context mContext;
    private SharedPreferences mPreferences;
    private String mKey;
    private EventDateRecorderData mData;

    public static EventDateRecorder load(Context context, String key) {
        return new EventDateRecorder(context, key);
    }

    EventDateRecorder(Context context, String key) {
        mContext = context;
        mPreferences = mContext.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        mKey = key;
    }

    public void record() {
        EventDateRecorderData newData = getData().update();
        mData = newData;
        mPreferences.edit().putLong(mKey + KEY_INITIAL_TIME_SUFFIX, newData.mInitialTime)
                           .putLong(mKey + KEY_PREVIOUS_TIME_SUFFIX, newData.mPreviousTime)
                           .putInt(mKey + KEY_COUNT_SUFFIX, newData.mCount)
                           .apply();
    }

    EventDateRecorderData getData() {
        if (mData == null) {
            long initialTime = mPreferences.getLong(mKey + KEY_INITIAL_TIME_SUFFIX, EventDateRecorderData.EMPTY_INITIAL_TIME);
            long previousTime = mPreferences.getLong(mKey + KEY_PREVIOUS_TIME_SUFFIX, 0);
            int count = mPreferences.getInt(mKey + KEY_COUNT_SUFFIX, 0);
            mData = new EventDateRecorderData(initialTime, previousTime, count);
            return mData;
        }
        return mData;
    }

    public boolean didRecorded() {
        return getData().didRecorded();
    }

    public int recordedCount() {
        return getData().mCount;
    }

    public Date previousRecordedDate() {
        return getData().previousDate();
    }

    public Date initialRecordedDate() {
        return getData().initialDate();
    }

    public boolean didElapsedSincePreviousRecordedDate(int seconds) {
        return getData().didElapsedSincePreviousRecordedDate(seconds);
    }

    public boolean didElapsedSinceInitialRecordedDate(int seconds) {
        return getData().didElapsedSinceInitialRecordedDate(seconds);
    }
}
