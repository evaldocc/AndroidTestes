package com.example.evaldo.firebase.activity.Administrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.DefinirQuestionarioParaTerminais.ListarQuestionarioResumidasKiosqueActivity;
import com.example.evaldo.firebase.activity.Administrador.ExibirResultados.ExibirResultatosQuantitativos2;
import com.example.evaldo.firebase.activity.Administrador.ExibirResultados.ListarResultadosResumidasActivity;
import com.example.evaldo.firebase.activity.Administrador.Manutencao.CadastrarDispositivoAdministradorActivity;
import com.example.evaldo.firebase.activity.Administrador.GerenciadorQuestionatio.ListarQuestionariosActivity;
import com.example.evaldo.firebase.activity.Administrador.Manutencao.PrincipalManutencaoActivity;


public class PrincipalActivity extends AppCompatActivity {

    public static int pularTela = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

    }

    public void clickManutencao(View view) {
        //Falta colocar a verificação de senha
        Intent intent = new Intent(this, PrincipalManutencaoActivity.class);
        startActivity(intent);
    }

    public void clickResponderQuetionario(View view) {

    }

    public void clickExibirResultados(View view) {
        //todo change class exibir
        Intent intent = new Intent(this, ExibirResultatosQuantitativos2.class);
        startActivity(intent);
    }

    public void clickGerenciarQuestionarios(View view) {
        Intent intent = new Intent(this, ListarQuestionariosActivity.class);
        startActivity(intent);
    }

    public void clickDefinirQuestionarioTerminais(View view) {
        Intent intent = new Intent(this, ListarQuestionarioResumidasKiosqueActivity.class);
        startActivity(intent);
    }
}
