package br.com.agenda.agenda;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.agenda.agenda.models.Alunos;

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoEmail;
    private final RatingBar campoNotas;
    Alunos aluno = new Alunos();

    public FormularioHelper(Formulario activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoEmail = (EditText) activity.findViewById(R.id.formulario_email);
        campoNotas = (RatingBar) activity.findViewById(R.id.formulario_nota);
        aluno = new Alunos();
    }

    public Alunos pegaAluno() {
        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setEmail(campoEmail.getText().toString());
        aluno.setNota(Double.valueOf(campoNotas.getProgress()));

        return aluno;

    }

    public void preencherFormulario(Alunos aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoEmail.setText(aluno.getEmail());
        campoNotas.setProgress(aluno.getNota().intValue());

        this.aluno = aluno;
    }
}
