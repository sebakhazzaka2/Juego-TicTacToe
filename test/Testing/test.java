package Testing;

import dominio.*;

/**
 *
 * @author agust
 */
public class test {

    public static void main(String[] args) {
       Sistema sys = new Sistema();
       Jugador unJugador = new Jugador("aa",21,"aa");
       sys.agregarJugador(unJugador);
       sys.mostrarRanking();
       unJugador.incrementarPartidasGanadas();
        sys.mostrarRanking();
       
    }
}
