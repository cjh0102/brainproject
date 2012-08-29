package kr.imus.brainproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class StartActivity extends Activity {
	public static int curWidth;
	public static int curHeight;
	
	private ImageView logoImageView;
	private Handler handler;
	private final int LOGO_SUCCESS = 1;
	private final int INTRO_SUCCESS = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.start);
		
		logoImageView = (ImageView) findViewById(R.id.logo);
		
		loadLogo();
		loadIntroScreen();
		calculateScreen();
	}
	
	public void loadLogo() {
		new Thread(new Runnable() {
			public void run() {
				try {
					Animation alphaAnimation = AnimationUtils.loadAnimation(StartActivity.this, R.anim.alpha);
					logoImageView.startAnimation(alphaAnimation);

					Thread.sleep(2000);
					
					handler.sendEmptyMessage(LOGO_SUCCESS);
				} catch (Exception e) {
				}
				
			}
		}).start();
	}
	
	public void loadIntroScreen() {

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				if (msg.what == LOGO_SUCCESS) {
					logoImageView.setBackgroundResource(R.drawable.background);
				} else if(msg.what == INTRO_SUCCESS) {
					Intent intent = new Intent(StartActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				}
			}
		};
		handler.sendEmptyMessageDelayed(2, 3000);
	}
	
	public void calculateScreen() {
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		
		curWidth = displayMetrics.widthPixels;
		curHeight =displayMetrics.heightPixels;	
	}
}
