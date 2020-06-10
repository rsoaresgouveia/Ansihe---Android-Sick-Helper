package com.example.sickhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Main_Activity_Paciente extends Activity {
	private ProgressDialog pDialog;
	private static final String URL_LOGIN = "http://192.168.1.103:80/ansihe/comentando.php";
	private static final String TAG_SUCCESS = "success";
	private String cpfusuario;
	private String mensagem;
	private String dataenvio;
	private String categoria;
	private String destinatario;
	private JSONArray Arrayc = null;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");
	private AlertDialog alerta;
//	Date date = new Date();
//	System.out.println(dateFormat.format(date));

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity_paciente);

		Intent l = getIntent();
		cpfusuario = (String) l.getSerializableExtra("cpf");



	}

	public void onNb(View view) {
		ArrayList<String> itens = new ArrayList<String>();
		itens.add("Ir ao banheiro");
		itens.add("Sentindo Frio");
		itens.add("Sentindo Calor");
		itens.add("Fome");
		itens.add("Sede");

		ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item_lista, itens);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Qual sua necessidade?");

		builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				Toast.makeText(Main_Activity_Paciente.this, "posição selecionada" + i, Toast.LENGTH_SHORT).show();
				alerta.dismiss();
			}
		});
			alerta = builder.create();
			alerta.show();


	}

	public void onEmergencia(View view) {
//		new EnviarComentario().execute();
	}


	public void onOutros(View view) {


	}

	public void onDores(View view) {
		ArrayList<String> itens = new ArrayList<String>();
		itens.add("Dor de cabeça");
		itens.add("Dor de barriga");
		itens.add("Dor nos braços");
		itens.add("Dor nas pernas");
		itens.add("Dor nas costas");

		ArrayAdapter adapter = new ArrayAdapter(this, R.layout.item_lista, itens);

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Qual sua dor?");

		builder.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				Toast.makeText(Main_Activity_Paciente.this, "posição selecionada" + i, Toast.LENGTH_SHORT).show();
				alerta.dismiss();
			}
		});
		alerta = builder.create();
		alerta.show();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main__activity__paciente, menu);
		return true;
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

//	class EnviarComentario extends AsyncTask<String, String, String> {
//		String sucess;
//
//		protected void onPreExecute() {
//
//		}
//		protected String doInBackground(String... strings) {
//			try{
//
//				List<NameValuePair> params = new ArrayList<NameValuePair>();
//				params.add(new BasicNameValuePair("cpf", postCpf));
//				params.add(new BasicNameValuePair("nome", postNome));
//				params.add(new BasicNameValuePair("especialidade", postEspecialidade));
//				params.add(new BasicNameValuePair("categoria", categoria));
//
//				Log.d("request!", "starting");
//
//				JSONObject json = jParser.makeHttpRequest(URL_LOGIN, "POST", params);
//
//				Log.d("Post Comment attempt", json.toString());
//
//				sucess = (String) json.getString(TAG_SUCCESS);
//				System.out.println(sucess);
//
//				if(sucess.equals("1")){
//
//					System.out.println("Entrou if");
//					flag = true;
//				}
//
//			}
//			catch(JSONException e){
//				e.printStackTrace();
//			}
//
//
//			return null;
//		}
//
//		protected void onPostExecute(String file_url){
//			if (flag){
//				System.out.println("Entrou if post");
//				Toast.makeText(Cadastro_responsavel.this, "Cadastro Realizado com Sucesso", Toast.LENGTH_LONG).show();
//				finish();
//				flag = false;
//			}
//			else{
//				System.out.println("Entrou else post");
//				Toast.makeText(Cadastro_responsavel.this, "Falha ao se cadastrar, cpf já existente ou houve um erro de conexão", Toast.LENGTH_LONG).show();
//			}
//		}
//	}

}
