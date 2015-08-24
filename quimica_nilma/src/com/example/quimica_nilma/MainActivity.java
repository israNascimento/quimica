package com.example.quimica_nilma;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	OnClickListener clicklistenerIniciar, clickListernerInstrucoes;
	Button iniciar, instrucoes;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.iniciar = (Button)findViewById(R.id.button1);
		this.instrucoes = (Button)findViewById(R.id.button2);
				
		clicklistenerIniciar = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), FullscreenActivity.class);
				startActivity(intent);
			}
		};
		
		
		clickListernerInstrucoes = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(), Instrucoes.class);
				startActivity(intent);				
			}
		};
		
		this.iniciar.setOnClickListener(clicklistenerIniciar);
		this.instrucoes.setOnClickListener(clickListernerInstrucoes);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
