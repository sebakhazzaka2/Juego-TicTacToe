package dominio;

public class Jugador {
    
    private String nombre;
    private int edad;
    private String alias;
    private String color;
    private int partidasGanadas;
    

    public Jugador(){

    }
    
       public Jugador(String unNombre, int unaEdad, String unAlias){
        this.setNombre(unNombre);
        this.setEdad(unaEdad);
        this.setAlias(unAlias);
        
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
    
    public void setColor(String unColor){
        this.color = unColor;
    }
    
    public String getColor(){
        return color;
    }
  
    
    public void incrementarPartidasGanadas() {
        partidasGanadas++;
    }
    
    public int getPartidasGanadas() {
        return partidasGanadas;
    }
    
    @Override
    public String toString(){
        return this.alias;
    }

    int getCantidadPartidasJugadas() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setCantidadPartidasJugadas(int i) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}




