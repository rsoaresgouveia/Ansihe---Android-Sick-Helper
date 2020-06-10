package com.example.sickhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Select_user extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.select_user);
	}
	
	public void onClickEntrar(View view){
		Intent login = new Intent(this, Login_activity.class);
		startActivity(login);
	}
	
	public void onClickCadastrar(View view){
		Intent cadastro = new Intent(this, Select_cadastro.class);
		startActivity(cadastro);
	}
}
