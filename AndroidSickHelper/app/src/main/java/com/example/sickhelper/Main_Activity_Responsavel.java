package com.example.sickhelper;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Main_Activity_Responsavel extends ListActivity {
	private ProgressDialog pDialog ;
	private static final String URL_LOGIN = "http://192.168.1.103:80/ansihe/listarPacientes.php";
	private static final String TAG_RESULT = "resultado";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_NOME = "nome";
	private static final String TAG_DOENCA = "doenca";
	private static final String TAG_SALA = "sala";
	private JSONArray Arrayc = null;
	private ArrayList<HashMap<String, String>> PList;
	String cpf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main__activity__responsavel);

		Intent i = getIntent();
		cpf = (String) i.getSerializableExtra("cpf");
		System.out.println(cpf + " ====================================");

		System.out.println("Cheguei1");
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main__activity__responsavel, menu);
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

	protected void onResume() {
		System.out.println("Cheguei2");
		super.onResume();
		new LoadPacientes().execute();
	}

	public void updateJSONdata() {

		PList = new ArrayList<HashMap<String, String>>();
		System.out.println("Cheguei5");
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("cpf", cpf));

			JSONParser jParser = new JSONParser();
			JSONObject json = new JSONObject();

			System.out.println("Cheguei6");
			System.out.println(params);

			json = jParser.makeHttpRequest(URL_LOGIN, "POST", params);

			System.out.println("Antes do if json=null");
			System.out.println(json);

			if (json != null) {
				Arrayc = json.getJSONArray(TAG_RESULT);
				System.out.println("depois do if json=null");

				for (int i = 0; i < Arrayc.length(); i++) {
					System.out.println("for for for");
					JSONObject c = Arrayc.getJSONObject(i);

					String nome = c.getString(TAG_NOME);
					String doenca = c.getString(TAG_DOENCA);
					String sala = c.getString(TAG_SALA);

					HashMap<String, String> map = new HashMap<String, String>();

					map.put(TAG_NOME, nome);
					map.put(TAG_DOENCA, doenca);
					map.put(TAG_SALA, sala);

					PList.add(map);

				}
			} else {
				AlertDialog.Builder alert = new AlertDialog.Builder(this);
				alert.setMessage("Erro na conexão!")
						.setNeutralButton("OK", null).show();
				System.out.println("Cheguei7");
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void updateList() {
		System.out.println("Cheguei8");
		if (!PList.isEmpty()) {
			System.out.println("Cheguei9");
			ListAdapter adapter = new SimpleAdapter(this, PList,
					R.layout.single_p, new String[] { TAG_NOME, TAG_DOENCA, TAG_SALA },
					new int[] { R.id.nome, R.id.doenca, R.id.sala });

			setListAdapter(adapter);

			ListView lv = getListView();
			lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
										int position, long id) {

				}
			});
		} else {
			System.out.println("Cheguei10");
			AlertDialog alerta;
			final Intent i = new Intent(this, Login_activity.class);

			AlertDialog.Builder alert = new AlertDialog.Builder(Main_Activity_Responsavel.this);
			alert.setMessage("Sua pesquisa não retornou pacientes!");
			alert.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							startActivity(i);
							finish();
						}
					});


			alerta = alert.create();
			alerta.show();
		}
	}

	class LoadPacientes extends AsyncTask<String, String, String> {
		ProgressDialog	pDialog = new ProgressDialog(Main_Activity_Responsavel.this);

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog.setMessage("Entrando...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
			System.out.println("Cheguei3");

		}

		protected String doInBackground(String... args) {
			System.out.println("Cheguei4");
			updateJSONdata();
			return null;
		}

		protected void onPostExecute(String file_url){
			pDialog.dismiss();
			updateList();
		}

	}
}
