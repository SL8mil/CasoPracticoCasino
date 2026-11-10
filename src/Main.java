import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Banca banca = new Banca();
        Ruleta ruleta = new Ruleta();
        List<Jugador> jugadores = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            jugadores.add(new JugadorNumero("JugadorNumero " + i, ruleta, banca));
        }

        for (int i = 5; i <= 8; i++) {
            jugadores.add(new JugadorParImpar("JugadorParImpar " + i, ruleta, banca));
        }

        for (int i = 9; i <= 12; i++) {
            jugadores.add(new JugadorMartingala("JugadorMartingala " + i, ruleta, banca));
        }

        for (Jugador j : jugadores) {
            j.start();
        }

        while (!ruleta.isFinSimulacion()) {
            try {
                ruleta.girarRuleta();

                Thread.sleep(3000);

            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("SIMULACIÓN FINALIZADA");
        System.out.println("Saldo final de la Banca: " + banca.getSaldo() + " €");
    }
}