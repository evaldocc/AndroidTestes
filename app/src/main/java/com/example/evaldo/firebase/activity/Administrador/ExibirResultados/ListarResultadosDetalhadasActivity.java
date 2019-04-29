package com.example.evaldo.firebase.activity.Administrador.ExibirResultados;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.evaldo.firebase.activity.Administrador.Classes.ResultadosQuestionario;
import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.MainActivity;
import com.example.evaldo.firebase.activity.Administrador.adapter.ResultadosDetalhadasRecyclerAdapter;
import com.example.evaldo.firebase.activity.Administrador.adapter.ResultadosResumidosRecyclerAdapter;
import com.example.evaldo.firebase.activity.Administrador.util.DialogAlerta;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ListarResultadosDetalhadasActivity extends AppCompatActivity {


    ResultadosQuestionario questClass;
    ResultadosDetalhadasRecyclerAdapter questAdapter;
    public static ArrayList<ResultadosQuestionario> questList;
    RecyclerView recyclerView;

    private DatabaseReference reference;

    int contTela = 0;
    String id;
    int contDataSnapshot = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_resultados_detalhados);

        //carregandoListTestes();

        //System.out.println("________________________________ListarRultadosDetalhados resultadosQuestionario" + resultadosQuestionario);

        iniciarRecyclerView();

        recebendoDadosBundle();

/////////Inicializando metodo para atualizar do iniciarFirebase
        iniciarFirebase();



    }

    private void iniciarFirebase() {
        reference = FirebaseDatabase.getInstance().getReference().child("Banco Respostas Questionário").child("id");
        reference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                questList.clear();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    try{
                        ResultadosQuestionario quest = data.getValue(ResultadosQuestionario.class);
                        quest.setKey(data.getKey());
                        questList.add(quest);
                        //System.out.println(data.toString());
                    }catch (Exception e){
                        System.out.println("Catch");
                    }


                }
                questAdapter = new ResultadosDetalhadasRecyclerAdapter(questList, ListarResultadosDetalhadasActivity.this);
                recyclerView.setAdapter(questAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListarResultadosDetalhadasActivity.this, "Erro no iniciarFirebase", Toast.LENGTH_LONG).show();
            }
        });
    }
//////////////////////////////////////FIM////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////METODOS///////////////////////////////////////////////////////////////////////////////////

    /////////////////////////MENU INFLANDO///////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_listar_resultados, menu);

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

                item_gerarPDF();
                return true;
            case R.id.item_voltar:

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                //item_voltar();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

