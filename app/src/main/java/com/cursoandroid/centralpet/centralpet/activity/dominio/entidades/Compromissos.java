package com.cursoandroid.centralpet.centralpet.activity.dominio.entidades;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.Timer;

/**
 * Created by L Moraes on 29/04/2017.
 */
public class Compromissos implements Serializable{
    private long id;
    private String nome;
    private String compromisso;
    private String tipo;
    private String outro;
    private Date data;
    private Timer horario;
    private String local;
    private String obs;


    public Compromissos(){
        id = 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCompromisso() {
        return compromisso;
    }

    public void setCompromisso(String compromisso) {
        this.compromisso = compromisso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOutro() {
        return outro;
    }

    public void setOutro(String outro) {
        this.outro = outro;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Timer getHorario() {
        return horario;
    }

    public void setHorario(Timer horario) {
        this.horario = horario;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }


    @Override
    public String toString(){

        return "Pet: " + nome + "\nCompromisso: " + compromisso + "\t ...";
    }
}
