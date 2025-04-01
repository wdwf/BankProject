package conta.model;

public class ContaPoupanca extends Conta {
    private int aniversario;

    public ContaPoupanca(int numero, int agencia, int tipo, String titular, float saldo, int aniversario) {
        super(numero, agencia, tipo, titular, saldo);
        this.aniversario = aniversario;
    }

    private int getAniversario() {
        return this.aniversario;
    }

    private void setAniversario(int aniversario) {
        this.aniversario = aniversario;
    }

    @Override
    public void visualizar() {
        super.visualizar();
        System.out.println("Aniversário da Conta: " + this.getAniversario());
    }
}
