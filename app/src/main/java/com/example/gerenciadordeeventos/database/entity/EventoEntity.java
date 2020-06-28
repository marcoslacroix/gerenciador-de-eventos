package com.example.gerenciadordeeventos.database.entity;

import android.provider.BaseColumns;

public class EventoEntity implements BaseColumns {

    private EventoEntity(){}

    public static final String TABLE_NAME = "evento";

    public static final String COLUM_NAME_NOME = "nome";

    public static final String COLUM_NAME_DATA = "data";

    public static final String COLUM_NAME_LOCAL = "local";




}
