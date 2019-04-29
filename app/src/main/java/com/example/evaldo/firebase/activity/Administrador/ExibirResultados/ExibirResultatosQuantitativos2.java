package com.example.evaldo.firebase.activity.Administrador.ExibirResultados;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.adapter.ResultadosQuantitativosAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ExibirResultatosQuantitativos2 extends AppCompatActivity {

    private DatabaseReference root = FirebaseDatabase.getInstance().getReference("Banco Respostas Questionário").child("id");
    private HashMap<String, HashMap<String, Integer>> perguntas = new HashMap<>();
    private ResultadosQuantitativosAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_resultatos_quantitativos2);

        adapter = new ResultadosQuantitativosAdapter();

        RecyclerView recycler = findViewById(R.id.recyclerViewResultados);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    //todo verificar se nome questionário é igual ao questinario procurado
                    for (DataSnapshot childChild : child.getChildren()) {

                        if (childChild.getKey().contains("resposta")){
                            String pergunta = childChild.getKey();
                            String resp = (String) childChild.getValue();

                            count(pergunta, resp);
                        }

                    }


                }

                adapter.update(perguntas);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void count(String pergunta, String resposta){


        if (resposta == null) resposta = "vazio";

        if (!perguntas.containsKey(pergunta)) perguntas.put(pergunta, new HashMap<String, Integer>());

        if (!perguntas.get(pergunta).containsKey(resposta)) perguntas.get(pergunta).put(resposta, 0);

        HashMap<String, Integer> respostas = perguntas.get(pergunta);

        respostas.put(resposta, respostas.get(resposta) + 1);
    }


}
