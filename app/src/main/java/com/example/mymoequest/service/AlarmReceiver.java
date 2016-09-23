package com.example.mymoequest.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.mymoequest.MainActivity;
import com.example.mymoequest.R;
import com.example.mymoequest.utils.HeadsUpUtils;
import com.example.mymoequest.utils.PreferencesLoader;


/**
 * Created by hcc on 16/6/25 18:05
 * 100332338@qq.com
 */
public class AlarmReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {

        PreferencesLoader loader = new PreferencesLoader(context);
        if (loader.getBoolean(R.string.action_notifiable, true))
        {
            HeadsUpUtils.show(context, MainActivity.class,
                    context.getString(R.string.headsup_title),
                    context.getString(R.string.headsup_content),
                    R.mipmap.ic_launcher, R.drawable.bow_tie, 123123);
        }
    }
}