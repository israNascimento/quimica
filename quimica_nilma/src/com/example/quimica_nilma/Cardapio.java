package com.example.quimica_nilma;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
public class Cardapio extends Activity {
  
	List<CheckBox> allCheckBox;
	Button addComida, save;  
	EditText et;
	OnClickListener clicklistenerAddComida, clickListenerSave;
	
	private Notification notification;
	private NotificationManager notificationManager;
	private String day;
    Context ctx;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cardapio);
		Intent intent = getIntent();
		day = (String) intent.getCharSequenceExtra("Day");
		
		Log.d("SERVICE SAMPLE", "DAY: "+day);
		//
		allCheckBox = new ArrayList<CheckBox>();
		this.ctx = this.getApplicationContext();
		this.notificationManager = (NotificationManager) this.getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
		this.notification = new Notification();
		this.notification.flags = Notification.FLAG_AUTO_CANCEL;
		this.notification.icon = R.drawable.ic_launcher;
		this.notification.tickerText = "EVITE O DESPERDÍCIO";
		this.notification.when = System.currentTimeMillis();
		
		addComida = (Button) findViewById(R.id.comida);
		save = (Button)findViewById(R.id.save);	
		et = (EditText)findViewById(R.id.editText1);
		
		save.setVisibility(View.INVISIBLE);		
		clicklistenerAddComida = new OnClickListener() 
			{
			    @Override
			    public void onClick(View v)
			   	{
			    	if(!et.getText().toString().equals(""))
			    		CreateCheckBox(""+et.getText().toString());
			   	}
			};
			
			clickListenerSave = new OnClickListener() {
				
				@Override
				public void onClick(View v) 
				{
					for(int i = 0; i<allCheckBox.size(); i++)
					{
						if(allCheckBox.get(i).isChecked())
						{
							SendNotification();
						}
					}
				}
			};
			
			
			save.setOnClickListener(clickListenerSave);
			addComida.setOnClickListener(clicklistenerAddComida);
	}
	
	private void CreateCheckBox(String text)
	{
		CheckBox checkbox = new CheckBox(getApplicationContext());
		checkbox.setText(""+text);
		
		//ARRAY PARA COBTROLAR AS CHECKBOX
		allCheckBox.add(checkbox);
		LinearLayout l = (LinearLayout)findViewById(R.id.cardapio);
		l.addView(checkbox);
		et.setText("");
		save.setVisibility(View.VISIBLE);
		Log.d("SERVICE SAMPLE", "AKI");
	}
	
	@SuppressWarnings("deprecation")
	private void SendNotification()
	{
		Intent notificationIntent = new Intent(this.ctx, FullscreenActivity.class);			
		PendingIntent contentIntent = PendingIntent.getActivity(this.ctx, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		
		this.notification.setLatestEventInfo(this.ctx, "EVITE O DESPERDÍCIO", "Leve comida na "+day, contentIntent);
		this.notificationManager.notify(0, this.notification);
	}
}
