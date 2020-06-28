package com.example.gerenciadordeeventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gerenciadordeeventos.database.EventoDAO;
import com.example.gerenciadordeeventos.modelo.Evento;

public class CadastroNovoEvento extends AppCompatActivity {

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_novo_evento);
        setTitle("Cadastro de Evento");
        carregarProduto();
    }

    private void carregarProduto() {
        Intent intent = getIntent();
        if (intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null) {
            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");
            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextData = findViewById(R.id.editText_data);
            EditText editTextLocal = findViewById(R.id.editText_local);
            editTextNome.setText(evento.getNome());
            editTextData.setText(evento.getData());
            editTextLocal.setText(evento.getLocal());
            id = evento.getId();
        }
    }

    public void onClickVoltar(View v) {
        finish();
    }

    public void onClickSalvar(View v) {
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_data);
        EditText editTextLocal = findViewById(R.id.editText_local);

        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        String local = editTextLocal.getText().toString();

        Evento evento = new Evento(id, nome, data, local);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        boolean salvou = eventoDAO.salvar(evento);
        if (salvou) {
            finish();
        } else {
            Toast.makeText(CadastroNovoEvento.this, "Error ao salvar", Toast.LENGTH_LONG).show();
        }

    }

    public void onClickExcluir(View v) {
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextData = findViewById(R.id.editText_data);
        EditText editTextLocal = findViewById(R.id.editText_local);

        String nome = editTextNome.getText().toString();
        String data = editTextData.getText().toString();
        String local = editTextLocal.getText().toString();


        Evento produto = new Evento(id, nome, data, local);
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());

        eventoDAO.excluir(produto);
        finish();
    }
}
