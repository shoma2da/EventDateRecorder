EventDateRecorder
===========

EventDateRecorder record event date and count persistently.

## Installation

under construction...

## Usage

At first you need to import EventDateRecorder class.
```java
import com.hatenablog.shoma2da.eventdaterecorderlib.EventDateRecorder;
```

You can record event.
```java
EventDateRecorder recorder = new EventDateRecorder(context, "my_event");
recorder.record();
```

You can access to recorded data.
```java
EventDateRecorder recorder = new EventDateRecorder(context, "my_event");

// return: boolean
recorder.didRecorded();

// return: int
recorder.recordedCount();

// return java.util.Date : initial event record date.
recorder.initialRecordedDate();

// return java.util.Date : previous event record date.
recorder.previousRecordedDate();

// return: boolean
recorder.didElapsedSinceInitialRecordedDate(60 * 60);

// return: boolean
recorder.didElapsedSincePreviousRecordedDate(60 * 60);
```

You can clear data.
```objc
EventDateRecorder recorder = new EventDateRecorder(context, "my_event");
recorder.clear();
```
