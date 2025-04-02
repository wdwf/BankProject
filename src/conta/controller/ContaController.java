package conta.controller;

import java.util.ArrayList;
import java.util.Scanner;

import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.model.ContaPoupanca;
import conta.repository.ContaRepository;
import conta.util.Cores;

public class ContaController implements ContaRepository {

    private ArrayList<Conta> listaContas = new ArrayList<Conta>();
    int numero = 0;

    @Override
    public void procurarPorNumero(int numero) {
        var conta = buscarNaCollection(numero);

        if (conta != null) {
            conta.visualizar();
        } else {
            System.out.println("\nA Conta numero: " + numero + " não foi encontrada!");
        }
    }

    @Override
    public void listarTodas() {
        for (var conta : listaContas) {
            conta.visualizar();
        }
    }

    @Override
    public void cadastrar(Conta conta) {
        listaContas.add(conta);
        System.out.println("\nConta numero: " + conta.getNumero() + " foi criada com sucesso!");
    }

    @Override
    public void atualizar(Conta conta) {
        var buscaConta = buscarNaCollection(conta.getNumero());

        if (buscaConta != null) {
            listaContas.set(listaContas.indexOf(buscaConta), conta);
            System.out.println("\nA Conta numero: " + conta.getNumero() + " foi atualizada com sucesso!");
        } else {
            System.out.println("\nA Conta numero: " + conta.getNumero() + " não foi encontrada!");
        }
    }

    @Override
    public void deletar(int numero) {
        var conta = buscarNaCollection(numero);

        if (conta != null) {
            listaContas.remove(conta);
            System.out.println(Cores.TEXT_GREEN_BOLD + "\nA Conta numero: " + numero + " foi deletada com sucesso!\n"
                    + Cores.TEXT_RESET);
        } else {
            System.out.println(
                    Cores.TEXT_RED_BOLD + "\nA Conta numero: " + numero + " não foi encontrada!\n" + Cores.TEXT_RESET);
        }
    }

    @Override
    public void sacar(int numero, float valor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sacar'");
    }

    @Override
    public void depositar(int numero, float valor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'depositar'");
    }

    @Override
    public void transferir(int numeroOrigem, int numeroDestino, float valor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transferir'");
    }

    public int gerarNumero() {
        return ++numero;
    }

    public Conta buscarNaCollection(int numero) {
        for (var conta : listaContas) {
            if (conta.getNumero() == numero) {
                return conta;
            }
        }
        return null;
    }

    public void formulario(Scanner input) {
        System.out.println("Digite o número da Agência: ");
        int agencia = input.nextInt();
        System.out.println("Digite o Nome do Titular: ");
        input.skip("\\R?");
        String titular = input.nextLine();

        int tipo;
        do {
            System.out.println("Digite o Tipo da Conta: (1 - CC ou 2 - CP)");
            tipo = input.nextInt();
        } while (tipo < 1 || tipo > 2);

        System.out.println("Digite o Saldo da Conta (R$): ");
        float saldo = input.nextFloat();

        switch (tipo) {
            case 1 -> {
                System.out.println("Digite o Limite de Crédito (R$): ");
                float limite = input.nextFloat();
                cadastrar(new ContaCorrente(gerarNumero(), agencia, tipo, titular, saldo, limite));
            }

            case 2 -> {
                System.out.println("Digite o dia do Aniversário da Conta: ");
                int aniversario = input.nextInt();
                cadastrar(new ContaPoupanca(gerarNumero(), agencia, tipo, titular, saldo, aniversario));
            }
            default -> {
                System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida!\n" + Cores.TEXT_RESET);
            }
        }
    }
}
