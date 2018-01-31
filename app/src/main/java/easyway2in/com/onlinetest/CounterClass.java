package easyway2in.com.onlinetest;

import android.app.Activity;
import android.content.Context;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class CounterClass extends CountDownTimer {
    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    TextView txtView;
    Context ctx;
    ConceptActivity conceptActivity;
    OnTaskCompleted timefinish;
    public CounterClass(long millisInFuture, long countDownInterval, Context ctx) {
        super(millisInFuture, countDownInterval);
        this.ctx=ctx;
        timefinish=(OnTaskCompleted)ctx;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        long millis = millisUntilFinished;

        String hms = String.format(

                "%02d:%02d:%02d",

                TimeUnit.MILLISECONDS.toHours(millis),

                TimeUnit.MILLISECONDS.toMinutes(millis)

                        - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS

                                .toHours(millis)),

                TimeUnit.MILLISECONDS.toSeconds(millis)

                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS

                                .toMinutes(millis)));

        System.out.println(hms);
         txtView = (TextView) ((Activity)ctx).findViewById(R.id.timers);
        txtView.setText(hms);


    }

    @Override
    public void onFinish() {
        txtView.setText("Time is up");
        timefinish.onTaskCompleted("00:00:00");
    }
}
