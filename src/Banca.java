public class Banca {
    private int saldo = 50000;

    public synchronized int getSaldo() {
        return saldo;
    }

    public synchronized void cobrar(int cantidad) {
        this.saldo += cantidad;
    }

    public synchronized boolean pagar(int cantidad) {
        if (this.saldo >= cantidad) {
            this.saldo -= cantidad;
            return true;
        }
        return false;
    }
}