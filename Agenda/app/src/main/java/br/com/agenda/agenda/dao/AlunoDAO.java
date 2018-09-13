package br.com.agenda.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.agenda.agenda.models.Alunos;

public class AlunoDAO extends SQLiteOpenHelper {
    public AlunoDAO(Context context) {
        super(context, "Alunos", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE alunos (id INTEGER PRIMARY KEY, " +
                "nome TEXT NOT NULL, endereco TEXT, telefone TEXT, email TEXT, nota REAL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS alunos";
        db.execSQL(sql);
        onCreate(db);
    }

    public void inserir(Alunos aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosAlunos(aluno);

        db.insert("alunos",null, dados);
    }

    @NonNull
    private ContentValues pegaDadosAlunos(Alunos aluno) {
        ContentValues dados = new ContentValues();
        dados.put("nome", aluno.getNome());
        dados.put("endereco", aluno.getEndereco());
        dados.put("telefone", aluno.getTelefone());
        dados.put("email", aluno.getEmail());
        dados.put("nota", aluno.getNota());
        return dados;
    }

    public List<Alunos> buscaAlunos(){
        String sql = "SELECT * FROM alunos;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        List<Alunos> alunos = new ArrayList<Alunos>();
        while (c.moveToNext()){
            Alunos aluno = new Alunos();
            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setEmail(c.getString(c.getColumnIndex("email")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));

            alunos.add(aluno);
        }
        c.close();

        return alunos;

    }

    public void delete(Alunos aluno) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {aluno.getId().toString()};
        db.delete("alunos","id = ?",params);
    }

    public void altera(Alunos aluno) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = pegaDadosAlunos(aluno);

        String[] params = {aluno.getId().toString()};
        db.update("alunos",dados,"id = ?",params);
    }
}
