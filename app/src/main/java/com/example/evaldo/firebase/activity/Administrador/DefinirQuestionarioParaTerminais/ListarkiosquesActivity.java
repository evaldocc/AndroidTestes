package com.example.evaldo.firebase.activity.Administrador.DefinirQuestionarioParaTerminais;

import android.content.Context;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.DispositivoKiosque;
import com.example.evaldo.firebase.activity.Administrador.adapter.DispositivoKiosquesRecyclerAdapter;
import com.example.evaldo.firebase.activity.Administrador.adapter.ResultadosResumidosRecyclerAdapter;
import com.example.evaldo.firebase.activity.Administrador.util.RecyclerItemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.evaldo.firebase.activity.Administrador.DefinirQuestionarioParaTerminais.ListarQuestionarioResumidasKiosqueActivity.dispositivoKiosque;
import static com.example.evaldo.firebase.activity.Administrador.DefinirQuestionarioParaTerminais.ListarQuestionarioResumidasKiosqueActivity.listDispositivoKiosqueArrayList;
import static com.example.evaldo.firebase.activity.Administrador.DefinirQuestionarioParaTerminais.ListarQuestionarioResumidasKiosqueActivity.perguntasQuestionario;

public class ListarkiosquesActivity extends AppCompatActivity {

    private DispositivoKiosque dispositivoKiosqueFinal;
    private DispositivoKiosquesRecyclerAdapter dispositivoKiosquesRecyclerAdapter;
    private ArrayList<DispositivoKiosque> dispositivoKiosquesArrayListFinal = listDispositivoKiosqueArrayList;
    private RecyclerView recyclerView;
    private Context context;
    private TextView tvNomeDispositivo, tvQuestionarioAtual, tvNovoQuestionario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarkiosques);

        iniciarTextView();


        iniciarRecyclerView();

        iniciarFirebaseDispositivosKiosque();

        eventoClick();

    }

    private void iniciarTextView() {

        tvNomeDispositivo = findViewById(R.id.tv_ListarKiosque_Nome);
        tvQuestionarioAtual = findViewById(R.id.tv_ListarKiosque_questionarioAtual);
        tvNovoQuestionario = findViewById(R.id.tv_ListarKiosque_novoQuestionario);
    }

    private void iniciarRecyclerView() {
        try {
            recyclerView = findViewById(R.id.recyclerView_Kiosques);

            listDispositivoKiosqueArrayList = new ArrayList<>();

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            RecyclerView.ItemDecoration itemDecoration = new
                    DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);

            DispositivoKiosquesRecyclerAdapter dispositivoKiosquesRecyclerAdapter = new DispositivoKiosquesRecyclerAdapter(listDispositivoKiosqueArrayList);
            //System.out.println(dispositivoKiosquesRecyclerAdapter.toString());
            recyclerView.setAdapter(dispositivoKiosquesRecyclerAdapter);

        } catch (Exception e) {
            System.out.println("Erro iniciarRecyclerView() = " + e);
        }

    }

    public void iniciarFirebaseDispositivosKiosque() {
        DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dispositivosKiosqueReferencia = databaseReferencia.child("Dispositivos Kiosque");


        DatabaseReference referenceDispositivosKiosque = FirebaseDatabase.getInstance().getReference().child("Dispositivos Kiosque").child("idDispositivo");
        //Query query = referenceDispositivosKiosque.orderByChild("idDispositivo").equalTo("c63fd0981626ca24");

        referenceDispositivosKiosque.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listDispositivoKiosqueArrayList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        DispositivoKiosque dispositivoKiosque = data.getValue(DispositivoKiosque.class);
                        dispositivoKiosque.setKey(data.getKey());
                        listDispositivoKiosqueArrayList.add(dispositivoKiosque);
                    } catch (Exception e) {
                        System.out.println("Catch =" + e);
                    }


                }
                System.out.println("Localizados = " + listDispositivoKiosqueArrayList.size() + " resultados");
                //System.out.println(" questListResumidos.toString() = " + questListResumidos.toString());
                //System.out.println(" questListResumidos.size() = " + questListResumidos.size());

                dispositivoKiosquesRecyclerAdapter = new DispositivoKiosquesRecyclerAdapter(listDispositivoKiosqueArrayList, ListarkiosquesActivity.this);
                recyclerView.setAdapter(dispositivoKiosquesRecyclerAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(ListarResultadosResumidasActivity.this, "Erro no iniciarFirebasePerguntas", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void eventoClick() {

        RecyclerView recyclerView = findViewById(R.id.recyclerView_Kiosques);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //System.out.println("Click curto");

                        dispositivoKiosqueFinal = listDispositivoKiosqueArrayList.get(position);


                        System.out.println("perguntasQuestionario.toString() = " + perguntasQuestionario.toString());

                        tvNomeDispositivo.setText("Nome do Dispositivo: " + dispositivoKiosqueFinal.getNomeDispositivo());
                        tvQuestionarioAtual.setText("Questionário Atual do Dispositvo: " + dispositivoKiosqueFinal.getQuestionarioAtual());
                        tvNovoQuestionario.setText("Novo Questionário para o Dispositivo: " + perguntasQuestionario.getNomeQuestionario());
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        System.out.println("Click longo");

                    }
                })
        );
    }


    public void clikouConfirmarQuestionarioKiosque(View view) {

        DatabaseReference databaseReferencia = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dispositivosKiosqueReferencia = databaseReferencia.child("Dispositivos Kiosque");

        dispositivosKiosqueReferencia.child("idDispositivo").child(pegarIDDispositivo()).child("questionarioAtual").setValue(perguntasQuestionario.getNomeQuestionario());

        pegarIDDispositivo();

        Toast.makeText(this, "Questionário alterado com sucesso", Toast.LENGTH_LONG).show();
        finish();

    }

    private String pegarIDDispositivo() {
        String android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }
}


