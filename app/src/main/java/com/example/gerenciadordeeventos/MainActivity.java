package com.example.gerenciadordeeventos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gerenciadordeeventos.database.EventoDAO;
import com.example.gerenciadordeeventos.modelo.Evento;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private ListView listViewGerenciamentoDeEventos;

    private ArrayAdapter<Evento> adapterEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Gerenciamento de Eventos");
        listViewGerenciamentoDeEventos = findViewById(R.id.listView_gerenciamentoDeEventos);
        defenirOnClickListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        adapterEvento = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDAO.listar());
        listViewGerenciamentoDeEventos.setAdapter(adapterEvento);
    }

    private void defenirOnClickListener() {
        listViewGerenciamentoDeEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento eventoClicado = adapterEvento.getItem(position);
                Intent intent = new Intent(MainActivity.this, CadastroNovoEvento.class);
                intent.putExtra("eventoEdicao", eventoClicado);
                startActivity(intent);
            }
        });
    }

    public void onClickNovoProduto(View v) {
        Intent intent = new Intent(MainActivity.this, CadastroNovoEvento.class);
        startActivity(intent);
    }

    public void onClickPesquisar(View v) {
        EditText editTextPesquisar = findViewById(R.id.editText_pesquisar);
        String pesquisar = editTextPesquisar.getText().toString();
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        List<Evento> eventos = eventoDAO.listarPesquisar(pesquisar);

        if (pesquisar.isEmpty()) {
            Toast.makeText(MainActivity.this, "Digite um Evento", Toast.LENGTH_LONG).show();
        } else if (eventos.isEmpty()) {
            Toast.makeText(MainActivity.this, "Evento não encontrado", Toast.LENGTH_LONG).show();
        } else {
            adapterEvento = new ArrayAdapter<Evento>(MainActivity.this,
                    android.R.layout.simple_list_item_1,
                    eventos);
            listViewGerenciamentoDeEventos.setAdapter(adapterEvento);
        }
    }

    public void onClickCrescente(View v) {
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        adapterEvento = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDAO.listarCrescente());
        listViewGerenciamentoDeEventos.setAdapter(adapterEvento);
    }

    public void onClickDecrescente(View v) {
        EventoDAO eventoDAO = new EventoDAO(getBaseContext());
        adapterEvento = new ArrayAdapter<Evento>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                eventoDAO.listarDecrescente());
        listViewGerenciamentoDeEventos.setAdapter(adapterEvento);
    }
}
