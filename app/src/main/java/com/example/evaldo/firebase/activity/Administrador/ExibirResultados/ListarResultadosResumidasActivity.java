package com.example.evaldo.firebase.activity.Administrador.ExibirResultados;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.PerguntasQuestionario;
import com.example.evaldo.firebase.activity.Administrador.adapter.ResultadosResumidosRecyclerAdapter;
import com.example.evaldo.firebase.activity.Administrador.util.RecyclerItemClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListarResultadosResumidasActivity extends AppCompatActivity {

    private PerguntasQuestionario perguntasQuestionario;

    ResultadosResumidosRecyclerAdapter questAdapter;
    public ArrayList<PerguntasQuestionario> questListResumidos;
    public static ArrayList<PerguntasQuestionario> resultListResumidos = new ArrayList<>();
    RecyclerView recyclerView;

    private DatabaseReference referencePerguntas;
    private DatabaseReference referenceRespostas;

    Context context = ListarResultadosResumidasActivity.this;

    //-------------------Variáveias para dados quantitativos---------------------------------------
    //private Context context;
    // private ArrayList<PerguntasQuestionario> pergList = new ArrayList<PerguntasQuestionario>();

    //private DatabaseReference referenceRespostas = FirebaseDatabase.getInstance().getReference();
    public static PerguntasQuestionario perguntasQuestionarioQuantitativo;
    //ResultadosQuantitativosRecyclerAdapter questAdapter;
    private Query query;
    private TextView tvNomeQuestionario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_resultados_resumidos);


        tvNomeQuestionario = findViewById(R.id.tv_listarResultadosResumidos_nomeQuestionario);

        iniciarRecyclerView();

/////////Inicializando metodo para atualizar do iniciarFirebasePerguntas

        iniciarFirebasePerguntas();

        eventoClick();

    }

    private void iniciarRecyclerView() {
        try {
            recyclerView = findViewById(R.id.recycler_view_mostrar_resultados_resumidos);

            questListResumidos = new ArrayList<>();

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);

            RecyclerView.ItemDecoration itemDecoration = new
                    DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerView.addItemDecoration(itemDecoration);

            ResultadosResumidosRecyclerAdapter resultadosResumidosRecyclerAdapter = new ResultadosResumidosRecyclerAdapter(questListResumidos);

            // System.out.println("ListasRsultadosResumidos iniciarRecyclerView() questListResumidos.toString() = " + questListResumidos.toString());
            recyclerView.setAdapter(resultadosResumidosRecyclerAdapter);

        } catch (Exception e) {
            System.out.println("Erro iniciarRecyclerView() = " + e);
        }

    }


    public void clicouConfirmarQuestionario(View view) {
        iniciarFirebaseResultados();
        finish();
        chamarClasseExibirResultadosQuantativos();

    }

    private void chamarClasseExibirResultadosQuantativos() {
        Intent intent1 = new Intent(context, ExibirResultadoQuantitativosActivity.class);
        startActivity(intent1);
    }

    private void iniciarFirebasePerguntas() {
        referencePerguntas = FirebaseDatabase.getInstance().getReference().child("Banco Perguntas Questionario").child("id");
        referencePerguntas.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                questListResumidos.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        PerguntasQuestionario quest = data.getValue(PerguntasQuestionario.class);
                        quest.setKey(data.getKey());
                        questListResumidos.add(quest);
                        //System.out.println("data.toString() = " + data.toString());
                    } catch (Exception e) {
                        System.out.println("Catch");
                    }


                }

                questAdapter = new ResultadosResumidosRecyclerAdapter(questListResumidos, ListarResultadosResumidasActivity.this);
                recyclerView.setAdapter(questAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListarResultadosResumidasActivity.this, "Erro no iniciarFirebasePerguntas", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void iniciarFirebaseResultados() {

        DatabaseReference referenceRespostas = FirebaseDatabase.getInstance().getReference().child("Banco Respostas Questionário").child("id");

        Query query = referenceRespostas.orderByChild("nomeQuestionario").equalTo(perguntasQuestionarioQuantitativo.getNomeQuestionario());

        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                resultListResumidos.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try {
                        PerguntasQuestionario quest = data.getValue(PerguntasQuestionario.class);
                        quest.setKey(data.getKey());
                        resultListResumidos.add(quest);
                        //System.out.println("data.toString() = " + data.toString());
                    } catch (Exception e) {
                        System.out.println("Catch");
                    }


                }
                System.out.println("Localizados = " + resultListResumidos.size() + " resultados");
                //System.out.println(" questListResumidos.toString() = " + questListResumidos.toString());
                //System.out.println(" questListResumidos.size() = " + questListResumidos.size());

                // questAdapter = new ResultadosResumidosRecyclerAdapter(resultListResumidos, ListarResultadosResumidasActivity.this);
                //recyclerView.setAdapter(questAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Toast.makeText(ListarResultadosResumidasActivity.this, "Erro no iniciarFirebasePerguntas", Toast.LENGTH_LONG).show();
            }
        });

    }

    private void eventoClick() {

        RecyclerView recyclerView = findViewById(R.id.recycler_view_mostrar_resultados_resumidos);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(context, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //System.out.println("Click curto");

                        perguntasQuestionario = questListResumidos.get(position);

                        perguntasQuestionarioQuantitativo = perguntasQuestionario;

                        tvNomeQuestionario.setText("Nome do Questionário: " + perguntasQuestionario.getNomeQuestionario());

                        iniciarFirebaseResultados();

                        //System.out.println("perguntasQuestionario do click = " + perguntasQuestionario);
                        //chamarClasseExibirResultadosQuantativos();


                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        System.out.println("Click longo");

                    }
                })
        );
    }


}
