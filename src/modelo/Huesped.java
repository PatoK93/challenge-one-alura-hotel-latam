package modelo;

public class Huesped {

    private Integer id;

    private String nombre;

    private String apellido;

    private String fechaNacimiento;
    
    private String nacionalidad;
    
    private String telefono;
    
    private String mail;
    
    private String pass;
    
    public Huesped() {}

    public Huesped(String nombre, String apellido, String fechaNacimiento, String nacionalidad, String telefono, String mail, String pass) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.mail = mail;
        this.pass = pass;
    }

    public Integer getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public String getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public String getNacionalidad() {
        return this.nacionalidad;
    }
    
    public String getTelefono() {
        return this.telefono;
    }
    
    public String getMail() {
        return this.mail;
    }
    
    public String getPass() {
        return this.pass;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    
    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return String.format(
                "{ id: %d, nombre: %s, apellido: %s, fechaNacimiento: %s, nacionalidad: %s, telefono: %s, mail: %s, pass: %s }",
                this.id, this.nombre, this.apellido, this.fechaNacimiento, this.nacionalidad, this.telefono, this.mail, this.pass);
    }
	
}
