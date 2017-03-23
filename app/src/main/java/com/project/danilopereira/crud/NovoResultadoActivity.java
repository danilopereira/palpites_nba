package com.project.danilopereira.crud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.project.danilopereira.crud.com.project.danilopereira.crud.dao.ConferenciaDAO;
import com.project.danilopereira.crud.com.project.danilopereira.crud.dao.ResultadoDAO;
import com.project.danilopereira.crud.com.project.danilopereira.crud.dao.TimeDAO;
import com.project.danilopereira.crud.com.project.danilopereira.crud.model.Conferencia;
import com.project.danilopereira.crud.com.project.danilopereira.crud.model.Resultado;
import com.project.danilopereira.crud.com.project.danilopereira.crud.model.Time;

import java.util.List;

public class NovoResultadoActivity extends AppCompatActivity {

    public static final int CODE_NOVO_RESULT = 1002;
    private Spinner spConferencia;
    private Spinner spTime1;
    private Spinner spTime2;
    private EditText resultadoTime1;
    private EditText resultadoTime2;
    private ResultadoDAO resultadoDAO;
    private TimeDAO timeDAO;
    private ConferenciaDAO conferenciaDao;
    private Button palpitar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_resultado);

        spConferencia = (Spinner) findViewById(R.id.spConferencia);
        spTime1 = (Spinner) findViewById(R.id.spTime1);
        spTime2 = (Spinner) findViewById(R.id.spTime2);
        resultadoTime1 = (EditText) findViewById(R.id.etResultado1);
        resultadoTime2 = (EditText) findViewById(R.id.etResultado2);
        palpitar = (Button) findViewById(R.id.btnPalpitar);

        resultadoDAO = new ResultadoDAO(this);
        timeDAO = new TimeDAO(this);
        conferenciaDao = new ConferenciaDAO(this);

        List<Conferencia> conferencias = conferenciaDao.findAll();
        ArrayAdapter<Conferencia> conferenciaArrayAdapter = new ArrayAdapter<Conferencia>(getApplicationContext(),R.layout.conferencia_item, conferencias);
        conferenciaArrayAdapter.setDropDownViewResource(R.layout.conferencia_item);
        spConferencia.setAdapter(conferenciaArrayAdapter);

        spConferencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            List<Time> times;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                times = timeDAO.findByConferenciaId(((Conferencia) spConferencia.getSelectedItem()).getId());
                ArrayAdapter<Time> timeArrayAdapter1 = new ArrayAdapter<Time>(getApplicationContext(), R.layout.conferencia_time, times);
                timeArrayAdapter1.setDropDownViewResource(R.layout.conferencia_time);
                spTime1.setPrompt("");
                spTime1.setAdapter(timeArrayAdapter1);
                spTime1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        times = removePrimeiroTime(times, spTime1.getSelectedItemPosition());
                        ArrayAdapter<Time> timeArrayAdapter2 = new ArrayAdapter<Time>(getApplicationContext(), R.layout.conferencia_time, times);
                        timeArrayAdapter2.setDropDownViewResource(R.layout.conferencia_time);
                        spTime1.setPrompt("");
                        spTime2.setAdapter(timeArrayAdapter2);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        palpitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrar();
            }
        });

    }

    private void cadastrar(){
        Resultado resultado = new Resultado();


        resultado.setTime1((Time)spTime1.getSelectedItem());
        resultado.setTime2((Time)spTime2.getSelectedItem());
        resultado.setResultadoTime1(Integer.parseInt(resultadoTime1.getText().toString()));
        resultado.setResultadoTime2(Integer.parseInt(resultadoTime2.getText().toString()));

        resultadoDAO.add(resultado);

        retornaParaTelaAnteiror();
    }

    private void retornaParaTelaAnteiror() {
        Intent intent = new Intent();
        setResult(CODE_NOVO_RESULT, intent);
        finish();
    }

    private List<Time> removePrimeiroTime(List<Time> times, int position) {
        times.remove(position);

        return times;
    }
}
