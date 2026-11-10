import java.util.Random;

public class JugadorNumero extends Jugador {
    private final int numeroApostado;
    private final int APUESTA = 10;

    public JugadorNumero(String nombre, Ruleta ruleta, Banca banca) {
        super(nombre, ruleta, banca);
        this.numeroApostado = new Random().nextInt(36) + 1;
    }

    @Override
    protected void realizarApuesta() {
        saldo -= APUESTA;
        banca.cobrar(APUESTA);
        System.out.println(nombre + " apuesta 10 € al número " + numeroApostado + " (Saldo: " + saldo + " €)");
    }

    @Override
    protected void comprobarResultado(int resultado) {
        if (resultado != 0 && resultado == numeroApostado) {
            int premio = 360;
            if (banca.pagar(premio)) {
                saldo += premio;
                System.out.println("-> " + nombre + " GANA " + premio + " € (Saldo: " + saldo + " €)");
            } else {
                System.out.println("-> BANCA EN BANCARROTA. No puede pagar a " + nombre);
                ruleta.detenerSimulacion();
            }
        } else {
            System.out.println("-> " + nombre + " pierde (Saldo: " + saldo + " €)");
        }
    }
}