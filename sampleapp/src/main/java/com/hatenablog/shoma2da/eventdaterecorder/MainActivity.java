package com.hatenablog.shoma2da.eventdaterecorder;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.hatenablog.shoma2da.eventdaterecorderlib.EventDateRecorder;


public class MainActivity extends ActionBarActivity {

    private EventDateRecorder mRecorder;

    private TextView mCountTextView;
    private TextView mInitialDateTextView;
    private TextView mPreviousDateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初期値
        mRecorder = new EventDateRecorder(this, "sample");

        //表示テキストを取得
        mCountTextView = (TextView)findViewById(R.id.countTextView);
        mInitialDateTextView = (TextView)findViewById(R.id.initialDateTextView);
        mPreviousDateTextView = (TextView)findViewById(R.id.previousDateTextView);

        updateTexts();

        //ボタンの動作を設定
        findViewById(R.id.recordButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecorder.record();
                updateTexts();
            }
        });
        findViewById(R.id.clearButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecorder.clear();
                updateTexts();
            }
        });
    }

    void updateTexts() {
        mCountTextView.setText("Count : " + mRecorder.recordedCount());
        mInitialDateTextView.setText("Initial Date : " + mRecorder.initialRecordedDate());
        mPreviousDateTextView.setText("Previous Date : " + mRecorder.previousRecordedDate());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mRecorder.clear();
    }
}
