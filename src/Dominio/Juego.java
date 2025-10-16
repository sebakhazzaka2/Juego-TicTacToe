package dominio;

import java.util.*;

/**
 *
 * @author Usuario
 */
public class Juego {

    private Sistema sistema;
    private Jugador jugadorX;
    private Jugador jugadorO;
    private Tablero tablero;
    private boolean juegoCompleto;
    private Jugador turno;
    private boolean turnoJugadorX;
    private boolean jugadaMagicaXRealizada;
    private boolean jugadaMagicaORealizada;

    public Juego() {
        this.sistema = new Sistema();
    }

    public Juego(Sistema sistema, Jugador jugadorX, Jugador jugadorO) {
        this.sistema = sistema;
        this.jugadorX = jugadorX;
        this.jugadorO = jugadorO;
        this.tablero = new Tablero();
        this.juegoCompleto = false;
        this.turnoJugadorX = true;
        this.turno = jugadorX;
        this.jugadaMagicaXRealizada = false;
        this.jugadaMagicaORealizada = false;
    }

    public Juego(Sistema sistema, Jugador jugadorX) {
        this.sistema = sistema;
        this.jugadorX = jugadorX;
        this.tablero = new Tablero();
        this.juegoCompleto = false;
        this.turnoJugadorX = true;
        this.turno = jugadorX;
        this.jugadaMagicaXRealizada = false;
        this.jugadaMagicaORealizada = false;
    }

    public Sistema getSistema() {
        return sistema;
    }

    public Jugador getJugadorX() {
        return this.jugadorX;
    }

    public void setJugadorX(String nombre, int edad, String alias) {
        this.jugadorX = new Jugador(nombre, edad, alias);
    }

    public Jugador getJugadorO() {
        return jugadorO;
    }

    public void setJugadorO(String nombre, int edad, String alias) {
        this.jugadorO = new Jugador(nombre, edad, alias);
    }

    public boolean isJuegoCompleto() {
        return juegoCompleto;
    }

    public boolean getTurnoX() {
        return this.turnoJugadorX;
    }

