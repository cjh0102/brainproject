package kr.imus.brainproject.activity;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends RoboActivity {
	private String gender;

	@InjectView(R.id.nameEditText)
	private EditText nameEditText;

	@InjectView(R.id.radioGroup1)
	private RadioGroup radioGroup;

	@InjectView(R.id.commit)
	private Button commitButton;

	@InjectView(R.id.nameTextView)
	private TextView textView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);


		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
				RadioButton radioButton = (RadioButton) findViewById(checkedId);
				if (radioButton != null) {
					gender = radioButton.getText().toString();
				} else {
					gender = null;
				}
			}	
		});

		commitButton.setOnClickListener(new OnClickListener() {
			Intent intent;
			public void onClick(View view) {

				intent = new Intent(MainActivity.this, BrainActivity.class);
				final String name = nameEditText.getText().toString();

				if(name.length() == 1) {
					Toast.makeText(MainActivity.this,"이름이 한글자 인가요?", Toast.LENGTH_SHORT).show();
				} else if (name.length() == 0) {
					Toast.makeText(MainActivity.this,"이름을 입력하세요!!", Toast.LENGTH_SHORT).show();
				} else if (gender == null) {
					Toast.makeText(MainActivity.this,"성별을 입력하세요", Toast.LENGTH_SHORT).show();
				} else {

					final ProgressDialog dialog = ProgressDialog.show(MainActivity.this, "뇌구조분석", "뇌파분석중입니다.");

					new Thread(new Runnable() {
						public void run() {
							try {

								Thread.sleep(2500);

								intent.putExtra("name", name);
								intent.putExtra("gender", gender);
								dialog.dismiss();
								MainActivity.this.startActivity(intent);

							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					}).start();
					nameEditText.setText("");
				}
			}
		});
	}
	
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("종료하시겠습니까?")
		.setPositiveButton("예", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {
				finish();
			}
		}).setNegativeButton("아니오", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		}).show();
	}
}