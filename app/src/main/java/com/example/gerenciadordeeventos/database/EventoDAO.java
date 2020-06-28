package com.example.gerenciadordeeventos.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.gerenciadordeeventos.database.entity.EventoEntity;
import com.example.gerenciadordeeventos.modelo.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + EventoEntity.TABLE_NAME;

    private final String SQL_LISTAR_LIKE = "SELECT * FROM " + EventoEntity.TABLE_NAME + " WHERE " + EventoEntity.COLUM_NAME_NOME + " LIKE ?";

    private final String SQL_LISTAR_ASC = "SELECT * FROM " + EventoEntity.TABLE_NAME + " ORDER BY " + EventoEntity.COLUM_NAME_NOME + " ASC";

    private final String SQL_LISTAR_DESC = "SELECT * FROM " + EventoEntity.TABLE_NAME + " ORDER BY " + EventoEntity.COLUM_NAME_NOME + " DESC";

    private DBGateway dbGateway;

    public EventoDAO(Context context) {
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Evento evento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUM_NAME_NOME, evento.getNome());
        contentValues.put(EventoEntity.COLUM_NAME_DATA, evento.getData());
        contentValues.put(EventoEntity.COLUM_NAME_LOCAL, evento.getLocal());
        if (evento.getId() > 0) {
            return dbGateway.getDatabase().update(EventoEntity.TABLE_NAME,
                    contentValues, EventoEntity._ID + "=?",
                    new String[]{String.valueOf(evento.getId())}) > 0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME,
                null, contentValues) > 0;
    }
    public List<Evento> listar() {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUM_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUM_NAME_DATA));
            String local = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUM_NAME_LOCAL));
            eventos.add(new Evento(id, nome, data, local));
        }
        cursor.close();
        return eventos;
    }

    public boolean excluir(Evento evento) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUM_NAME_NOME, evento.getNome());
        contentValues.put(EventoEntity.COLUM_NAME_DATA, evento.getData());
        contentValues.put(EventoEntity.COLUM_NAME_LOCAL, evento.getLocal());

        return dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME,
                EventoEntity._ID + "=?",
                new String[]{String.valueOf(evento.getId())}) > 0;
    }

    public List<Evento> listarPesquisar(String pesquisar) {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_LIKE,  new String[] { "%"+pesquisar+"%" });
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUM_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUM_NAME_DATA));
            String local = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUM_NAME_LOCAL));
            eventos.add(new Evento(id, nome, data, local));
        }
        cursor.close();
        return eventos;
    }

    public List<Evento> listarCrescente(){
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_ASC, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUM_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUM_NAME_DATA));
            String local = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUM_NAME_LOCAL));
            eventos.add(new Evento(id, nome, data, local));
        }
        cursor.close();
        return eventos;
    }

    public List<Evento> listarDecrescente(){
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_DESC, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUM_NAME_NOME));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUM_NAME_DATA));
            String local = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUM_NAME_LOCAL));
            eventos.add(new Evento(id, nome, data, local));
        }
        cursor.close();
        return eventos;
    }
}
