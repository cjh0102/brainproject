package kr.imus.brainproject.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.w3c.dom.Text;

import kr.imus.brainproject.model.BrainViewModel;
import kr.imus.brainproject.model.Data;
import roboguice.activity.RoboActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hipits.apps.entertain.brainproject.manager.BitmapManager;
import com.hipits.apps.entertain.brainproject.manager.BrainView;
import com.hipits.apps.entertain.brainproject.manager.ColorManager;
import com.hipits.apps.entertain.brainproject.manager.FontSizeManager;
import com.hipits.apps.entertain.brainproject.manager.ItemManager;
import com.hipits.apps.entertain.brainproject.manager.PointManager;

public class BrainActivity extends RoboActivity {

	private static final String FILE_NAME = "data.txt";
	private View brainView;
	private List<Data> dataList;
	private int num;
	private String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		init();
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		name = getIntent().getStringExtra("name");
		String gender = getIntent().getStringExtra("gender");
		Data data = null;

		try {
			data = new Data(name, gender, null, 0,formatter.parse(formatter
					.format(new Date())));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		if (dataList.contains(data)) {
			data = dataList.get(dataList.indexOf(data));
			Log.d("data is exist", data.getGender() + data.getName() + data.getNumberofItems() + data.getItems());
		} else {
			Random random = new Random();
			num = random.nextInt(8) + 1;

			data = new Data();

			data.setName(name);
			data.setGender(gender);
			data.setNumberofItems(num);
			data.setItems(ItemManager.getInstance().getItemList(num));
			data.setTimestamp(new Date());

			Log.d("data isn't exist", data.getGender() + data.getName() + data.getNumberofItems() + data.getItems());

			try {
				save(data);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		drawLayout(data);
	}

	private void init() {
		// 데이터 리스트 생성
		dataList = new ArrayList<Data>();

		// 2012-04-05 처럼 포멧터 객체생성
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		// 파일객체생성 파일이름 'data.txt'
		File path = new File(Environment.getExternalStorageDirectory() + "/brain/data");
		if (!path.isDirectory()){
			path.mkdirs();
		}
		File file = new File(path, FILE_NAME);

		// 파일이 존재하지않으면 return
		if (!file.exists()) {
			return;
		}

		// 파일이 존재할경우
		Scanner scanner = null;

		// 스캐너 객체생성('data.txt')
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			//throw new RuntimeException(e);
		}

		// 스캐너로 값이 없을�까지 분석
		while (scanner.hasNext()) {
			// 빈 data 객체 생성
			Data data = new Data();

			// data객체에 이름 초기화
			data.setName(scanner.next());
			data.setGender(scanner.next());
			data.setNumberofItems(scanner.nextInt());

			String items = scanner.next();

			// Tokenizer로 "|"을 제거
			StringTokenizer tokenizer = new StringTokenizer(items, "|");
			while (tokenizer.hasMoreTokens()) {
				data.getItems().add(tokenizer.nextToken());
			}

			try {
				// 날짜의 값을 스캔하여 값을 추가
				data.setTimestamp(formatter.parse(scanner.next()));
			} catch (ParseException e) {
				Log.d("ParseException", data.getGender() + data.getName() + data.getNumberofItems() + data.getItems());
				//throw new RuntimeException(e);
			}
			dataList.add(data);
		}
		scanner.close();
	}


	public void drawLayout(Data data) {
		setContentView(R.layout.brain);

		int num = data.getNumberofItems();
		
		BrainViewModel brainViewModel = new BrainViewModel (num, 
				data.getItems(),
				FontSizeManager.getInstance().getFontSizes(num),
				PointManager.getInstance().getPoint(num),
				ColorManager.getInstance().getColor(num), 
				BitmapManager.getInstance().getBitmapForId(this, num));

		brainView = new BrainView(this, brainViewModel, name);

		LinearLayout view = (LinearLayout)findViewById(R.id.canvas);
		view.addView(brainView);

	}

	private void save(Data data) throws IOException {

		File path = new File(Environment.getExternalStorageDirectory() + "/brain/data");
		path.mkdirs();

		File file = new File(path, FILE_NAME);

		FileWriter writer = new FileWriter(file, true);

		writer.write(data.getName() + "\t");
		writer.write(data.getGender() + "\t");
		writer.write(data.getNumberofItems() + "\t");

		for (String item : data.getItems()) {
			writer.write(item + "|");
		}
		writer.write("\t");

		SimpleDateFormat formattter = new SimpleDateFormat("yyyy-MM-dd");
		writer.write(formattter.format(data.getTimestamp()));
		writer.write("\n");
		writer.flush();
		writer.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, 0, 0, "스크린샷") ;  
		menu.add(0, 1, 0, "카카오톡공유");  
		return super.onCreateOptionsMenu(menu);
	}
	public void sendBroadCast() {

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case 0:
			screenShot();
			Toast.makeText(BrainActivity.this, "저장되었습니다", Toast.LENGTH_LONG).show();
			break;

		case 1:
			kakaoSend();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	private void screenShot() {
		
		LinearLayout screen = (LinearLayout) findViewById(R.id.canvas);
		View view = screen.getRootView();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = dateFormat.format(new Date()) + " "  + name + ".png";
		File file = null;

		try {
			File path = new File(Environment.getExternalStorageDirectory() + "/brain/screenShot");

			if (!path.isDirectory()) {
				path.mkdirs();
			} 

			file = new File(path, fileName);

			if (!file.isFile()) {
				file.createNewFile();
			} else {
				return;
			}

			view.setDrawingCacheEnabled(true);
			Bitmap bitmap = view.getDrawingCache();

			FileOutputStream outputStream = new FileOutputStream(file);

			bitmap.compress(Bitmap.CompressFormat.PNG, 90, outputStream);

			sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
					Uri.parse("file://" + Environment.getExternalStorageDirectory())));

			outputStream.flush();
			outputStream.close();


		} catch (Exception e) { 
			Log.d("FileNotFoundException:", e.getMessage());
		}
	}

	public void kakaoSend() {

		screenShot();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String fileName = dateFormat.format(new Date()) + " " + name + ".png";
		
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("image/png");

		try {
			intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(Environment.
					getExternalStorageDirectory() + "/brain/screenShot/" + fileName));
			intent.setPackage("com.kakao.talk");

			BrainActivity.this.startActivity(intent);

		} catch (Exception e) {
			Toast.makeText(this, "카카오톡을 설치해주세요", Toast.LENGTH_SHORT).show();
		}
	}
}
