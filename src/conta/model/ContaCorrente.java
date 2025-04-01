package conta.model;

public class ContaCorrente extends Conta {
    private float limite;

    public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) {
        super(numero, agencia, tipo, titular, saldo);
        this.limite = limite;
    }

    private float getLimite() {
        return this.limite;
    }

    private void setLimite(float limite) {
        this.limite = limite;
    }

    @Override
    public boolean sacar(float valor) {

        if (this.getSaldo() + this.getLimite() < valor) {
            System.out.println("Saldo insuficiente!");
            return false;
        }
        this.setSaldo(this.getSaldo() - valor);
        return true;
    }

    @Override
    public void visualizar() {
        super.visualizar();
        System.out.println("Limite de Crédiro:" + this.getLimite());
    }
}
