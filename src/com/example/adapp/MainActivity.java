package com.example.adapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageView img1, img2;
	final int[] imageArray={R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5,R.drawable.image2};
	Dialog dialog;
    ImageView view;
    Button b;
    RadioGroup radioGroup;
    RadioButton radioButton;
    private int current_image=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.button1);
        radioGroup = (RadioGroup)findViewById(R.id.rgroup);
       // radioButton=(RadioButton)findViewById(R.id.radioButton1);
        
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);  
        dialog.setContentView(R.layout.custom_dialogat_login);
        view = (ImageView)dialog.findViewById(R.id.image_custom_dialog);
        view.setBackgroundResource(imageArray[0]);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.FILL_PARENT;
        lp.height = WindowManager.LayoutParams.FILL_PARENT;
        dialog.getWindow().setAttributes(lp);
        
        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {			
			@Override
			public void run() {
				imagechanger();
			}
			
		}, 0, 5000);
        
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "haha", Toast.LENGTH_SHORT).show();
				int selectedId = radioGroup.getCheckedRadioButtonId();
			    radioButton = (RadioButton) findViewById(selectedId);
	 
				Toast.makeText(MainActivity.this,
					radioButton.getText(), Toast.LENGTH_SHORT).show();
				String extr = Environment.getExternalStorageDirectory().toString();
	            File mFolder = new File(extr + "/madhav");
	            if (!mFolder.exists()) {
	                mFolder.mkdir();
	            }
			        File file = new File(mFolder, "tst.txt");

			        try {
						FileOutputStream f = new FileOutputStream(file);
						String data="testtext";
						f.write(data.getBytes());
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		});
       
    }
    public void imagechanger(){
    	runOnUiThread(new Runnable() {
    		public void run() {
    		    view.setBackgroundResource(imageArray[current_image]);
    		}
    		 });
    		if(current_image<imageArray.length-1)
    		current_image++;
    		else{
    			
    			if(dialog.isShowing())
        			dialog.dismiss();
    		}
//    		if(current_image>imageArray.length-1){
//    			if(dialog.isShowing())
//    			dialog.dismiss();
//    			//setContentView(R.layout.activity_main);
//    		}
    			
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
