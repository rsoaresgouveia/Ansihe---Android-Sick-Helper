package com.example.sickhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Cadastro_responsavel extends Activity {
	private static final String URL_LOGIN = "http://192.168.1.103:80/ansihe/CadastrarResponsavel.php";
	private static final String TAG_SUCCESS = "success";
	private EditText nome, cpf, especialidade;
	public static String categoria = "1";
	JSONParser jParser = new JSONParser();
	public boolean flag = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cadastro_responsavel);

		nome = (EditText) findViewById(R.id.nome_responsa);
		cpf = (EditText) findViewById(R.id.cpf_responsa);
		especialidade = (EditText) findViewById(R.id.especialidade);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cadastro_responsavel, menu);
		return true;
	}

	public void onCadastrar(View view){
		String postNome = nome.getText().toString();
		String postCpf = cpf.getText().toString();
		String postEspecialidade = especialidade.getText().toString();

		if(((postNome.equals("")) || postCpf.equals("")) || ((postEspecialidade.equals("")))) {
			AlertDialog.Builder a = new AlertDialog.Builder(this);
			a.setMessage("Campos em branco, preencha todos para poder prosseguir.")
					.setNeutralButton("Ok", null).show();
		}
		else{
			new EnviarCadastro().execute();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	class EnviarCadastro extends AsyncTask<String, String, String> {
		String sucess;
		String postNome = nome.getText().toString();
		String postCpf = cpf.getText().toString();
		String postEspecialidade = especialidade.getText().toString();

		protected void onPreExecute() {

		}
		protected String doInBackground(String... strings) {
				try{

				List<NameValuePair> params = new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("cpf", postCpf));
				params.add(new BasicNameValuePair("nome", postNome));
				params.add(new BasicNameValuePair("especialidade", postEspecialidade));
				params.add(new BasicNameValuePair("categoria", categoria));

				Log.d("request!", "starting");

				JSONObject json = jParser.makeHttpRequest(URL_LOGIN, "POST", params);

				Log.d("Post Comment attempt", json.toString());

				sucess = (String) json.getString(TAG_SUCCESS);
				System.out.println(sucess);

				if(sucess.equals("1")){

					System.out.println("Entrou if");
					flag = true;
				}

			}
			catch(JSONException e){
				e.printStackTrace();
			}


			return null;
		}

		protected void onPostExecute(String file_url){
			if (flag){
				System.out.println("Entrou if post");
				Toast.makeText(Cadastro_responsavel.this, "Cadastro Realizado com Sucesso", Toast.LENGTH_LONG).show();
				finish();
				flag = false;
			}
			else{
				System.out.println("Entrou else post");
				Toast.makeText(Cadastro_responsavel.this, "Falha ao se cadastrar, cpf já existente ou houve um erro de conexão", Toast.LENGTH_LONG).show();
			}
		}
	}
}
