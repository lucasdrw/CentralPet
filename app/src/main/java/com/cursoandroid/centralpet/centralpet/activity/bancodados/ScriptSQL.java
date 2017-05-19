package com.cursoandroid.centralpet.centralpet.activity.bancodados;

/**
 * Created by L Moraes on 28/04/2017.
 */
public class ScriptSQL {

    public static String getCreateAgenda(){

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("CREATE TABLE IF NOT EXISTS COMPROMISSO( ");
        sqlBuilder.append("_id          INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sqlBuilder.append("NOME         VARCHAR (10), ");
        sqlBuilder.append("COMPROMISSO  VARCHAR (55), ");
        sqlBuilder.append("TIPO         VARCHAR (1), ");
        sqlBuilder.append("OUTRO        VARCHAR (55), ");
        sqlBuilder.append("DATA         DATE, ");
        sqlBuilder.append("HORARIO      TIME, ");
        sqlBuilder.append("LOCAL        VARCHAR (55), ");
        sqlBuilder.append("OBS          VARCHAR (55) ");
        sqlBuilder.append(");");

        return sqlBuilder.toString();
    }

}
