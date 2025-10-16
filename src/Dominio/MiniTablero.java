package dominio;

public class MiniTablero {

    private char[][] matriz = {{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
    
    private char ganador = ' ';

    public char getGanador() {
        return ganador;
    }

    public void setGanador(char ganador) {
        if (this.getGanador() == ' '){
             this.ganador = ganador;
        }
    }

    public String getFila(int fila) {
        String reset = "\u001B[0m";
        String rojo = "\u001B[31m";
        String azul = "\u001B[34m";
        String separador = "|";
        if (this.getGanador() == 'O') {
            separador = azul + "|" + reset;
        }
        if (this.getGanador() == 'X') {
            separador = rojo + "|" + reset;
        }
        String pos0 = (matriz[fila][0] == 'O') ? azul + matriz[fila][0] + reset : rojo + matriz[fila][0] + reset;
        String pos1 = (matriz[fila][1] == 'O') ? azul + matriz[fila][1] + reset : rojo + matriz[fila][1] + reset;
        String pos2 = (matriz[fila][2] == 'O') ? azul + matriz[fila][2] + reset : rojo + matriz[fila][2] + reset;
        return pos0 + separador + pos1 + separador + pos2;
    }

    public String posConColor(int fila, int columna){
        String reset = "\u001B[0m";
        String rojo = "\u001B[31m";
        String azul = "\u001B[34m";
        return (matriz[fila][columna]=='O')?azul+matriz[fila][columna]+reset:rojo+matriz[fila][columna]+reset;
    }
    
    public MiniTablero() {
        matriz = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matriz[i][j] = ' ';
            }
        }
    }

    public boolean hacerJugada(int fila, int columna, char jugador) {
        if (matriz[fila][columna] == ' ') {
            matriz[fila][columna] = jugador;
            return true;
        } else {
            return false;
        }
    }

    public boolean esGanador() {
        // Comprobar filas
        for (int i = 0; i < 3; i++) {
            if (matriz[i][0] != ' ' && matriz[i][0] == matriz[i][1] && matriz[i][1] == matriz[i][2]) {
                this.setGanador(matriz[i][1]);
                return true;
            }
        }
        // Comprobar columnas
        for (int i = 0; i < 3; i++) {
            if (matriz[0][i] != ' ' && matriz[0][i] == matriz[1][i] && matriz[1][i] == matriz[2][i]) {
                this.setGanador(matriz[1][i]);
                return true;
            }
        }
        // Comprobar diagonales
        if (matriz[0][0] != ' ' && matriz[0][0] == matriz[1][1] && matriz[1][1] == matriz[2][2]) {
            this.setGanador(matriz[1][1]);
            return true;
        }
        if (matriz[0][2] != ' ' && matriz[0][2] == matriz[1][1] && matriz[1][1] == matriz[2][0]) {
            this.setGanador(matriz[1][1]);
            return true;
        }
        return false;
    }

    public boolean estaCompleto() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matriz[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    
    public void limpiarCelda(int fila, int col) {
        matriz[fila][col] = ' ';
    }
    
}
