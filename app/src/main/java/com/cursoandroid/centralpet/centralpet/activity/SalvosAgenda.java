package com.cursoandroid.centralpet.centralpet.activity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.cursoandroid.centralpet.centralpet.R;
import com.cursoandroid.centralpet.centralpet.activity.bancodados.DataBase;
import com.cursoandroid.centralpet.centralpet.activity.dominio.RepositorioComp;
import com.cursoandroid.centralpet.centralpet.activity.dominio.entidades.Compromissos;

public class SalvosAgenda extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener{

    private Toolbar toolbaragenda;

    private EditText editPesquisa;
    private ListView listSalvos;
    private ArrayAdapter<Compromissos> adpComp;

    private DataBase dataBase;
    private SQLiteDatabase conn;
    private RepositorioComp repositorioComp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salvos_agenda);

        toolbaragenda = (Toolbar) findViewById(R.id.toolbar_agenda);
        setSupportActionBar(toolbaragenda);
        editPesquisa = (EditText) findViewById(R.id.editPesquisa);
        listSalvos = (ListView) findViewById(R.id.listSalvos);

        listSalvos.setOnItemClickListener(this);

        try {
            dataBase = new DataBase(this);
            conn = dataBase.getWritableDatabase();

            repositorioComp = new RepositorioComp(conn);

            adpComp = repositorioComp.buscaComp(this);

            listSalvos.setAdapter(adpComp);


            FiltraDados filtraDados = new FiltraDados(adpComp);
            editPesquisa.addTextChangedListener(filtraDados);

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
        inflater.inflate(R.menu.menu_agenda, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.adicionar:
                Intent intent = new Intent(this, CadastroAgenda.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Compromissos compromissos = adpComp.getItem(i);

        Intent it = new Intent(this, CadastroAgenda.class);
        it.putExtra("COMPROMISSO", compromissos);
        startActivityForResult(it, 0);


    }

    @Override
    public void onClick(View view) {
        Intent it = new Intent(this, CadastroAgenda.class);
        startActivityForResult(it, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adpComp = repositorioComp.buscaComp(this);
        listSalvos.setAdapter(adpComp);
    }


    private class FiltraDados implements TextWatcher{

        private ArrayAdapter<Compromissos> arrayAdapter;

        private  FiltraDados(ArrayAdapter<Compromissos> arrayAdapter){

            this.arrayAdapter = arrayAdapter;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            arrayAdapter.getFilter().filter(charSequence);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}
