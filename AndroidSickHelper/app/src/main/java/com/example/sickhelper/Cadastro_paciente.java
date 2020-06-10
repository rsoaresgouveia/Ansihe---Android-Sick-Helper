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


public class Cadastro_paciente extends Activity {
    private static final String URL_LOGIN = "http://192.168.1.103:80/ansihe/CadastrarPaciente.php";
    private static final String TAG_SUCCESS = "success";
    private EditText nome, cpf, doenca, sala, cpf_responsavel;
    public static String categoria = "0";
    JSONParser jParser = new JSONParser();
    public boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_paciente);

        nome = (EditText) findViewById(R.id.nome_paci);
        cpf = (EditText) findViewById(R.id.cpf_paci);
        doenca = (EditText) findViewById(R.id.enfermidade);
        sala = (EditText) findViewById(R.id.local);
        cpf_responsavel = (EditText) findViewById(R.id.cpf_respon);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cadastro_paciente, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onCadastrar(View view){
        String postNome = nome.getText().toString();
        String postCpf = cpf.getText().toString();
        String postDoenca = doenca.getText().toString();
        String postSala = sala.getText().toString();
        String postCpfRes = cpf_responsavel.getText().toString();

        if(((postNome.equals("")) || postCpf.equals("")) || ((postDoenca.equals("")) || postSala.equals("")) || (postCpfRes.equals(""))) {
            AlertDialog.Builder a = new AlertDialog.Builder(this);
            a.setMessage("Campos em branco, preencha todos para poder prosseguir.")
                    .setNeutralButton("Ok", null).show();
        }
        else{
            new EnviarCadastro().execute();
        }
    }

    class EnviarCadastro extends AsyncTask<String, String, String> {
        String sucess;
        String postNome = nome.getText().toString();
        String postCpf = cpf.getText().toString();
        String postDoenca = doenca.getText().toString();
        String postSala = sala.getText().toString();
        String postCpfRes = cpf_responsavel.getText().toString();

        protected void onPreExecute() {

        }
        protected String doInBackground(String... strings) {
            try{

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("cpf", postCpf));
                params.add(new BasicNameValuePair("nome", postNome));
                params.add(new BasicNameValuePair("doenca", postDoenca));
                params.add(new BasicNameValuePair("sala", postSala));
                params.add(new BasicNameValuePair("categoria", categoria));
                params.add(new BasicNameValuePair("cpfResponsavel", postCpfRes));

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
                Toast.makeText(Cadastro_paciente.this, "Cadastro Realizado com Sucesso", Toast.LENGTH_LONG).show();
                finish();
                flag = false;
            }
            else{
                System.out.println("Entrou else post");
                Toast.makeText(Cadastro_paciente.this, "Falha ao se cadastrar. Tente novamente", Toast.LENGTH_LONG).show();
            }
        }
    }
}
