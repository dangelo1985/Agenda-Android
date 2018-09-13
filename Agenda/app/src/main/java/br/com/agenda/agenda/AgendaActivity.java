package br.com.agenda.agenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.List;

import br.com.agenda.agenda.dao.AlunoDAO;
import br.com.agenda.agenda.models.Alunos;

public class AgendaActivity extends AppCompatActivity {

    private ListView listaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);


       listaAlunos = (ListView) findViewById(R.id.lista_alunos);

       listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
               Alunos aluno = (Alunos) listaAlunos.getItemAtPosition(position);

               Intent intentVaiProFormulario = new Intent(AgendaActivity.this, Formulario.class);
               intentVaiProFormulario.putExtra("aluno", (Serializable) aluno);
               startActivity(intentVaiProFormulario);
           }
       });

        Button botaoCadastrar = (Button) findViewById(R.id.cadastrar);
        botaoCadastrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent formulario = new Intent(AgendaActivity.this,Formulario.class);
                startActivity(formulario);
            }
        });
        registerForContextMenu(listaAlunos);
    }


    private void carregarLista() {
        AlunoDAO alunoDAO = new AlunoDAO(this);
        List<Alunos> alunos = alunoDAO.buscaAlunos();
        alunoDAO.close();


        ArrayAdapter<Alunos> adapter = new ArrayAdapter<Alunos>(this,android.R.layout.simple_list_item_1,alunos);
        listaAlunos.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener(){
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Alunos aluno = (Alunos) listaAlunos.getItemAtPosition(info.position);
                Toast.makeText(AgendaActivity.this,"Deletar"+aluno.getNome(),Toast.LENGTH_SHORT).show();

                AlunoDAO dao = new AlunoDAO(AgendaActivity.this);
                dao.delete(aluno);
                dao.close();

                carregarLista();
                return false;

            }

         });
    }
}