    public char obtenerTurno() {
        char turno = 'O';
        if (this.getTurnoX()) {
            turno = 'X';
        }
        return turno;
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public void cambiarTurno() {
        this.turnoJugadorX = !this.getTurnoX();
        if (turnoJugadorX) {
            turno = getJugadorX();
        } else {
            turno = getJugadorO();
        }
    }

    public String realizarPrimeraJugada() {
        Scanner in = new Scanner(System.in);
        System.out.println(turno.getAlias() + ", es tu turno para elegir el minicuadro.");
        System.out.print("Ingresa la posicion en el tablero (ej. A1): ");
        String posTableroStr = in.nextLine();
        int posTablero = tablero.convertirPosicion(posTableroStr);

        while (posTablero == -1) {
            System.out.println("Posicion invalida. Intenta nuevamente.");
            System.out.print("Ingresa la posicion en el tablero (ej. A1): ");
            posTableroStr = in.nextLine();
            posTablero = tablero.convertirPosicion(posTableroStr);
        }

        System.out.println(turno.getAlias() + ", es tu turno para hacer la jugada.");
        System.out.print("Ingresa la posicion en el minitablero: ");
        String posMiniTableroStr = in.nextLine();
        int posMiniTablero = tablero.convertirPosicion(posMiniTableroStr);

        while (posMiniTablero == -1 || !tablero.realizarMovimiento(posTableroStr, posMiniTablero, this.obtenerTurno())) {
            System.out.println("Movimiento invalido. Intenta nuevamente.");
            System.out.print("Ingresa la posicion en el minitablero (ej. b2): ");
            posMiniTableroStr = in.nextLine();
            posMiniTablero = tablero.convertirPosicion(posMiniTableroStr);
        }
        return posMiniTableroStr;
    }
    
    public String realizarJugadaSolo (String proximoMinicuadro) {
        Scanner in = new Scanner(System.in);
        System.out.println(turno.getAlias() + ", es tu turno.");
        System.out.print("Ingresa la posicion en el minitablero " + proximoMinicuadro + ": ");
        String posMiniTableroStr = in.nextLine();
        int posMiniTablero = tablero.convertirPosicion(posMiniTableroStr);

        if (posMiniTableroStr.equalsIgnoreCase("X")) {
            juegoCompleto = true;
            System.out.println(turno.getAlias() + " ha decidido terminar el juego y pierde.");
            return "X";
        }   

        while (posMiniTablero == -1 || !tablero.realizarMovimiento(proximoMinicuadro, posMiniTablero, this.obtenerTurno())) {
            System.out.println("Movimiento invalido. Intenta nuevamente.");
            System.out.print("Ingresa la posicion en el minitablero " + proximoMinicuadro + ": ");
            posMiniTableroStr = in.nextLine();
            posMiniTablero = tablero.convertirPosicion(posMiniTableroStr);
        }
        return posMiniTableroStr;
    }
    
    public String realizarJugadaMulti (String proximoMinicuadro) {
        Scanner in = new Scanner(System.in);
        System.out.println(turno.getAlias() + ", es tu turno.");
        System.out.print("Ingresa la posicion en el minitablero " + proximoMinicuadro + ": ");
        String posMiniTableroStr = in.nextLine();

        int posMiniTablero = tablero.convertirPosicion(posMiniTableroStr);

        if (posMiniTableroStr.equalsIgnoreCase("X")) {
            juegoCompleto = true;
            System.out.println(turno.getAlias() + " ha decidido terminar el juego y pierde.");
            return "X";
        } else if (posMiniTableroStr.equalsIgnoreCase("M")) {
            if ((turno.getAlias().equals(jugadorX.getAlias()) && !jugadaMagicaXRealizada) || 
                (turno.getAlias().equals(jugadorO.getAlias()) && !jugadaMagicaORealizada)) {
                if (turno.getAlias().equals(jugadorX.getAlias())) {
                    jugadaMagicaXRealizada = true;
                } else if (turno.getAlias().equals(jugadorO.getAlias())) {
                    jugadaMagicaORealizada = true;
                }
                return realizarJugadaMagica(proximoMinicuadro);
            } else {
                System.out.println(turno.getAlias() + ", ya has usado tu jugada magica. Ingresa otra posicion:");
                posMiniTableroStr = in.nextLine(); // Pide una nueva posición si ya usaron su jugada mágica
                posMiniTablero = tablero.convertirPosicion(posMiniTableroStr); // Actualiza posMiniTablero para el nuevo input
            }
        }

        while (posMiniTablero == -1 || !tablero.realizarMovimiento(proximoMinicuadro, posMiniTablero, this.obtenerTurno())) {
            System.out.println("Movimiento invalido. Intenta nuevamente.");
            System.out.print("Ingresa la posicion en el minitablero " + proximoMinicuadro + ": ");
            posMiniTableroStr = in.nextLine();
            posMiniTablero = tablero.convertirPosicion(posMiniTableroStr);
        }
        return posMiniTableroStr;
    }

    public String verificarEstadoJuego() {
        if (tablero.verificarGanadorGeneral()) {
            juegoCompleto = true;
            sistema.obtenerJugadorPorAlias(turno.getAlias()).incrementarPartidasGanadas();
            return turno.getAlias();
        } else if (tablero.tableroCompleto()) {
            juegoCompleto = true;
            return "Empate";
        }
        return "No";
    }

    public String jugadaRandom(String proximoMinicuadro) {
        Random random = new Random();
        int aux = random.nextInt(3);
        char letraRandom = (char) ('a' + aux);
        int numRandom = random.nextInt(3);
        String posMiniTableroStr = letraRandom + "" + numRandom;
        int posMiniTablero = tablero.convertirPosicion(posMiniTableroStr);

        while (posMiniTablero == -1 || !tablero.realizarMovimiento(proximoMinicuadro, posMiniTablero, this.obtenerTurno())) {
            aux = random.nextInt(3);
            letraRandom = (char) ('a' + aux);
            numRandom = random.nextInt(4);
            posMiniTableroStr = letraRandom + "" + numRandom;
            posMiniTablero = tablero.convertirPosicion(posMiniTableroStr);
        }
        return posMiniTableroStr;
    }
    
    public String realizarJugadaMagica(String proximoMinicuadro) {
        Scanner in = new Scanner(System.in);
        System.out.println(turno.getAlias() + ", es tu turno para realizar la jugada magica.");
        System.out.print("Ingresa la posicion en el minitablero " + proximoMinicuadro + " donde deseas hacer la jugada magica: ");
        String posMiniTableroStr = in.nextLine();
        int posMiniTablero = tablero.convertirPosicion(posMiniTableroStr);

        while (posMiniTablero == -1) {
            System.out.println("Posicion invalida. Intenta nuevamente.");
            System.out.print("Ingresa la posicion en el minitablero " + proximoMinicuadro + " donde deseas hacer la jugada magica: ");
            posMiniTableroStr = in.nextLine();
            posMiniTablero = tablero.convertirPosicion(posMiniTableroStr);
        }

        int filaMiniTablero = posMiniTablero / 3;
        int colMiniTablero = posMiniTablero % 3;
        tablero.limpiarMinicuadro(filaMiniTablero, colMiniTablero); // Limpia el minicuadro completo

        System.out.println(turno.getAlias() + ", ahora ingresa la posicion en el minitablero " + proximoMinicuadro + " para hacer tu jugada.");
        while (true) {
            int posMagica = tablero.convertirPosicion(posMiniTableroStr);

            if (posMagica != -1 && tablero.realizarMovimiento(proximoMinicuadro, posMagica, this.obtenerTurno())) {
                return posMiniTableroStr; // Devuelve la posición del próximo minicuadro
            }

            System.out.println("Movimiento invalido. Intenta nuevamente.");
            System.out.print("Ingresa la posicion en el minitablero " + proximoMinicuadro + " para hacer tu jugada (ej. b2): ");
            posMiniTableroStr = in.nextLine();
        }
    }
}
