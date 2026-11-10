import java.util.Random;

public class JugadorParImpar extends Jugador {
    private boolean esPar;
    private final int APUESTA = 10;
    private final Random random = new Random();

    public JugadorParImpar(String nombre, Ruleta ruleta, Banca banca) {
        super(nombre, ruleta, banca);
    }

    @Override
    protected void realizarApuesta() {
        esPar = random.nextBoolean();
        saldo -= APUESTA;
        banca.cobrar(APUESTA);
        System.out.println(nombre + " apuesta 10 € a " + (esPar ? "PAR" : "IMPAR") + " (Saldo: " + saldo + " €)");
    }

    @Override
    protected void comprobarResultado(int resultado) {
        boolean acierto = (resultado != 0) && ((resultado % 2 == 0) == esPar);
        if (acierto) {
            int premio = 20;
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