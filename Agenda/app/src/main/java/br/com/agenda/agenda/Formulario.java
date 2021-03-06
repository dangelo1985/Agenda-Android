package br.com.agenda.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.agenda.agenda.dao.AlunoDAO;
import br.com.agenda.agenda.models.Alunos;

public class Formulario extends AppCompatActivity {


    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);
        Intent intent = getIntent();
        Alunos aluno = (Alunos) intent.getSerializableExtra("aluno");
        if (aluno != null){
            helper.preencherFormulario(aluno);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario:

                Alunos aluno = helper .pegaAluno();
                AlunoDAO alunoDAO = new AlunoDAO(this);
                if (aluno.getId() != null){
                    alunoDAO.altera(aluno);
                }else {
                    alunoDAO.inserir(aluno);
                }
                alunoDAO.close();
                Toast.makeText(Formulario.this,"Salvo  com Sucesso!", Toast.LENGTH_SHORT).show();
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
