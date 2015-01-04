package com.hatenablog.shoma2da.eventdaterecorderlib;

import android.test.AndroidTestCase;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by shoma2da on 15/01/04.
 */
public class EventDateRecorderDataTest extends AndroidTestCase {

    public void testオブジェクトを生成できる() {
        EventDateRecorderData data = new EventDateRecorderData(0, 0, 0);
        assertNotNull(data);
    }

    public void testオブジェクトを生成したらパラメータが保存される() {
        EventDateRecorderData data = new EventDateRecorderData(1, 2, 3);
        assertEquals(1, data.mInitialTime);
        assertEquals(2, data.mPreviousTime);
        assertEquals(3, data.mCount);
    }

    public void test更新をかけると各値が更新される() {
        //更新日時を用意しておく
        Date now = new Date();

        //データ更新
        EventDateRecorderData data = new EventDateRecorderData(0, 0, 0);
        EventDateRecorderData newData = data.update(now);

        //検証
        assertEquals(now.getTime(), newData.mInitialTime);
        assertEquals(now.getTime(), newData.mPreviousTime);
        assertEquals(1, newData.mCount);
    }

    public void test２回更新をかけると適切に値が更新される() {
        //更新日時を用意しておく
        Date oldTime = new Date();

        //データ更新
        EventDateRecorderData data = new EventDateRecorderData(0, 0, 0);
        EventDateRecorderData tempData = data.update(oldTime);

        //更新日時を用意しておく
        Date newTime = new Date();

        //データ更新
        EventDateRecorderData newData = tempData.update(newTime);

        //検証
        assertEquals(oldTime.getTime(), newData.mInitialTime);
        assertEquals(newTime.getTime(), newData.mPreviousTime);
        assertEquals(2, newData.mCount);
    }

    public void test一度も更新していなかったら記録なしとする() {
        EventDateRecorderData data = new EventDateRecorderData();
        assertFalse(data.didRecorded());
    }

    public void test一度でも更新してたら記録ありとする() {
        EventDateRecorderData data = new EventDateRecorderData();
        assertTrue(data.update().didRecorded());
    }

    public void testDate型で前回日時の取得ができる() {
        Date now = new Date();

        EventDateRecorderData data = new EventDateRecorderData(0, now.getTime(), 0);
        assertEquals(now, data.previousDate());
    }

    public void testDate型で初回日時の取得ができる() {
        Date now = new Date();

        EventDateRecorderData data = new EventDateRecorderData(now.getTime(), 0, 0);
        assertEquals(now, data.initialDate());
    }

    public void test初回日時からの経過時間を判定できる() {
        //日時を用意
        Date date = new Date();
        Date tenSecondsAfterDate = getAfterSecondsDate(date, 10);

        //オブジェクト準備
        EventDateRecorderData data = new EventDateRecorderData(date.getTime(), 0, 0);

        //検証
        assertFalse(data.didElapsedSinceInitialRecordedDate(tenSecondsAfterDate, 9));
        assertFalse(data.didElapsedSinceInitialRecordedDate(tenSecondsAfterDate, 10));
        assertTrue(data.didElapsedSinceInitialRecordedDate(tenSecondsAfterDate, 11));
    }

    public void test更新日時からの経過時間を判定できる() {
        //日時を用意
        Date date = new Date();
        Date tenSecondsAfterDate = getAfterSecondsDate(date, 10);

        //オブジェクト準備
        EventDateRecorderData data = new EventDateRecorderData(0, date.getTime(), 0);

        //検証
        assertFalse(data.didElapsedSincePreviousRecordedDate(tenSecondsAfterDate, 9));
        assertFalse(data.didElapsedSincePreviousRecordedDate(tenSecondsAfterDate, 10));
        assertTrue(data.didElapsedSincePreviousRecordedDate(tenSecondsAfterDate, 11));
    }

    private Date getAfterSecondsDate(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }
}