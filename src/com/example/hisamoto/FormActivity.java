package com.example.hisamoto;

import java.util.ArrayList;
import java.util.List;

import com.example.hisamoto.entidades.Estado;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class FormActivity extends Activity {
	
	private Spinner mComboboxEstado;
	private ArrayAdapter<Estado> mAdapter;
	private List<Estado> mItens;
	private Button btnSalvar;
	private EditText nome;
	private Spinner estado;
	private EditText cidade;
	private EditText email;
	private CheckBox trabalhador;
	private CheckBox estudante;
	private RadioGroup tipoPessoa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form);
		
		mItens = new ArrayList<Estado>();
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		Estado e1 = new Estado();
		e1.setId(1);
		e1.setNome("Goiás");
		e1.setSigla("GO");
		mItens.add(e1);
		
		Estado e2 = new Estado();
		e2.setId(2);
		e2.setNome("São Paulo");
		e2.setSigla("SP");
		mItens.add(e2);
		
		Estado e3 = new Estado();
		e3.setId(2);
		e3.setNome("Bahia");
		e3.setSigla("BA");
		mItens.add(e2);
		
		mAdapter = new ArrayAdapter<Estado>(this, R.layout.activity_form_spinner_item,mItens);
		
		mComboboxEstado = (Spinner)findViewById(R.id.comboboxEstados);
		mComboboxEstado.setAdapter(mAdapter);
		
		if(getIntent().hasExtra("valor_nome")) {
			Log.i("agenda", "Valor recebido");
			String valor = getIntent().getStringExtra("valor_nome");
			Log.i("agenda", "O valor é:"+valor);
		}

		btnSalvar = (Button)findViewById(R.id.salvarUsuario);
		nome = (EditText)findViewById(R.id.nomeCadastro);
		estado = (Spinner)findViewById(R.id.comboboxEstados);
		cidade = (EditText)findViewById(R.id.cidadeCadastro);
		email = (EditText)findViewById(R.id.emailCadastro);
		trabalhador =(CheckBox)findViewById(R.id.checkBoxTrabalhador);
		estudante =(CheckBox)findViewById(R.id.checkBoxEstudante);
		tipoPessoa = (RadioGroup)findViewById(R.id.tipoPessoa);
		
		tipoPessoa.check(R.id.radioPessoaFisica);
		
		btnSalvar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Usuario usuario = new Usuario();
				
				usuario.setNome(nome.getText().toString());
				usuario.setEmail(email.getText().toString());
				
				Context contexto = getApplicationContext();
				UsuarioDAO usuarioDAO = new UsuarioDAO(contexto);
				usuarioDAO.inserir(usuario);
				
				Log.i("agenda", "Clicando em salvar");
				Log.i("agenda", "Nome:"+nome.getText().toString());
				Log.i("agenda", "Estado:"+estado.getSelectedItemId());
				Log.i("agenda", "Cidade:"+cidade.getText().toString());
				Log.i("agenda", "Email:"+email.getText().toString());
				if(trabalhador.isChecked()) {
					Log.i("agenda", "Trabalhador: Sim");	
				} else {
					Log.i("agenda", "Trabalhador: Não");
				}
				if(estudante.isChecked()) {
					Log.i("agenda", "Estudante: Sim");
				} else {
					Log.i("agenda", "Estudante: Não");
				}
				Log.i("agenda", "Tipo de Pessoa:"+tipoPessoa.getCheckedRadioButtonId());
				
			}
		});
		
	}
	
	public void setMensagem(String titulo, String mensagem) {
		
    	AlertDialog.Builder caixaDialog = new AlertDialog.Builder(FormActivity.this);
		caixaDialog.setMessage(mensagem);
		caixaDialog.setTitle(titulo);
		caixaDialog.setNeutralButton("OK", null);
		caixaDialog.show();
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==android.R.id.home) {
			Log.i("agenda", "botao voltar");
			finish();
		}
	
		// TODO Auto-generated method stub
		return super.onOptionsItemSelected(item);
	}
	
}
