package com.seven.idouban.app.background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AppExitReceiver extends BroadcastReceiver {
	
	public static final String ACTION_APP_EXIT = "com.pkpknetwork.pkpk.APP_EXIT";
	
	public void onAppExit() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if (ACTION_APP_EXIT.equals(intent.getAction())) {
			onAppExit();
		}
	}

}
