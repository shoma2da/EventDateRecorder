EventDateRecorder
===========

EventDateRecorder record event date and count persistently.

If you want to get a sample app, please clone this repo : 'sampleapp' is a sample application.

iOS project is [here](https://github.com/soragoto/SRGEventDateRecorder).

## Installation

Add below lines to your `build.gradle`.

```java
repositories {
    maven { url 'https://raw.githubusercontent.com/shoma2da/EventDateRecorder/master/lib/repository/' }
}
dependencies {
    compile 'com.hatenablog.shoma2da:eventdaterecorderlib:0.1.0'
}
```

## Usage

At first you need to import EventDateRecorder class.
```java
import com.hatenablog.shoma2da.eventdaterecorderlib.EventDateRecorder;
```

You can record event.
```java
EventDateRecorder recorder = EventDateRecorder.load(context, "my_event");
recorder.record();
```

You can access to recorded data.
```java
EventDateRecorder recorder = EventDateRecorder.load(context, "my_event");

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
EventDateRecorder recorder = EventDateRecorder.load(context, "my_event");
recorder.clear();
```
