import java.util.Random;

public class Ruleta {
    private int numeroGanador = -1;
    private int apuestasRealizadas = 0;
    private int comprobacionesRealizadas = 0;
    private final int TOTAL_JUGADORES = 12;
    private boolean tiradaLista = false;
    private boolean finSimulacion = false;
    private final Random random = new Random();

    private int fase = 0;

    public synchronized boolean apostar() throws InterruptedException {
        while (fase != 0 && !finSimulacion) {
            wait();
        }
        if (finSimulacion) return false;

        apuestasRealizadas++;
        if (apuestasRealizadas == TOTAL_JUGADORES) {
            fase = 1;
            notifyAll();
        }
        return true;
    }

    public synchronized void girarRuleta() throws InterruptedException {
        while (fase != 1 && !finSimulacion) {
            wait();
        }
        if (finSimulacion) return;

        numeroGanador = random.nextInt(37);
        System.out.println("\n--- NUEVA TIRADA: " + numeroGanador + " ---");

        apuestasRealizadas = 0;
        comprobacionesRealizadas = 0;
        fase = 2;
        notifyAll();
    }

    public synchronized int esperarResultado() throws InterruptedException {
        while (fase != 2 && !finSimulacion) {
            wait();
        }
        return numeroGanador;
    }

    public synchronized void finalizarComprobacion() {
        comprobacionesRealizadas++;
        if (comprobacionesRealizadas == TOTAL_JUGADORES) {
            fase = 0;
            notifyAll();
        }
    }

    public synchronized void detenerSimulacion() {
        this.finSimulacion = true;
        notifyAll();
    }

    public synchronized boolean isFinSimulacion() {
        return finSimulacion;
    }
}