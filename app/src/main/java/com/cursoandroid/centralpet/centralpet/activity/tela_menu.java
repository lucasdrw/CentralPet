package com.cursoandroid.centralpet.centralpet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.cursoandroid.centralpet.centralpet.R;
import com.facebook.login.LoginManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class tela_menu extends AppCompatActivity{

    private AdView adView;

    private Toolbar toolbar_menu;

    private ImageView agenda;
    private ImageView marcados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_menu);

        toolbar_menu = (Toolbar) findViewById(R.id.toolbar_menu);
        agenda = (ImageView) findViewById(R.id.agenda_id);
        marcados = (ImageView) findViewById(R.id.agenda_salva_id);

        //Toolbar
        toolbar_menu.setTitle("Central Pet");
        setSupportActionBar(toolbar_menu);




        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String nome = user.getDisplayName();
            String email = user.getEmail();


        } else {
            goLoginScreen();
        }


        //chamar a agenda
        agenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tela_menu.this, CadastroAgenda.class);
                startActivity(intent);
            }
        });

        //chamar o cadastro de agenda
        marcados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tela_menu.this, SalvosAgenda.class);
                startActivity(intent);
            }
        });



        //Anúncios
        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }
    private void goLoginScreen(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

    //toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_deslogar:
                deslogar_usuario();
                return true;
            default:
            return super.onOptionsItemSelected(item);
        }
    }

    private void deslogar_usuario(){
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }

}


