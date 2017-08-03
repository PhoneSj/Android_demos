package mvp.phone.com.myapplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "thread test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button1).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new MyAsyncTask("AsyncTast#1").execute("");
                new MyAsyncTask("AsyncTast#2").execute("");
                new MyAsyncTask("AsyncTast#3").execute("");
                new MyAsyncTask("AsyncTast#4").execute("");
                new MyAsyncTask("AsyncTast#5").execute("");
                new MyAsyncTask("AsyncTast#6").execute("");
            }
        });

        findViewById(R.id.button2).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new MyAsyncTask("AsyncTast#1").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsyncTask("AsyncTast#2").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsyncTask("AsyncTast#3").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsyncTask("AsyncTast#4").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsyncTask("AsyncTast#5").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
                new MyAsyncTask("AsyncTast#6").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "");
            }
        });

        findViewById(R.id.button3).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent service = new Intent(MainActivity.this, LocalIntentService.class);
                service.putExtra("task_action", "TASK1");
                startService(service);
                service.putExtra("task_action", "TASK2");
                startService(service);
                service.putExtra("task_action", "TASK3");
                startService(service);
            }
        });

        final Runnable command = new Runnable() {

            @Override
            public void run() {
                SystemClock.sleep(2000);
                Log.i(TAG, "command finished");
            }
        };

        findViewById(R.id.button4).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ExecutorService fixedThreadPool = Executors.newFixedThreadPool(4);
                fixedThreadPool.execute(command);
            }
        });
        findViewById(R.id.button5).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
                cacheThreadPool.execute(command);
            }
        });
        findViewById(R.id.button6).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(4);
                //				scheduledThreadPool.execute(command);

                //				scheduledThreadPool.schedule(command, 2000, TimeUnit.MILLISECONDS);

                //固定频率（这一次开始执行时间-上一次开始执行时间）
                //				scheduledThreadPool.scheduleAtFixedRate(command, 2000, 4000, TimeUnit.MILLISECONDS);

                //固定间隔（这一次开始执行时间-上一个执行结束时间）
                scheduledThreadPool.scheduleWithFixedDelay(command, 2000, 4000, TimeUnit.MILLISECONDS);
            }
        });
        findViewById(R.id.button7).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
                singleThreadExecutor.execute(command);
            }
        });
    }

    private static class MyAsyncTask extends AsyncTask<String, Integer, String> {

        private String mName = "AsyncTast";

        public MyAsyncTask(String name) {
            super();
            this.mName = name;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Thread.sleep((new Random().nextInt(3) + 1) * 500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return mName;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM-dd HH:mm:ss");
            Log.d(TAG, result + " execute finish at " + sdf.format(new Date()));
        }

    }
}
