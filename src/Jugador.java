public abstract class Jugador extends Thread {
    protected String nombre;
    protected int saldo = 1000;
    protected Ruleta ruleta;
    protected Banca banca;

    public Jugador(String nombre, Ruleta ruleta, Banca banca) {
        this.nombre = nombre;
        this.ruleta = ruleta;
        this.banca = banca;
    }

    public int getSaldo() {
        return saldo;
    }

    @Override
    public void run() {
        try {
            while (!ruleta.isFinSimulacion() && saldo > 0) {
                if (!ruleta.apostar()) break;

                realizarApuesta();

                int resultado = ruleta.esperarResultado();
                if (ruleta.isFinSimulacion()) break;

                comprobarResultado(resultado);
                ruleta.finalizarComprobacion();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected abstract void realizarApuesta();
    protected abstract void comprobarResultado(int resultado);
}