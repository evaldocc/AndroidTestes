package com.example.evaldo.firebase.activity.Administrador.Login;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.util.Permissao;

public class LoginAdministradorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_administrador);
        permissao();




    }

    private void permissao(){

        String permissoes [] = new String[]{

                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        };

        Permissao.permissao(this,0,permissoes);
    }
}
