package com.example.evaldo.firebase.activity.Administrador;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.DispositivoAdministrador;
import com.example.evaldo.firebase.activity.Administrador.ExibirResultados.ListarResultadosDetalhadasActivity;
import com.example.evaldo.firebase.activity.Administrador.ExibirResultados.ListarResultadosResumidasActivity;
import com.example.evaldo.firebase.activity.Cliente.TelaGerenciadorlActivity;
import com.example.evaldo.firebase.activity.Administrador.util.Permissao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        permissao();
        //pegarIDDispositivo();
        chamarClasseTeste();

    }

    private String pegarIDDispositivo() {
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        System.out.println(pegarIDDispositivo());
        return android_id;
    }


    private void chamarClasseTeste() {
        finish();
        Intent intent = new Intent(this, PrincipalActivity.class);
        startActivity(intent);
    }

    public void clickResposnder(View view) {
       /* String postId = UUID.randomUUID().toString();
        contTela += 1;
        //BancoQuestionarioReferencia.setValue(postId);

        Bundle bundle = new Bundle();
        bundle.putString("id", postId);
        bundle.putInt("contTela", contTela);*/
        Intent intent = new Intent(this, TelaGerenciadorlActivity.class);
        //intent.putExtras(bundle);
        finish();
        startActivity(intent);
    }

    public void clickOpcoesAdministrativas(View view) {
        Intent intent = new Intent(this, DispositivoAdministrador.class);
        finish();
        startActivity(intent);
    }

    //------------------pERMISSÃO DO USUARIO------------------------------------------------------

    private void permissao(){

        String permissoes [] = new String[]{

                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };

        Permissao.permissao(this,0,permissoes);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result: grantResults){
            if (result == PackageManager.PERMISSION_DENIED){

                Toast.makeText(this, "Aceite as permissões para o aplicativo funcionar corretamente", Toast.LENGTH_LONG).show();
                finish();

                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }

    //////////////////////////MENU CARREGANDO//////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_compartilhar:
                Toast.makeText(this, "Compartilhar", Toast.LENGTH_LONG).show();
                return true;

            case R.id.item_criar_pdf:
                Toast.makeText(this, "Gerar PDF", Toast.LENGTH_LONG).show();
                //item_gerarPDF();
                return true;
            case R.id.item_voltar:
                Toast.makeText(this, "Voltar", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                //item_voltar();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
