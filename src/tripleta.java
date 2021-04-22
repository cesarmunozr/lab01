public class tripleta implements Comparable {
    private String categoria;
    private String nombreProducto;
    private int conteo;

    public tripleta(){
        categoria = "";
        nombreProducto = "";
        conteo = 0;
    }

    public tripleta(String categoria, String nombreProducto){
        this.categoria = categoria;
        this.nombreProducto = nombreProducto;
        conteo = 1;
    }

    public void setCategoria (String categoria){
        this.categoria = categoria;
    }

    public String getCategoria(){
        return categoria;
    }

    public void setNombreProducto(String nombreProducto){
        this.nombreProducto = nombreProducto;
    }

    public String getNombreProducto(){
        return nombreProducto;
    }

    public void incrementarConteo(){
        conteo++;
    }

    public int getConteo(){
        return conteo;
    }

    @Override
    public int compareTo(Object t2){
        int t2count = ((tripleta)t2).getConteo();
        return t2count - this.getConteo();
    }
}
