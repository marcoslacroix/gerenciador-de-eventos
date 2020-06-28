package com.example.gerenciadordeeventos.database.contract;

import com.example.gerenciadordeeventos.database.entity.EventoEntity;

public class EventoContract {

    private EventoContract() {}

    public static final String criarTabela() {
        return "CREATE TABLE " + EventoEntity.TABLE_NAME
                + "( "
                + EventoEntity._ID + " INTEGER PRIMARY KEY, "
                + EventoEntity.COLUM_NAME_NOME + " TEXT,"
                + EventoEntity.COLUM_NAME_DATA + " TEXT,"
                + EventoEntity.COLUM_NAME_LOCAL + " TEXT)";
    }

    public static final String removerTabela() {
        return "DROP TABLE IF EXISTS " + EventoEntity.TABLE_NAME;
    }




}
