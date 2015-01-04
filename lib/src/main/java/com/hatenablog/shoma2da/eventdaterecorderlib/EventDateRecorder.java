package com.hatenablog.shoma2da.eventdaterecorderlib;

import android.content.Context;

/**
 * Created by shoma2da on 15/01/04.
 */
public class EventDateRecorder {

    private Context mContext;
    private String mKey;

    public static EventDateRecorder load(Context context, String key) {
        return new EventDateRecorder(context, key);
    }

    EventDateRecorder(Context context, String key) {
        mContext = context;
        mKey = key;
    }

    public void record() {

    }

}
