package com.example.sickhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Login_activity extends Activity{
	// private EditText inputPesquisa;
	private EditText ecpf;
	private ProgressDialog pDial;
	private static final String URL_LOGIN = "http://192.168.1.103:80/ansihe/loginA.php";
	private static final String TAG_RESULT = "resultado";
	private static final String TAG_SUCCESS = "success";
	public int sucesso;
	public boolean flag = false;
	private JSONArray Arrayc = null;
	JSONParser jParser = new JSONParser();
	JSONObject json = new JSONObject();


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);

		ecpf = (EditText) findViewById(R.id.cpf);

	}

	public void onClickEntrar(View view){
		String cpf = ecpf.getText().toString();

		if(cpf.equals("")) {
			AlertDialog.Builder a = new AlertDialog.Builder(this);
			a.setMessage("Campo CPF não pode ficar em branco.")
					.setNeutralButton("Ok", null).show();
		}
		else {

			new SelecionarLogin().execute();
		}
	}

	class SelecionarLogin extends AsyncTask<String, String, String> {
		String resultado;
		String cpf = ecpf.getText().toString();
		ProgressDialog	pDialog = new ProgressDialog(Login_activity.this);

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog.setMessage("Entrando...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();

		}

		@Override
		protected String doInBackground(String... args) {
			try {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("cpf", cpf));

				Log.d("request!", "starting");

				System.out.println(params + " Esse é do login");
				// getting product details by making HTTP request
				JSONObject json = jParser.makeHttpRequest(URL_LOGIN, "POST", params);

				// check your log for json response
				Log.d("Login attempt", json.toString());

				// json success tag
				resultado = json.getString(TAG_RESULT);
				sucesso = json.getInt(TAG_SUCCESS);
				System.out.println(sucesso);
				if(sucesso==1){
					flag = true;
					System.out.println("Entrou if sucesso");
					if (resultado.equals("[{\"categoria\":\"0\"}]")) {
						Intent i = new Intent(Login_activity.this, Main_Activity_Paciente.class);
						System.out.println(cpf);
						i.putExtra("cpf", cpf);
						finish();
						startActivity(i);

					}
					else {
						Intent i = new Intent(Login_activity.this, Main_Activity_Responsavel.class);
						i.putExtra("cpf", cpf);
						finish();
						startActivity(i);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;

		}
		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url){

			if(flag){
				flag = false;
			}
			else{
				System.out.println("Entrou if !flag");
				pDialog.dismiss();
				Toast.makeText(Login_activity.this, "CPF inexistente.", Toast.LENGTH_LONG).show();
			}
		}

	}
}
