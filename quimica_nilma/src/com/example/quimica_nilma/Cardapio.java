package com.example.quimica_nilma;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlarmManager;
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
import android.widget.Toast;
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
							Schedule(day);
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
	
	private void Schedule(String day)
	{
		int _day;
		
		switch(day)
		{
			case "SEGUNDA-FEIRA": 
				_day = 1;
				break;
		
			case "TERÇA-FEIRA"  :
				_day = 2;
				break;
			
			case "QUARTA-FEIRA" :
				_day = 3;
				break;
				
			case "QUINTA-FEIRA" :
				_day = 4;
				break;
			
			case "SEXTA-FEIRA"  :
				_day = 5;
				break;
				
				default: _day = 7;
		}
		Intent i = new Intent("EXECUTE_ALARM"); 
		PendingIntent pi = PendingIntent.getBroadcast(Cardapio.this, 0, i, 0);
		
		Calendar c = Calendar.getInstance(); 
		c.setTimeInMillis(System.currentTimeMillis());
		c.add(Calendar.DAY_OF_WEEK, _day);
		c.add(Calendar.HOUR, 6);
		
		AlarmManager alarm = (AlarmManager) getSystemService(ALARM_SERVICE); //Agenda o alarme
		long time = c.getTimeInMillis();
		alarm.set(AlarmManager.RTC_WAKEUP, time, pi);
				
		Toast.makeText(ctx, "AGENDADO PARA: "+day, Toast.LENGTH_SHORT).show();
	}	
}
