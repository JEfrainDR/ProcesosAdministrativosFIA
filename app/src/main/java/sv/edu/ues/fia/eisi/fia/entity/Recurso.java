package sv.edu.ues.fia.eisi.fia.entity;

public class Recurso {
    private String nombre;
    private String tamaño;
    private String url;
    private int icon;

    public Recurso(String nombre, String tamaño, String url, int icon) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.url = url;
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTamaño() {
        return tamaño;
    }

    public void setTamaño(String tamaño) {
        this.tamaño = tamaño;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
