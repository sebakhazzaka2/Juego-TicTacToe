package dominio;


public class Tablero {

    private MiniTablero[][] m = new MiniTablero[3][3];

    private int posicion = -1;

    public int getPosicion() {
        return this.posicion;
    }

    public void setPosicion(int unaPosicion) {
        this.posicion = unaPosicion;
    }

    public Tablero() {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                m[i][j] = new MiniTablero();
            }
        }
    }

    public void imprimirT() {
        int fila = -1;
        int columna = -1;
        if (this.getPosicion() >= 0) {
            fila = this.getPosicion() / 3;
            columna = this.getPosicion() % 3;
        }
        String verde = "\u001B[42m"; // Código ANSI para verde en el fondo
        String reset = "\033[0m";  // Código ANSI para resetear el color
        String amarillo = "\u001B[43m";
        String rojo = "\u001B[31m";
        String azul = "\u001B[34m";
        int inicio = columna * 6;
        for (int i = 0; i < 19; i++) {
            if (fila == 0 && i >= inicio && i < inicio + 7) {
                System.out.print(amarillo + "*" + reset);
            } else {
                System.out.print(verde + "*" + reset);
            }
        }
        System.out.println("");
        for (int i = 0; i < 3; i++) { // Recorre las filas del tablero
            for (int filaMinicuadrado = 0; filaMinicuadrado < 3; filaMinicuadrado++) { // Recorre las filas de los Minicuadrados
                for (int j = 0; j < 3; j++) { // Recorre las columnas del tablero
                    if (fila == i && j == 0 && columna == 0) {
                        System.out.print(amarillo + "*" + reset);
                    } else if (j == 0) {
                        System.out.print(verde + "*" + reset);
                    }
                    m[i][j].esGanador();
                    System.out.print(m[i][j].getFila(filaMinicuadrado)); // Imprime la fila actual del Minicuadrado
                    if (j < 3) {
                        if (fila == i && (columna - j == 0 || columna - j == 1)) {
                            System.out.print(amarillo + "*" + reset);
                        } else {
                            System.out.print(verde + "*" + reset);
                        }
                    }
                }
                System.out.println();
                if (filaMinicuadrado < 2) {
                    for (int j = 0; j < 3; j++) { // Recorre las columnas del tablero
                        if (fila == i && j == 0 && columna == 0) {
                            System.out.print(amarillo + "*" + reset);
                        } else if (j == 0) {
                            System.out.print(verde + "*" + reset);
                        }
                        if (m[i][j].getGanador() == ' '){
                            System.out.print("-+-+-");
                        }
                        if (m[i][j].getGanador() == 'X'){
                            System.out.print(rojo + "-+-+-"+ reset);
                        }
                        if (m[i][j].getGanador() == 'O'){
                            System.out.print(azul + "-+-+-" + reset);
                        }
                         // Imprime la fila actual del Minicuadrado
                        if (j < 3) {
                            if (fila == i && (columna - j == 0 || columna - j == 1)) {
                                System.out.print(amarillo + "*" + reset);
                            } else {
                                System.out.print(verde + "*" + reset);
                            }
                        }
                    }
                    System.out.println("");
                }// Nueva línea al terminar una fila de minitableros
            }
            if (i < 3) {
                for (int col = 0; col < 19; col++) {
                    if (fila - i >= 0 && fila - i <= 1 && col >= inicio && col < inicio + 7) {
                        System.out.print(amarillo + "*" + reset);
                    } else {
                        System.out.print(verde + "*" + reset);
                    }
                }
                System.out.println("");
            }
        }
    }

    public boolean verificarGanadorGeneral() {
        for (int i = 0; i < 3; i++) {
            if (m[i][0].esGanador() && m[i][0].esGanador() == m[i][1].esGanador() && m[i][1].esGanador() == m[i][2].esGanador()) {
                return true;
            }
        }
        
        for (int i = 0; i < 3; i++) {
            if (m[0][i].esGanador() && m[0][i].esGanador() == m[1][i].esGanador() && m[1][i].esGanador() == m[2][i].esGanador()) {
                return true;
            }
        }

        if (m[0][0].esGanador() && m[0][0].esGanador() == m[1][1].esGanador() && m[1][1].esGanador() == m[2][2].esGanador()) {
            return true;
        }
        if (m[0][2].esGanador() && m[0][2].esGanador() == m[1][1].esGanador() && m[1][1].esGanador() == m[2][0].esGanador()) {
            return true;
        }

        return false;
    }
    
    public boolean verificarGanador(){
         for (int i = 0; i < 3; i++) {
            if (m[i][0].getGanador() != ' ' && m[i][0].getGanador() == m[i][1].getGanador() && m[i][1].getGanador() == m[i][2].getGanador()) {
                return true;
            }
        }
         
        for (int i = 0; i < 3; i++) {
            if (m[0][i].getGanador() != ' ' && m[0][i].getGanador() == m[1][i].getGanador() && m[1][i].getGanador() == m[2][i].getGanador()) {
                return true;
            }
        }
        
        if (m[0][0].getGanador() != ' ' && m[0][0].getGanador() == m[1][1].getGanador() && m[1][1].getGanador() == m[2][2].getGanador()) {
            return true;
        }
        if (m[0][2].getGanador() != ' ' && m[0][2].getGanador() == m[1][1].getGanador() && m[1][1].getGanador() == m[2][0].getGanador()) {
            return true;
        }
        return false;
    }

    public boolean tableroCompleto() { 
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!m[i][j].estaCompleto()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean realizarMovimiento(String proximoMinicuadro, int pos, char jugador) { 
        int filaMiniTablero = convertirPosicion(proximoMinicuadro) / 3;
        int colMiniTablero = convertirPosicion(proximoMinicuadro) % 3;
        int filaEnMiniTablero = pos / 3;
        int colEnMiniTablero = pos % 3;

        if (m[filaMiniTablero][colMiniTablero].hacerJugada(filaEnMiniTablero, colEnMiniTablero, jugador)) {
            this.setPosicion(pos);
            return true;
        }
        return false;
    }
    
    public boolean realizarMovimientoTEST(int posMiniTablero, char jugador) {
        int filaMiniTablero = this.getPosicion() / 3;
        int colMiniTablero = this.getPosicion() % 3;
        int filaEnMiniTablero = posMiniTablero / 3;
        int colEnMiniTablero = posMiniTablero % 3;

        if (m[filaMiniTablero][colMiniTablero].hacerJugada(filaEnMiniTablero, colEnMiniTablero, jugador)) {
            this.setPosicion(posMiniTablero);
            return true;
        }
        return false;
    }

    public MiniTablero obtenerMiniTablero(int fila, int columna) {
        return m[fila][columna];
    }

    public void reiniciarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m[i][j] = new MiniTablero();
            }
        }
    }
    
    public int convertirPosicion(String coordenada) {
        if (coordenada.length() != 2) {
            return -1;
        }

        char fila = coordenada.charAt(0);
        char col = coordenada.charAt(1);
        int filaNum = -1;
        int colNum = -1;
        if (fila >= 'A' && fila <= 'C') {
            filaNum = fila - 'A';
        } else if (fila >= 'a' && fila <= 'c') {
            filaNum = fila - 'a';
        }
        if (col >= '1' && col <= '3') {
            colNum = col - '1';
        }
        if (filaNum == -1 || colNum == -1) {
            return -1;
        }
        return filaNum * 3 + colNum;
    }


    
    public void limpiarMinicuadro(int filaMiniTablero, int colMiniTablero) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m[filaMiniTablero][colMiniTablero].limpiarCelda(i, j);
            }
        }
    }


}
