package com.example.quimica_nilma;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceiveAlarm extends BroadcastReceiver
{	
	private Notification notification;
	private NotificationManager notificationManager;
	private String day;
	Context ctx;
	@Override
	public void onReceive(Context arg0, Intent arg1) 
	{
		Log.d("ALARME", "ALARME DISPARADO");
		
		this.ctx = arg0;
		this.notificationManager = (NotificationManager) arg0.getSystemService(Context.NOTIFICATION_SERVICE);
		this.notification = new Notification();
		this.notification.flags = Notification.FLAG_AUTO_CANCEL;
		this.notification.icon = R.drawable.ic_launcher;
		this.notification.tickerText = "EVITE O DESPERDÍCIO";
		this.notification.when = System.currentTimeMillis();
		
		SendNotification();
		
		
		//Pode-se iniciar uma Activity, serviço ou exibir uma notificação aqui!	
	}
	
	private void SendNotification()
	{
		Intent notificationIntent = new Intent(this.ctx, FullscreenActivity.class);			
		PendingIntent contentIntent = PendingIntent.getActivity(this.ctx, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
		
		this.notification.setLatestEventInfo(this.ctx, "EVITE O DESPERDÍCIO", "NÃO SE ESQUEÇA DE LEVAR COMIDA HOJE!!!", contentIntent);
		this.notificationManager.notify(0, this.notification);
	}
		
}