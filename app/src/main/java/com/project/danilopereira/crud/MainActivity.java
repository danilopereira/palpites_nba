package com.project.danilopereira.crud;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.project.danilopereira.crud.com.project.danilopereira.crud.com.project.danilopereira.crud.adapter.TimesAdapter;
import com.project.danilopereira.crud.com.project.danilopereira.crud.dao.ResultadoDAO;
import com.project.danilopereira.crud.com.project.danilopereira.crud.model.Resultado;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvResultados;
    private TimesAdapter adapter;
    private ResultadoDAO resultadoDAO;
    private List<Resultado> resultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, NovoResultadoActivity.class),
                        NovoResultadoActivity.CODE_NOVO_RESULT);
            }
        });

        rvResultados = (RecyclerView) findViewById(R.id.rvLista);

       carregaTorcedores();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(MainActivity.this, "Cancelado",
                    Toast.LENGTH_LONG).show();
        } else if (requestCode == NovoResultadoActivity.CODE_NOVO_RESULT) {
            carregaTorcedores();
        }
    }

    private void carregaTorcedores(){
        resultadoDAO = new ResultadoDAO(this);
        resultados = resultadoDAO.findAll();

        setupLista(resultados);
    }

    private void setupLista(List<Resultado> resultados) {
        adapter = new TimesAdapter(MainActivity.this, resultados, nbaOnClickListener());
        rvResultados.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvResultados.setAdapter(adapter);
    }

    private TimesAdapter.NBAOnClickListener nbaOnClickListener() {
        return new TimesAdapter.NBAOnClickListener() {

            @Override
            public void onLongClickCelular(View view, final int posicao) {
                final Resultado resultado = resultados.get(posicao);

                PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int retorno = 0;

                        switch (item.getItemId()){
                            case R.id.menu_excluir:
                                retorno = resultadoDAO.delete(resultado.getId());
                                break;
                        }

                        if (retorno > 0) {
                            Toast.makeText(getApplicationContext(), "Exluido com sucesso", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Não foi possível excluir", Toast.LENGTH_LONG).show();
                        }

                        resultados.remove(posicao);
                        adapter.notifyItemRemoved(posicao);
                        adapter.notifyItemRangeChanged(posicao, resultados.size());

                        carregaTorcedores();

                        return false;
                    }
                });

                popupMenu.show();
            }
        };
    }

}
