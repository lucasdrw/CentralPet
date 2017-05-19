package com.cursoandroid.centralpet.centralpet.activity;

import android.app.DatePickerDialog;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cursoandroid.centralpet.centralpet.R;
import com.cursoandroid.centralpet.centralpet.activity.bancodados.DataBase;
import com.cursoandroid.centralpet.centralpet.activity.dominio.RepositorioComp;
import com.cursoandroid.centralpet.centralpet.activity.dominio.entidades.Compromissos;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class CadastroAgenda extends AppCompatActivity {

    private Toolbar toolbarCadastro;

    private EditText editNome, editCompromisso, editOutro, editData, editHora, editLocal, editObs;
    private Spinner spnTipo;
    private ArrayAdapter<String> adpSpinner;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioComp repositorioComp;
    private Compromissos compromissos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_agenda);

        toolbarCadastro = (Toolbar) findViewById(R.id.toolbar_cadastro);
        setSupportActionBar(toolbarCadastro);
        editNome = (EditText) findViewById(R.id.editNome);
        editCompromisso = (EditText) findViewById(R.id.editCompromisso);
        editOutro = (EditText) findViewById(R.id.editOutro);
        editData = (EditText) findViewById(R.id.editData);
        editHora = (EditText) findViewById(R.id.editHora);
        editLocal = (EditText) findViewById(R.id.editLocal);
        editObs = (EditText) findViewById(R.id.editObs);
        spnTipo = (Spinner) findViewById(R.id.spinner);
        adpSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adpSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnTipo.setAdapter(adpSpinner);
        adpSpinner.add("Vacinar");
        adpSpinner.add("Banho");
        adpSpinner.add("Tosa");
        adpSpinner.add("Passeio");
        adpSpinner.add("Rotina");
        adpSpinner.add("Outro");


        /*ExibeDataListener listener = new ExibeDataListener();
        editData.setOnClickListener(listener);
        editData.setOnFocusChangeListener(listener); */


         Bundle bundle = getIntent().getExtras();
        if((bundle != null)&&(bundle.containsKey("COMPROMISSO"))){
            compromissos = (Compromissos) bundle.getSerializable("COMPROMISSO");
            preencheDados();
        }else{
            compromissos = new Compromissos();
        }


        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioComp = new RepositorioComp(conn);


        }catch (SQLException e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("erro" + e.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cadastro, menu);



        if(compromissos.getId() != 0)
        menu.getItem(0).setVisible(true);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.salvar:
                salvar();
                Toast.makeText(this, "Compromisso Salvo!", Toast.LENGTH_SHORT).show();
                finish();

                break;

            case R.id.excluir:
                excluir();
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


    private void preencheDados(){

        editNome.setText(compromissos.getNome());
        editCompromisso.setText(compromissos.getCompromisso());
        spnTipo.setSelection(Integer.parseInt(compromissos.getTipo()));
        editOutro.setText(compromissos.getOutro());
        //DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
        //String dt = format.format(compromissos.getData());
        //editData.setText(dt);
        editData.setText(null);
        editHora.setText(null);
        editLocal.setText(compromissos.getLocal());
        editObs.setText(compromissos.getOutro());
    }

    private void salvar(){

        try {

            compromissos = new Compromissos();

            compromissos.setNome(editNome.getText().toString());
            compromissos.setCompromisso(editCompromisso.getText().toString());
            compromissos.setTipo(String.valueOf( spnTipo.getSelectedItemPosition() ));
            compromissos.setOutro(editOutro.getText().toString());
            compromissos.setData(null);
            compromissos.setHorario(null);
            compromissos.setLocal(editLocal.getText().toString());
            compromissos.setObs(editObs.getText().toString());

            if(compromissos.getId() == 0) {
                repositorioComp.inserir(compromissos);
            }else {

                repositorioComp.alterar(compromissos);
            }



        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("erro ao salvar os dados " + e.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }
    }

    private void excluir(){
        try{
            repositorioComp.excluir(compromissos.getId());
        }catch (Exception e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setMessage("erro ao excluir os dados " + e.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }
    }


   /*
    private void exibeData(){
        Calendar calendar = Calendar.getInstance();
        int ano = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH);
        int dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dlg = new DatePickerDialog(this, new SelecionaDataListener(), ano, mes, dia);
        dlg.show();
    }
    private class ExibeDataListener implements View.OnClickListener, View.OnFocusChangeListener{

        @Override
        public void onClick(View view) {
           exibeData();
        }

        @Override
        public void onFocusChange(View view, boolean b) {
            if(b)
                exibeData();
        }
    }
    private class SelecionaDataListener implements DatePickerDialog.OnDateSetListener{

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            Calendar calendar = Calendar.getInstance();
            calendar.set(i, i1, i2);

            Date date = calendar.getTime();

            DateFormat format =DateFormat.getDateInstance(DateFormat.MEDIUM);
            String dt = format.format(date);

            editData.setText(dt);
            compromissos.setData(date);

        }
    }
    */

}
