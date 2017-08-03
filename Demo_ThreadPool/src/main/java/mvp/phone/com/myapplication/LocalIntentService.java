package mvp.phone.com.myapplication;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

public class LocalIntentService extends IntentService {
	
	private static final String TAG = "LocalIntentService";

	public LocalIntentService() {
		super(TAG);
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String action = intent.getStringExtra("task_action");
		Log.d(TAG, "receive task:" + action);
		SystemClock.sleep(1000);
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "service destroyed");
		super.onDestroy();
	}

}
