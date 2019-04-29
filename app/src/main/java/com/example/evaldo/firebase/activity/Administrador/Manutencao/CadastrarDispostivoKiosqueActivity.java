package com.example.evaldo.firebase.activity.Administrador.Manutencao;

import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CadastrarDispostivoKiosqueActivity extends AppCompatActivity {

    EditText nomeDispositivoKiosque, statusDispositivoKiosque, questionarioAtualDispositivoKiosque;
    TextView idDispositivoKiosque;

    DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
    DatabaseReference DispositivosKiosqueReferencia = databaseReferencia.child("Dispositivos Kiosque");

    String postId = UUID.randomUUID().toString();
    String dataCadastro = pegandoHora();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_dispostivo_kiosque);

        idDispositivoKiosque = findViewById(R.id.tv_cadastrarDispositivoKiosque_idDispositivo);
        nomeDispositivoKiosque = findViewById(R.id.et_cadastrarDispositivoKiosque_nomeDispositivo);
        questionarioAtualDispositivoKiosque = findViewById(R.id.et_cadastrarDispositivoKiosque_questionarioAtual);

        statusDispositivoKiosque =  findViewById(R.id.et_cadastrarDispositivoKiosque_status);
        idDispositivoKiosque.setText("Id idDispositivo= " + pegarIDDispositivo());
    }

    public void clickAtivarDispositivo(View view) {
        if  (idDispositivoKiosque.getText().toString().equals("") ){
            Toast.makeText(this, "Por favor insira o nome do Dispositivo", Toast.LENGTH_LONG).show();
        } else if (statusDispositivoKiosque.getText().toString().equals("")) {
            Toast.makeText(this, "Por favor insira o status do Dispositivo", Toast.LENGTH_LONG).show();
        }else {
            DispositivosKiosqueReferencia.child("idDispositivo").child(pegarIDDispositivo()).child("idDispositivo").setValue(pegarIDDispositivo());
            DispositivosKiosqueReferencia.child("idDispositivo").child(pegarIDDispositivo()).child("key").setValue(postId);
            DispositivosKiosqueReferencia.child("idDispositivo").child(pegarIDDispositivo()).child("nomeDispositivo").setValue(nomeDispositivoKiosque.getText().toString());
            DispositivosKiosqueReferencia.child("idDispositivo").child(pegarIDDispositivo()).child("dataAtivacao").setValue(dataCadastro);
            DispositivosKiosqueReferencia.child("idDispositivo").child(pegarIDDispositivo()).child("status").setValue(statusDispositivoKiosque.getText().toString());
            DispositivosKiosqueReferencia.child("idDispositivo").child(pegarIDDispositivo()).child("questionarioAtual").setValue(questionarioAtualDispositivoKiosque.getText().toString());

            pegarIDDispositivo();

            Toast.makeText(this, "Dispositivo cadastrado com sucesso", Toast.LENGTH_LONG).show();
            finish();

        }
    }

    private String pegarIDDispositivo() {
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }

    private String pegandoHora() {
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        Date dataCal = new Date();
        Date dataHora = new Date();
        String dataFormatada = formataData.format(dataCal);
        String horaFormatada = formatHora.format(dataHora);
        String dataEHora = "Data " + dataFormatada + " Hora " + horaFormatada ;
        System.out.println("Data " + dataFormatada + " Hora " + horaFormatada );

        return dataEHora;
    }
}
