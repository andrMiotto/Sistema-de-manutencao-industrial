package org.example.app;


import org.example.dao.MaquinaDao;
import org.example.dao.OrdemDao;
import org.example.dao.PecaDao;
import org.example.dao.TecnicoDao;
import org.example.model.Maquina;
import org.example.model.OrdemManutencao;
import org.example.model.Peca;
import org.example.model.Tecnico;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner SC = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        inicio();

    }

    public static void inicio() throws SQLException {
        boolean sair = false;

        System.out.println("=== SISTEMA DE MANUTENÇÃO INDUSTRIAL ===");
        System.out.println("1 - Cadastrar Maquina");
        System.out.println("2 - Cadastrar Técnico");
        System.out.println("3 - Cadastrar Peça");
        System.out.println("4 - Criar ordem de manutenção");
        System.out.println("5 - Associar peças à ordem");
        System.out.println("6 - Executar manutenção");

        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");

        int opcao = SC.nextInt();
        SC.nextLine();

        switch (opcao) {
            case 1: {
                cadastrarMaquina();
                break;
            }
            case 2: {
                cadastrarTecnico();
                break;
            }
            case 3: {
                cadastrarPeca();
                break;
            }
            case 4: {
                criarManutencao();
                break;
            }
            case 5: {
                associarPecaOrdem();
                break;
            }
            case 6: {
                executarManutencao();
                break;
            }

            case 0: {
                sair = true;
                break;
            }
        }


        if (!sair) {
            inicio();
        }
    }

    public static void cadastrarMaquina() throws SQLException {
        System.out.println("===CADASTRAR MAQUINA===");
        var dao = new MaquinaDao();

        System.out.println("Digite o nome da maquina: ");
        String nome = SC.nextLine();

        System.out.println("Digite o setor da maquina: ");
        String setor = SC.nextLine();

        if (!nome.isEmpty() && !setor.isEmpty()) {

            var maquina = new Maquina(nome, setor);

            try {
                if (dao.verificaMaquinaDuplicada(maquina)) {
                    dao.cadastrarMaquina(maquina);
                    System.out.println("Maquina cadastrada com sucesso!!!");
                } else {
                    System.out.println("Maquina ja cadastrada");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("Nome da maquina ou setor não podem ser nulos");
        }

    }

    public static void cadastrarTecnico() throws SQLException {
        var dao = new TecnicoDao();
        System.out.println("CADASTRAR TECNICO");

        System.out.println("Digite o nome do tecnico");
        String nome = SC.nextLine();

        System.out.println("Digite a especialidade: ");
        String especialidade = SC.nextLine();


        var tecnico = new Tecnico(nome, especialidade);

        if (!nome.isEmpty() && !especialidade.isEmpty()) {

            try {
                if (dao.verificaTecnicoDuplicado(tecnico)) {
                    dao.cadastrarTecnico(tecnico);
                    System.out.println("Técnico cadastrado com sucesso!!!");
                } else {
                    System.out.println("Tecnico ja cadastrado");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Nome ou especialidade não podem ser nulas");
        }
    }

    public static void cadastrarPeca() throws SQLException {
        var dao = new PecaDao();
        System.out.println("===CADASTRAR PECA===");

        System.out.println("Digite o nome da peça: ");
        String nome = SC.nextLine();

        System.out.println("Digite a quantidade em estoque: ");
        Double estoque = SC.nextDouble();

        if (nome.isEmpty()) {
            var peca = new Peca(nome, estoque);

            try {
                if (dao.verificaPecaDuplicada(peca)) {
                    dao.cadastrarPeca(peca);
                    System.out.println("Peça cadastrada com sucesso!!!");
                } else {
                    System.out.println("Peça já cadastrada");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Nome não pode ser nulo");
        }
    }

    public static void criarManutencao() throws SQLException {

        var dao = new OrdemDao();
        var maquinasDao = new MaquinaDao();
        var tecnicosDao = new TecnicoDao();

        System.out.println("===      CRIAR ORDEM DE MANUTENÇÃO      ===");

        List<Maquina> listaMaquinas = maquinasDao.listarTodas();

        System.out.println(">>>       MÁQUINAS CADASTRADAS      <<<");
        for (Maquina m : listaMaquinas) {
            System.out.println("ID: "+m.getId() + "\n"+" Nome: " + m.getNome() + "\n Setor: " + m.getSetor());

        }



        System.out.println("Escolha a maquina: ");
        int idMaquina = SC.nextInt();
        SC.nextLine();

        List<Tecnico> listaTecnicos = tecnicosDao.listarTodos();



        System.out.println(">>>       TÉCNICOS DISPONÍVEIS      <<<");
        for (Tecnico t : listaTecnicos) {
            System.out.println(t.getId() + " - " + t.getNome() + " - " + t.getEspecialdiade());
        }

        System.out.println("Escolha o técnico: ");
        int idTecnico = SC.nextInt();
        SC.nextLine();

        try {
            var ordem = new OrdemManutencao(idMaquina, idTecnico);
            dao.criarOrdem(ordem);
            System.out.println("Ordem de manutenção criada com sucesso!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void associarPecaOrdem() throws SQLException {

        var dao = new OrdemDao();

        System.out.println("===      ASSOCIAR PEÇAS À ORDEM       ===");

        System.out.println("ID | Máquina(Pendente) | Técnico ");
        System.out.println("-------------------------------------------------");
        List<OrdemManutencao> listaOrdens = dao.listarTodas();

        for (OrdemManutencao o : listaOrdens) {
            System.out.println(o.getId() + "-" + o.getIdMaquina() + "-" + o.getIdTecnico());
        }

        System.out.println("Escolha a ordem: ");
        int idOrdem = SC.nextInt();
        SC.nextLine();


        var pecaDao = new PecaDao();
        List<Peca> listaPecas = pecaDao.listarTodas();


        System.out.println("ID | Nome | Estoque ");
        System.out.println("-------------------------------------------------");
        for (Peca p : listaPecas) {
            System.out.println(p.getId() + " - " + p.getNome() + " - " + p.getEstoque());
        }

        System.out.println("Escolha a peça: ");
        int idPeca = SC.nextInt();
        SC.nextLine();

        System.out.println("Digite a quantidade necessaria da peça: ");
        double quantidadeNecessaria = SC.nextDouble();
        SC.nextLine();


    }

    public static void executarManutencao() throws SQLException {

    }
}