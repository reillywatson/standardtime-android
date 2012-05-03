package com.reilly.standardtime;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

public class StandardTimeActivity extends Activity {
    /** Called when the activity is first created. */
	
	List<Uri> uris = new ArrayList<Uri>();
	int current;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.main);
        uris.clear();
        uris.add(Uri.parse("file:///mnt/sdcard/st24_iPhone_Retina_1-4.mp4"));
        uris.add(Uri.parse("file:///mnt/sdcard/st24_iPhone_Retina_2-4.mp4"));
        uris.add(Uri.parse("file:///mnt/sdcard/st24_iPhone_Retina_3-4.mp4"));
        uris.add(Uri.parse("file:///mnt/sdcard/st24_iPhone_Retina_4-4.mp4"));
//		WindowManager.LayoutParams params = new WindowManager.LayoutParams();
//		params.buttonBrightness = 0;
//		view.setLayoutParams(params);
        VideoView view = (VideoView)findViewById(R.id.videoView1);
        view.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer player) {
				syncVideo();
			}
		});
        syncVideo();
    }
    
    void syncVideo() {
        VideoView view = (VideoView)findViewById(R.id.videoView1);
        Time time = new Time();
        time.setToNow();
        current = time.hour / 6;
        int offset = ((time.hour % 6) * 3600000) + (time.minute * 60000) + (time.second * 1000);
		view.setVideoURI(uris.get(current));
        view.seekTo(offset);
        view.start();
    }
}