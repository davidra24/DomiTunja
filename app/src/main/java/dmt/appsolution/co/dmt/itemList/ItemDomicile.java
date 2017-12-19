package dmt.appsolution.co.dmt.itemList;

public class ItemDomicile {
    private int imagen;
    private String titulo;
    private String descripcion;
    private int rating;
    private int masInfo;

    public ItemDomicile(int imagen, String titulo, String descripcion, int rating, int masInfo){
        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.rating = rating;
        this.masInfo = masInfo;
    }
    public int getImagen(){
        return this.imagen;
    }
    public String getTitulo(){
        return this.titulo;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public int getRating(){return this.rating;}
    public int getMasInfo(){
        return this.masInfo;
    }
}
