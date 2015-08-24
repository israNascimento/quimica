package com.example.quimica_nilma;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FullscreenActivity extends Activity {


    List<Button> allButtons;
	OnClickListener clicklistener;
	
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_fullscreen);
        allButtons = new ArrayList<Button>();
        allButtons.add((Button)findViewById(R.id.buttonAddCardapio1));
        allButtons.add((Button)findViewById(R.id.buttonAddCardapio2));
        allButtons.add((Button)findViewById(R.id.buttonAddCardapio3));
        allButtons.add((Button)findViewById(R.id.buttonAddCardapio4));
        allButtons.add((Button)findViewById(R.id.buttonAddCardapio5));
        

    	Log.d("SERVICE SAMPLE", "ACIMA");
        clicklistener = new OnClickListener() 
		{
		    @Override
		    public void onClick(View v)
		   	{
		    	Log.d("SERVICE SAMPLE", "BUTTON");
		   	    for(int i=0; i<allButtons.size();i++)
		   	    {
		   	    	if(v.getId() == allButtons.get(i).getId())
		   	    	{
		   	    		Button b = (Button)v;
		   	    		String text = b.getText().toString();
		   	    		Intent intent = new Intent(getApplicationContext(), Cardapio.class);
		   	    		intent.putExtra("Day", ""+text);
		   	    		startActivity(intent);
		   	    	}
		   	    }
		   	}
		};
		
		for(int i = 0; i<allButtons.size(); i++)
        {
        	allButtons.get(i).setOnClickListener(clicklistener);
        	Log.d("SERVICE SAMPLE", "DENTRO DO FOR "+clicklistener);
        }

    }
}