////////////////////////////////// PDF////////////////////////////////////////////////////////////////////////

    ////////////////////////////PDF GERAR////////////////////////////////////////////////////////////////////////

    private void item_gerarPDF() {
        if (questList != null) {

            try {
                gerarPDF();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        } else {
            DialogAlerta alerta = new DialogAlerta("Erro ao Gerar PDF", "Não existe nenhuma imagem para gerar PDF");
            alerta.show(getSupportFragmentManager(), "1");
        }
    }

    /////////////////// PDF////////////////////////////////////////////////////////////////////////////
    private void gerarPDF() throws FileNotFoundException, DocumentException {

        File diretorio = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        Toast.makeText(this, "Criar PDF", Toast.LENGTH_LONG).show();

        String nome_Arquivo = diretorio.getPath() + "/" + "Coleta de dados" + System.currentTimeMillis() + ".pdf";
        System.out.println("String" + nome_Arquivo);

        File pdf = new File(nome_Arquivo);

        OutputStream outputStream = new FileOutputStream(pdf);

        com.itextpdf.text.Document document = new Document();
        com.itextpdf.text.pdf.PdfWriter whiter = PdfWriter.getInstance(document, outputStream);
        whiter.setBoxSize("iniciarFirebase", new Rectangle(36, 54, 559, 788));

        document.open();

        Font fontGrande = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Font fontMedia = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font fontPequena = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD);

        Paragraph paragraph = new Paragraph("COLETA DE DADOS " + " \n " + "ARQUIVO PDF GERADO EM " + pegandoHora() + " \n", fontGrande);

        paragraph.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph);

        ////////Prenchendo o PDF
        ListItem item = new ListItem();
        Paragraph paragraph2;
        Paragraph paragraph3;
        ///Verificando e preenchendo o paragrafo com dos dados da coleta
        for (int i = 0; i < questList.size(); i++) {
            paragraph2 = new Paragraph(String.valueOf(questList.get(i).getHora()), fontMedia);
            item.add(paragraph2);
            if (questList.get(i).getPergunta1() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getPergunta1()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getResposta1() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getResposta1()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getPergunta2() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getPergunta2()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getResposta2() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getResposta2()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getPergunta3() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getPergunta3()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getResposta3() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getResposta3()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getPergunta4() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getPergunta4()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getResposta4() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getResposta4()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getPergunta5() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getPergunta5()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getResposta5() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getResposta5()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getPergunta6() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getPergunta6()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getResposta6() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getResposta6()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getPergunta7() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getPergunta7()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getResposta7() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getResposta7()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getPergunta8() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getPergunta8()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getResposta8() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getResposta8()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getPergunta9() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getPergunta9()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getResposta9() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getResposta9()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getPergunta10() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getPergunta10()), fontPequena);
                item.add(paragraph3);
            }
            if (questList.get(i).getResposta10() != null) {
                paragraph3 = new Paragraph(String.valueOf(questList.get(i).getResposta10()), fontPequena);
                item.add(paragraph3);
            }

        }
        document.add(item);


        document.close();

        visualizarPDF(pdf);

    }

    ///////////////////PDF VISUALIZAR////////////////////////////////////////////////////////////////

    private void visualizarPDF(File pdf) {

        PackageManager packageManager = getPackageManager();
        Intent itent = new Intent(Intent.ACTION_VIEW);

        itent.setType("application/pdf");

        List<ResolveInfo> lista = packageManager.queryIntentActivities(itent, PackageManager.MATCH_DEFAULT_ONLY);

        if (lista.size() > 0) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

            Uri uri = FileProvider.getUriForFile(getBaseContext(), "com.exemplo.firebasecursods", pdf);

            intent.setDataAndType(uri, "application/pdf");

            startActivity(intent);
        } else {
            DialogAlerta dialogAlerta = new DialogAlerta("Erro ao Abrir PDF", "Não foi detectado nenhum leitor de PDF no seu DispositivoAdministrador");
            dialogAlerta.show(getSupportFragmentManager(), "3");
        }
    }


    private void carregandoListTestes() {
       // questClass = new ResultadosQuestionario("2", "2", "16:15", "BCC", "Evaldo Administrador", "Primeira Pergunta", "Primeira Resposta", "Segunda Pergunta","Segunta Respostas");
        //System.out.println("questionario1 = " + questClass);
        questList = new ArrayList<ResultadosQuestionario>();
        questList.add(questClass);
        questList.add(questClass);
        questList.add(questClass);
        //System.out.println("questListResumidos = " + questListResumidos);
    }

    private void recebendoDadosBundle() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {

            id = bundle.getString("id");
            //System.out.println("id = " + id);
            contTela = bundle.getInt("contTela");
            // System.out.println("contTela = " +  contTela);
        }
    }

    private void iniciarRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view_mostrar_resultados);

        questList = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);

        ResultadosDetalhadasRecyclerAdapter resultadosDetalhadasRecyclerAdapter= new ResultadosDetalhadasRecyclerAdapter(questList);

        System.out.println("class ListarResultadosDetalhadasActivity questaList = ");

        recyclerView.setAdapter(resultadosDetalhadasRecyclerAdapter);
    }

    private String pegandoHora() {
        SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatHora = new SimpleDateFormat("HH:mm:ss");
        Date dataCal = new Date();
        Date dataHora = new Date();
        String dataFormatada = formataData.format(dataCal);
        String horaFormatada = formatHora.format(dataHora);
        String dataEHora = "Data " + dataFormatada + " Hora " + horaFormatada;
        //System.out.println("Data " + dataFormatada + " Hora " + horaFormatada);

        return dataEHora;
    }


}

