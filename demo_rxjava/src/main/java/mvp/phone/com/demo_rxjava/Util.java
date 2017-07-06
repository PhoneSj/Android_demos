package mvp.phone.com.demo_rxjava;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.util.Log;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Phone on 2017/7/6.
 */

public class Util {

    public static final String MYTAG = "phone";

    public static Subscription mSubscription;

    public static void readFile(final Context context) {
        Flowable
            .create(new FlowableOnSubscribe<String>() {
                @Override
                public void subscribe(FlowableEmitter<String> emitter) throws Exception {
                    try {
                        InputStream is = context
                            .getResources()
                            .openRawResource(R.raw.test);//test.txt放在res/raw目录中
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String str;
                        while ((str = br.readLine()) != null && !emitter.isCancelled()) {
                            while (emitter.requested() == 0) {
                                if (emitter.isCancelled()) {
                                    break;
                                }
                            }
                            emitter.onNext(str);
                        }
                        br.close();
                        is.close();
                        emitter.onComplete();
                    } catch (IOException e) {
                        emitter.onError(e);
                    }

                }
            }, BackpressureStrategy.ERROR)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Subscriber<String>() {

                @Override
                public void onSubscribe(Subscription s) {
                    mSubscription = s;
                    s.request(1);
                }

                @Override
                public void onNext(String s) {
                    System.out.println(s);
                    try {
                        Thread.sleep(1000);
                        mSubscription.request(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Throwable t) {
                    Log.i(Util.MYTAG, "onError" + t.toString());
                }

                @Override
                public void onComplete() {
                    Log.i(Util.MYTAG, "onComplete");
                }
            });
    }
}
