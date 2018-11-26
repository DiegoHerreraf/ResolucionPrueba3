
package shop.dao;

public class Usuario {
    
    private int codigoUsuario;
    private String apellidoUsuario;
    private String nombreUusuario;
    private String ciudad;
    private String perfil;
    private String correo;
    private String claveUsuario;

    public Usuario(int codigoUsuario, String apellidoUsuario, String nombreUusuario, String ciudad, String perfil, String correo, String claveUsuario) {
        this.codigoUsuario = codigoUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.nombreUusuario = nombreUusuario;
        this.ciudad = ciudad;
        this.perfil = perfil;
        this.correo = correo;
        this.claveUsuario = claveUsuario;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public String getNombreUusuario() {
        return nombreUusuario;
    }

    public void setNombreUusuario(String nombreUusuario) {
        this.nombreUusuario = nombreUusuario;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClaveUsuario() {
        return claveUsuario;
    }

    public void setClaveUsuario(String claveUsuario) {
        this.claveUsuario = claveUsuario;
    }
    
    
}
