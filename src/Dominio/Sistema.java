package dominio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */

public class Sistema {
    private ArrayList<Jugador> listaJugadores;
    
    public Sistema(){
        this.listaJugadores = new ArrayList<>();
    }
    
    public ArrayList<Jugador> getListaJugadores(){
        return this.listaJugadores;
    }
    
    
    public void agregarJugador(Jugador unJugador){
        this.getListaJugadores().add(unJugador);
    }
    
    
    public void mostrarRanking() {
        Collections.sort(listaJugadores, Comparator.comparingInt(Jugador::getPartidasGanadas).reversed());
        System.out.println("Ranking:");
        for (Jugador jugador : listaJugadores) {
            System.out.print(jugador.getAlias() + " - " );
            for (int i = 0; i < jugador.getPartidasGanadas(); i++) {
                System.out.print("*");  
            }
            System.out.println("");
        }
    }
    
    public void registrarJugador() {
        String nombre;
        String alias = null;
        int edad = -1;
        Scanner in = new Scanner(System.in);
        System.out.println("\n##### Registrar Jugador #####");
        System.out.print("Ingrese un nombre: ");
        nombre = in.nextLine();
        System.out.print("Ingrese edad: ");
        while (edad == -1) {
            try { 
                edad = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Ingrese la edad en formato numerico: ");
                in.next();
            }
        }
        in.nextLine();
        System.out.print("Ingrese un alias: ");
        while (alias == null || alias.equals("") ) {
            alias = in.nextLine();
            for (Jugador j : this.getListaJugadores()){
                if(j.getAlias().equalsIgnoreCase(alias)){
                    alias = null;
                    System.out.print("Alias en uso, ingrese otro: ");
                    break;
                }
            }               
        }
        Jugador j = new Jugador(nombre,edad,alias);
        this.agregarJugador(j);
        System.out.println("El jugador fue registrado con exito");
    }
    
    public Jugador elegirJugador(String simboloJugador) {
        Scanner in = new Scanner(System.in);
        boolean nombreValido = false;

        while (!nombreValido) {
            System.out.println("Ingrese alias del jugador " + simboloJugador);
            String aux = in.nextLine();
            Jugador jugador = this.obtenerJugadorPorAlias(aux);

            if (jugador != null) {
                nombreValido = true;
                return jugador;
            } else {
                System.out.println("El alias ingresado no está registrado en el sistema. Por favor, verifique o regístrese como nuevo jugador.");
            }
        }
        return null;
    }
   
    
    public Jugador obtenerJugadorPorAlias(String unAlias) {
        for (Jugador jugador : this.getListaJugadores()) {
            if (jugador.getAlias().equalsIgnoreCase(unAlias)) {
                return jugador;
            }
        }
        return null;
    }
    
    public void agregarCantidadPartidas(String alias){ //suma 1 partida jugada al jugador
        ArrayList<Jugador> listaJugadores = this.getListaJugadores();
        Iterator<Jugador> it = listaJugadores.iterator();
        while(it.hasNext()){
            Jugador j = it.next();
            if(alias.equals(j.getAlias())){
                j.setCantidadPartidasJugadas(j.getCantidadPartidasJugadas() + 1);
            }
        }
    }    
}


