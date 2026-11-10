import java.util.Random;

public class JugadorMartingala extends Jugador {
    private final int numeroApostado;
    private int apuestaActual = 10;

    public JugadorMartingala(String nombre, Ruleta ruleta, Banca banca) {
        super(nombre, ruleta, banca);
        this.numeroApostado = new Random().nextInt(36) + 1;
    }

    @Override
    protected void realizarApuesta() {
        if (apuestaActual > saldo) {
            apuestaActual = saldo;
        }
        saldo -= apuestaActual;
        banca.cobrar(apuestaActual);
        System.out.println(nombre + " apuesta " + apuestaActual + " € al número " + numeroApostado + " (Saldo: " + saldo + " €)");
    }

    @Override
    protected void comprobarResultado(int resultado) {
        if (resultado != 0 && resultado == numeroApostado) {
            int premio = apuestaActual * 36;
            if (banca.pagar(premio)) {
                saldo += premio;
                System.out.println("-> " + nombre + " GANA " + premio + " € (Saldo: " + saldo + " €)");
                apuestaActual = 10;
            } else {
                System.out.println("-> BANCA EN BANCARROTA. No puede pagar a " + nombre);
                ruleta.detenerSimulacion();
            }
        } else {
            System.out.println("-> " + nombre + " pierde (Saldo: " + saldo + " €)");
            apuestaActual *= 2;
        }
    }
}