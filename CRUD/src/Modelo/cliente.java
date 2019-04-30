package Modelo;

public class cliente {
    private int id;
    private String Rut;
    private String RazonSocial;
    private String Giro;
    private String Direccion;
    private String Comuna;
    private String Correo;
    private int telfono;

    public cliente (){
       super(); 
    }
    
    public cliente(String Rut, String RazonSocial, String Giro, String Direccion, String Comuna, String Correo, int telfono) {
        this.Rut = Rut;
        this.RazonSocial = RazonSocial;
        this.Giro = Giro;
        this.Direccion = Direccion;
        this.Comuna = Comuna;
        this.Correo = Correo;
        this.telfono = telfono;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRut() {
        return Rut;
    }

    public void setRut(String Rut) {
        this.Rut = Rut;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial = RazonSocial;
    }

    public String getGiro() {
        return Giro;
    }

    public void setGiro(String Giro) {
        this.Giro = Giro;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getComuna() {
        return Comuna;
    }

    public void setComuna(String Comuna) {
        this.Comuna = Comuna;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }

    public int getTelfono() {
        return telfono;
    }

    public void setTelfono(int telfono) {
        this.telfono = telfono;
    }

    @Override
    public String toString() {
        return "cliente{" + "id=" + id + ", Rut=" + Rut + ", RazonSocial=" + RazonSocial + ", Giro=" + Giro + ", Direccion=" + Direccion + ", Comuna=" + Comuna + ", Correo=" + Correo + ", telfono=" + telfono + '}';
    }

}
