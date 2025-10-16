package interfaz;

import dominio.*;
import java.util.Collections;
import java.util.Scanner;


public class Menu {

    private static Sistema nuevoSistema = new Sistema();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("   GRAN TATETI   ");
            System.out.println("1- Registrar jugador");
            System.out.println("2- Jugar Gran Tateti entre 2 personas");
            System.out.println("3- Jugar Gran Tateti vs la Computadora");
            System.out.println("4- Ver ranking");
            System.out.println("5- Salir");
            System.out.print("Ingrese una opcion (Del 1 al 5): ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    nuevoSistema.registrarJugador();
                    break;
                case 2:
                    jugarTatetiMulti();
                    break;
                case 3:
                    jugarTatetiSolo();
                    break;
                case 4:
                    mostrarRanking(nuevoSistema);
                    break;
                case 5:
                    System.out.println("  FIN DE JUEGO  ");
                    break;
                default:
                    System.out.println("La opcion ingresada no es valida, intentelo de nuevo");
                    break;
            }
        } while (opcion != 5);
    }

    public static void jugarTatetiMulti() {
        System.out.println("   JUGAR VERSION MULTIJUGADOR   ");
        if (nuevoSistema.getListaJugadores().size() > 1) {
            System.out.println("Elegir Jugador X: ");
            Jugador jugadorX = nuevoSistema.elegirJugador("X");
            System.out.println("Elegir Jugador O: ");
            Jugador jugadorO = nuevoSistema.elegirJugador("O");

            if (jugadorX != null && jugadorO != null) {
                Juego juego = new Juego(nuevoSistema, jugadorX, jugadorO);
                Tablero tablero = juego.getTablero();
                String ganador = "No";
                String turno;
                String proximoMinicuadro = juego.realizarPrimeraJugada();
                juego.setJugadorX(jugadorX.getNombre(), jugadorX.getEdad(), jugadorX.getAlias());
                juego.setJugadorO(jugadorO.getNombre(), jugadorO.getEdad(), jugadorO.getAlias());
                nuevoSistema.agregarCantidadPartidas(jugadorX.getAlias());
                nuevoSistema.agregarCantidadPartidas(jugadorO.getAlias());
                tablero.imprimirT();
                juego.cambiarTurno();
                while (ganador.equals("No") && !proximoMinicuadro.equals("X")) {
                    turno = String.valueOf(juego.obtenerTurno());
                    System.out.println("Turno del jugador " + turno);
                    proximoMinicuadro = juego.realizarJugadaMulti(proximoMinicuadro);
                    ganador = juego.verificarEstadoJuego();
                    juego.cambiarTurno();
                    tablero.imprimirT();
                }
                if (proximoMinicuadro.equals("X")) {
                    System.out.println("El juego ha terminado porque un jugador abandono la partida.");
                } else {
                    tablero.imprimirT();
                    if (ganador.equals("Empate")) {
                        System.out.println("Resultado: Empate");
                    } else {
                        System.out.println("El ganador es: " + ganador);
                    }
                }
            } else {
                System.out.println("Error al seleccionar los jugadores. Por favor, intente nuevamente.");
            }
        } else {
            System.out.println("Registre al menos dos jugadores para proceder a jugar.");
        }
    }

    public static void jugarTatetiSolo() {
        System.out.println("   JUGAR VERSION CONTRA LA COMPUTADORA   ");
        if (nuevoSistema.getListaJugadores().size() >= 1) {
            System.out.println("Elegir Jugador X: ");
            Jugador jugadorX = nuevoSistema.elegirJugador("X");

            Jugador jugadorO = new Jugador("COMPUTADORA", 21, "COMPUTADORA");
            if (jugadorX != null && jugadorO != null) {
                Juego juego = new Juego(nuevoSistema, jugadorX, jugadorO);
                Tablero tablero = juego.getTablero();
                String ganador = "No";
                String turno;
                String proximoMinicuadro = juego.realizarPrimeraJugada();
                juego.setJugadorX(jugadorX.getNombre(), jugadorX.getEdad(), jugadorX.getAlias());
                juego.setJugadorO(jugadorO.getNombre(), jugadorO.getEdad(), jugadorO.getAlias());
                nuevoSistema.agregarCantidadPartidas(jugadorX.getAlias());
                nuevoSistema.agregarCantidadPartidas(jugadorO.getAlias());
                tablero.imprimirT();
                juego.cambiarTurno();
                while (ganador.equals("No") && !proximoMinicuadro.equals("X")) {
                    turno = String.valueOf(juego.obtenerTurno());
                    System.out.println("Turno del jugador " + turno);
                    if (juego.getTurnoX()) {
                        proximoMinicuadro = juego.realizarJugadaSolo(proximoMinicuadro);
                        ganador = juego.verificarEstadoJuego();
                        juego.cambiarTurno();
                        System.out.println(juego.getTurnoX());
                    } else {
                        proximoMinicuadro = juego.jugadaRandom(proximoMinicuadro);
                        ganador = juego.verificarEstadoJuego();
                        juego.cambiarTurno();
                        System.out.println(juego.getTurnoX());
                    }
                    tablero.imprimirT();
                }
                if (proximoMinicuadro.equals("X")) {
                    System.out.println("El juego ha terminado porque un jugador abandono la partida.");
                } else {
                    tablero.imprimirT();
                    if (ganador.equals("Empate")) {
                        System.out.println("Resultado: Empate");
                    } else {
                        System.out.println("El ganador es: " + ganador);
                    }
                }
            } else {
                System.out.println("Error al seleccionar los jugadores. Por favor, intente nuevamente.");
            }
        } else {
            System.out.println("Registre al menos un jugador para proceder a jugar.");
        }
    }

    public static void mostrarRanking(Sistema sistema) {
        sistema.mostrarRanking();
    }

}
