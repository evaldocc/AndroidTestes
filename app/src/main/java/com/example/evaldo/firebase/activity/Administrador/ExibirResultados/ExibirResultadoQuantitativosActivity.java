package com.example.evaldo.firebase.activity.Administrador.ExibirResultados;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.evaldo.firebase.R;
import com.example.evaldo.firebase.activity.Administrador.Classes.PerguntasQuestionario;
import com.example.evaldo.firebase.activity.Administrador.adapter.ResultadosQuantitativosRecyclerAdapter;

import java.util.ArrayList;


import static com.example.evaldo.firebase.activity.Administrador.ExibirResultados.ListarResultadosResumidasActivity.perguntasQuestionarioQuantitativo;
import static com.example.evaldo.firebase.activity.Administrador.ExibirResultados.ListarResultadosResumidasActivity.resultListResumidos;

public class ExibirResultadoQuantitativosActivity extends AppCompatActivity {


    private int ExelenteTotal, BomTotal, RazoavelTotal, RuimTotal, PessimoTotal, SimTotal, NaoTotal, RespostaAbertaTotal, TelefoneOUEmailTotal, PreferiuNaoSeIdentificarTotal;
    private PerguntasQuestionario perguntasQuestionarioQuantitativoFinal = perguntasQuestionarioQuantitativo;
    private ArrayList<PerguntasQuestionario> pergList = new ArrayList<>();
    ResultadosQuantitativosRecyclerAdapter questAdapter;
    RecyclerView recyclerViewQuantitativo;
    private String perguntaAtual, tipoRespostaAtual;
    private int contadorVerificacoes = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exibir_resultado_da_lista);

        filtrandoResultados();

        iniciarRecyclerView();

        carregandoRecyclerView();

        verificarNomeResposta();


    }

    public void verificarNomeResposta() {


        System.out.println("decidirNumeroDaPerguntaETipoRespostasEChamarTelas()");
        if (contadorVerificacoes <= perguntasQuestionarioQuantitativo.getContPerguntas()) {

            System.out.println("if chamarProximaPergunta");
            System.out.println("contadorVerificacoes = " + contadorVerificacoes);
            contadorVerificacoes++;
            switch (contadorVerificacoes) {

                case 1:

                    System.out.println("case 1");

                    tipoRespostaAtual = perguntasQuestionarioQuantitativo.getResposta1();

                    break;

                case 2:

                    System.out.println("case 2");

                    tipoRespostaAtual = perguntasQuestionarioQuantitativo.getResposta2();

                    break;

                case 3:
                    System.out.println("case 3");

                    tipoRespostaAtual = perguntasQuestionarioQuantitativo.getResposta3();

                    break;

                case 4:
                    System.out.println("case 4");

                    tipoRespostaAtual = perguntasQuestionarioQuantitativo.getResposta4();

                    break;

                case 5:
                    System.out.println("case 5");

                    tipoRespostaAtual = perguntasQuestionarioQuantitativo.getResposta5();


                    break;

                case 6:
                    System.out.println("case 6");

                    tipoRespostaAtual = perguntasQuestionarioQuantitativo.getResposta6();

                    break;

                case 7:
                    System.out.println("case 7");

                    tipoRespostaAtual = perguntasQuestionarioQuantitativo.getResposta7();

                    break;

                case 8:
                    System.out.println("case 8");

                    tipoRespostaAtual = perguntasQuestionarioQuantitativo.getResposta8();

                    break;

                case 9:
                    System.out.println("case 9");

                    tipoRespostaAtual = perguntasQuestionarioQuantitativo.getResposta9();

                    break;

                case 10:
                    System.out.println("case 10");

                    tipoRespostaAtual = perguntasQuestionarioQuantitativo.getResposta10();

                    break;
                default:
            }

        }
        System.out.println("perguntaAtual = " + perguntaAtual);
    }

    private String verificarTipoDeResposta1(String pergQuest) {


        System.out.println("verificarTipoDeRespostas(String pergQuest)");
        System.out.println("pergList.size() = " + resultListResumidos.size());
        ExelenteTotal = 0;
        BomTotal = 0;
        RazoavelTotal = 0;
        RuimTotal = 0;
        PessimoTotal = 0;


        String resul = "";

        if (pergQuest.equals("Princial 3 Emotions e Ouvidoria")) {


            for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                //System.out.println("forPerguntasQuestionario");


                if (forPerguntasQuestionario.getResposta1().equals("(Exelente)")) {
                    ExelenteTotal++;
                }
                if (forPerguntasQuestionario.getResposta1().equals("(Razoável)")) {
                    RazoavelTotal++;
                }
                if (forPerguntasQuestionario.getResposta1().equals("(Ruim)")) {
                    RuimTotal++;
                }

            }
            resul = ("Exelente = ( " + ExelenteTotal + " ) " + "          Razoável = ( " + RazoavelTotal + " ) " + "          Ruim = ( " + RuimTotal + " )");
        }


        return resul;
    }
    private String verificarTipoDeResposta2(String pergQuest) {
        String result = "Tipo de Resposta Não localizada";

        if (pergQuest != null) {

            ExelenteTotal = 0;
            BomTotal = 0;
            RazoavelTotal = 0;
            RuimTotal = 0;
            PessimoTotal = 0;
            TelefoneOUEmailTotal = 0;
            RespostaAbertaTotal = 0;
            if (pergQuest.equals("6 Emotions")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {

                    if (forPerguntasQuestionario.getResposta2().equals("(Exelente)")) {
                        ExelenteTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Bom)")) {
                        BomTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Razoável)")) {
                        RazoavelTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Ruim)")) {
                        RuimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Péssimo)")) {
                        PessimoTotal++;
                    }

                }
                result = ("Exelente = ( " + ExelenteTotal + " ) " + "     Bom = ( " + BomTotal + " ) " + "     Razoável = ( " + RazoavelTotal + " )" + "     Ruim = ( " + RuimTotal + " ) " + "     Péssimo = ( " + PessimoTotal + " ) ");

            }
            if (pergQuest.equals("(Sim) ou (Não)")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Sim)")) {
                        SimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
            if (pergQuest.equals("Telefone e Email")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Não se identificou)")) {
                        PreferiuNaoSeIdentificarTotal++;
                    } else if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }else{
                        TelefoneOUEmailTotal++;
                    }
                }
                result = ("Deixaram Email ou Telefone = (" + TelefoneOUEmailTotal + ") Não se identificaram = (" + PreferiuNaoSeIdentificarTotal + ")");
            }
            if (pergQuest.equals("Resposta Aberta")){
                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Não se identificou)")) {
                        PreferiuNaoSeIdentificarTotal++;
                    } else if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                    result = ("Deixaram Resposta Aberta = (" + RespostaAbertaTotal + ") Não se identificaram = (" + PreferiuNaoSeIdentificarTotal + ")");

                }
            }
        }
        return result;
    }

    private String verificarTipoDeResposta3(String pergQuest) {
        String result = "Tipo de Resposta Não localizada";

        if (pergQuest != null) {

            ExelenteTotal = 0;
            BomTotal = 0;
            RazoavelTotal = 0;
            RuimTotal = 0;
            PessimoTotal = 0;
            if (pergQuest.equals("6 Emotions")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {

                    if (forPerguntasQuestionario.getResposta3().equals("(Exelente)")) {
                        ExelenteTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta3().equals("(Bom)")) {
                        BomTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta3().equals("(Razoável)")) {
                        RazoavelTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta3().equals("(Ruim)")) {
                        RuimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta3().equals("(Péssimo)")) {
                        PessimoTotal++;
                    }

                }
                result = ("Exelente = ( " + ExelenteTotal + " ) " + "     Bom = ( " + BomTotal + " ) " + "     Razoável = ( " + RazoavelTotal + " )" + "     Ruim = ( " + RuimTotal + " ) " + "     Péssimo = ( " + PessimoTotal + " ) ");

            }
            if (pergQuest.equals("(Sim) ou (Não)")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta3().equals("(Sim)")) {
                        SimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta3().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
            if (pergQuest.equals("Telefone e Email")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta3().equals("(Não se identificou)")) {
                        PreferiuNaoSeIdentificarTotal++;
                    } else if (forPerguntasQuestionario.getResposta3().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
        }
        return result;
    }
    private String verificarTipoDeResposta4(String pergQuest) {
        String result = "Tipo de Resposta Não localizada";

        if (pergQuest != null) {

            ExelenteTotal = 0;
            BomTotal = 0;
            RazoavelTotal = 0;
            RuimTotal = 0;
            PessimoTotal = 0;
            if (pergQuest.equals("6 Emotions")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {

                    if (forPerguntasQuestionario.getResposta2().equals("(Exelente)")) {
                        ExelenteTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Bom)")) {
                        BomTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Razoável)")) {
                        RazoavelTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Ruim)")) {
                        RuimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Péssimo)")) {
                        PessimoTotal++;
                    }

                }
                result = ("Exelente = ( " + ExelenteTotal + " ) " + "     Bom = ( " + BomTotal + " ) " + "     Razoável = ( " + RazoavelTotal + " )" + "     Ruim = ( " + RuimTotal + " ) " + "     Péssimo = ( " + PessimoTotal + " ) ");

            }
            if (pergQuest.equals("(Sim) ou (Não)")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Sim)")) {
                        SimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
            if (pergQuest.equals("Telefone e Email")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Não se identificou)")) {
                        PreferiuNaoSeIdentificarTotal++;
                    } else if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
        }
        return result;
    }
    private String verificarTipoDeResposta5(String pergQuest) {
        String result = "Tipo de Resposta Não localizada";

        if (pergQuest != null) {

            ExelenteTotal = 0;
            BomTotal = 0;
            RazoavelTotal = 0;
            RuimTotal = 0;
            PessimoTotal = 0;
            if (pergQuest.equals("6 Emotions")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {

                    if (forPerguntasQuestionario.getResposta2().equals("(Exelente)")) {
                        ExelenteTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Bom)")) {
                        BomTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Razoável)")) {
                        RazoavelTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Ruim)")) {
                        RuimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Péssimo)")) {
                        PessimoTotal++;
                    }

                }
                result = ("Exelente = ( " + ExelenteTotal + " ) " + "     Bom = ( " + BomTotal + " ) " + "     Razoável = ( " + RazoavelTotal + " )" + "     Ruim = ( " + RuimTotal + " ) " + "     Péssimo = ( " + PessimoTotal + " ) ");

            }
            if (pergQuest.equals("(Sim) ou (Não)")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Sim)")) {
                        SimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
            if (pergQuest.equals("Telefone e Email")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Não se identificou)")) {
                        PreferiuNaoSeIdentificarTotal++;
                    } else if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
        }
        return result;
    }
    private String verificarTipoDeResposta6(String pergQuest) {
        String result = "Tipo de Resposta Não localizada";

        if (pergQuest != null) {

            ExelenteTotal = 0;
            BomTotal = 0;
            RazoavelTotal = 0;
            RuimTotal = 0;
            PessimoTotal = 0;
            if (pergQuest.equals("6 Emotions")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {

                    if (forPerguntasQuestionario.getResposta2().equals("(Exelente)")) {
                        ExelenteTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Bom)")) {
                        BomTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Razoável)")) {
                        RazoavelTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Ruim)")) {
                        RuimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Péssimo)")) {
                        PessimoTotal++;
                    }

                }
                result = ("Exelente = ( " + ExelenteTotal + " ) " + "     Bom = ( " + BomTotal + " ) " + "     Razoável = ( " + RazoavelTotal + " )" + "     Ruim = ( " + RuimTotal + " ) " + "     Péssimo = ( " + PessimoTotal + " ) ");

            }
            if (pergQuest.equals("(Sim) ou (Não)")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Sim)")) {
                        SimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
            if (pergQuest.equals("Telefone e Email")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Não se identificou)")) {
                        PreferiuNaoSeIdentificarTotal++;
                    } else if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
        }
        return result;
    }
    private String verificarTipoDeResposta7(String pergQuest) {
        String result = "Tipo de Resposta Não localizada";

        if (pergQuest != null) {

            ExelenteTotal = 0;
            BomTotal = 0;
            RazoavelTotal = 0;
            RuimTotal = 0;
            PessimoTotal = 0;
            if (pergQuest.equals("6 Emotions")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {

                    if (forPerguntasQuestionario.getResposta2().equals("(Exelente)")) {
                        ExelenteTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Bom)")) {
                        BomTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Razoável)")) {
                        RazoavelTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Ruim)")) {
                        RuimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Péssimo)")) {
                        PessimoTotal++;
                    }

                }
                result = ("Exelente = ( " + ExelenteTotal + " ) " + "     Bom = ( " + BomTotal + " ) " + "     Razoável = ( " + RazoavelTotal + " )" + "     Ruim = ( " + RuimTotal + " ) " + "     Péssimo = ( " + PessimoTotal + " ) ");

            }
            if (pergQuest.equals("(Sim) ou (Não)")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Sim)")) {
                        SimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
            if (pergQuest.equals("Telefone e Email")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Não se identificou)")) {
                        PreferiuNaoSeIdentificarTotal++;
                    } else if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
        }
        return result;
    }
    private String verificarTipoDeResposta8(String pergQuest) {
        String result = "Tipo de Resposta Não localizada";

        if (pergQuest != null) {

            ExelenteTotal = 0;
            BomTotal = 0;
            RazoavelTotal = 0;
            RuimTotal = 0;
            PessimoTotal = 0;
            if (pergQuest.equals("6 Emotions")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {

                    if (forPerguntasQuestionario.getResposta2().equals("(Exelente)")) {
                        ExelenteTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Bom)")) {
                        BomTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Razoável)")) {
                        RazoavelTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Ruim)")) {
                        RuimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Péssimo)")) {
                        PessimoTotal++;
                    }

                }
                result = ("Exelente = ( " + ExelenteTotal + " ) " + "     Bom = ( " + BomTotal + " ) " + "     Razoável = ( " + RazoavelTotal + " )" + "     Ruim = ( " + RuimTotal + " ) " + "     Péssimo = ( " + PessimoTotal + " ) ");

            }
            if (pergQuest.equals("(Sim) ou (Não)")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Sim)")) {
                        SimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
            if (pergQuest.equals("Telefone e Email")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Não se identificou)")) {
                        PreferiuNaoSeIdentificarTotal++;
                    } else if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
        }
        return result;
    }
    private String verificarTipoDeResposta9(String pergQuest) {
        String result = "Tipo de Resposta Não localizada";

        if (pergQuest != null) {

            ExelenteTotal = 0;
            BomTotal = 0;
            RazoavelTotal = 0;
            RuimTotal = 0;
            PessimoTotal = 0;
            if (pergQuest.equals("6 Emotions")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {

                    if (forPerguntasQuestionario.getResposta2().equals("(Exelente)")) {
                        ExelenteTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Bom)")) {
                        BomTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Razoável)")) {
                        RazoavelTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Ruim)")) {
                        RuimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Péssimo)")) {
                        PessimoTotal++;
                    }

                }
                result = ("Exelente = ( " + ExelenteTotal + " ) " + "     Bom = ( " + BomTotal + " ) " + "     Razoável = ( " + RazoavelTotal + " )" + "     Ruim = ( " + RuimTotal + " ) " + "     Péssimo = ( " + PessimoTotal + " ) ");

            }
            if (pergQuest.equals("(Sim) ou (Não)")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Sim)")) {
                        SimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
            if (pergQuest.equals("Telefone e Email")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Não se identificou)")) {
                        PreferiuNaoSeIdentificarTotal++;
                    } else if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
        }
        return result;
    }
    private String verificarTipoDeResposta10(String pergQuest) {
        String result = "Tipo de Resposta Não localizada";

        if (pergQuest != null) {

            ExelenteTotal = 0;
            BomTotal = 0;
            RazoavelTotal = 0;
            RuimTotal = 0;
            PessimoTotal = 0;
            if (pergQuest.equals("6 Emotions")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {

                    if (forPerguntasQuestionario.getResposta2().equals("(Exelente)")) {
                        ExelenteTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Bom)")) {
                        BomTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Razoável)")) {
                        RazoavelTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Ruim)")) {
                        RuimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Péssimo)")) {
                        PessimoTotal++;
                    }

                }
                result = ("Exelente = ( " + ExelenteTotal + " ) " + "     Bom = ( " + BomTotal + " ) " + "     Razoável = ( " + RazoavelTotal + " )" + "     Ruim = ( " + RuimTotal + " ) " + "     Péssimo = ( " + PessimoTotal + " ) ");

            }
            if (pergQuest.equals("(Sim) ou (Não)")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Sim)")) {
                        SimTotal++;
                    }
                    if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
            if (pergQuest.equals("Telefone e Email")) {

                for (PerguntasQuestionario forPerguntasQuestionario : resultListResumidos) {
                    if (forPerguntasQuestionario.getResposta2().equals("(Não se identificou)")) {
                        PreferiuNaoSeIdentificarTotal++;
                    } else if (forPerguntasQuestionario.getResposta2().equals("(Não)")) {
                        NaoTotal++;
                    }
                }
                result = ("Sim = (" + SimTotal + ") Não = (" + NaoTotal + ")");
            }
        }
        return result;
    }



    private void filtrandoResultados() {
        System.out.println("perguntasQuestionarioQuantitativoFinal.toString() = " + perguntasQuestionarioQuantitativoFinal.toString());
        System.out.println("perguntasQuestionarioQuantitativo.toString() = " + perguntasQuestionarioQuantitativo.toString());

        perguntasQuestionarioQuantitativoFinal.setResposta1(verificarTipoDeResposta1(perguntasQuestionarioQuantitativo.getResposta1()));
        perguntasQuestionarioQuantitativoFinal.setResposta2(verificarTipoDeResposta2(perguntasQuestionarioQuantitativo.getResposta2()));
        perguntasQuestionarioQuantitativoFinal.setResposta3(verificarTipoDeResposta3(perguntasQuestionarioQuantitativo.getResposta3()));
       /* perguntasQuestionarioQuantitativoFinal.setResposta4(verificarTipoDeResposta4(perguntasQuestionarioQuantitativo.getResposta4()));
        perguntasQuestionarioQuantitativoFinal.setResposta5(verificarTipoDeResposta5(perguntasQuestionarioQuantitativo.getResposta5()));
        perguntasQuestionarioQuantitativoFinal.setResposta6(verificarTipoDeResposta6(perguntasQuestionarioQuantitativo.getResposta6()));
        perguntasQuestionarioQuantitativoFinal.setResposta7(verificarTipoDeResposta7(perguntasQuestionarioQuantitativo.getResposta7()));
        perguntasQuestionarioQuantitativoFinal.setResposta8(verificarTipoDeResposta8(perguntasQuestionarioQuantitativo.getResposta8()));
        perguntasQuestionarioQuantitativoFinal.setResposta9(verificarTipoDeResposta9(perguntasQuestionarioQuantitativo.getResposta9()));
        perguntasQuestionarioQuantitativoFinal.setResposta10(verificarTipoDeResposta10(perguntasQuestionarioQuantitativo.getResposta10()));*/


    }


    private void iniciarRecyclerView() {
        try {
            recyclerViewQuantitativo = findViewById(R.id.recyclerView_resultadosQuantitativos);

            pergList = new ArrayList<>();

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerViewQuantitativo.setLayoutManager(layoutManager);

            RecyclerView.ItemDecoration itemDecoration = new
                    DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclerViewQuantitativo.addItemDecoration(itemDecoration);

            ResultadosQuantitativosRecyclerAdapter resultadosQuantitativosRecyclerAdapter = new ResultadosQuantitativosRecyclerAdapter(pergList);

            // System.out.println("ListasRsultadosResumidos iniciarRecyclerView() questListResumidos.toString() = " + questListResumidos.toString());
            recyclerViewQuantitativo.setAdapter(resultadosQuantitativosRecyclerAdapter);

        } catch (Exception e) {
            System.out.println("Erro iniciarRecyclerView() = " + e);
        }

    }

    private void carregandoRecyclerView() {

        pergList.clear();


        pergList.add(perguntasQuestionarioQuantitativo);


        // System.out.println("perguntasQuestionarioQuantitativo = " + perguntasQuestionarioQuantitativo.toString());
        //System.out.println("resultListResumidos tamanho = " + resultListResumidos.size() + "\n resultListResumidos = " + resultListResumidos.toString());


        questAdapter = new ResultadosQuantitativosRecyclerAdapter(pergList, ExibirResultadoQuantitativosActivity.this);
        recyclerViewQuantitativo.setAdapter(questAdapter);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ListarResultadosResumidasActivity.class);
        startActivity(intent);

        finish();
    }


}




