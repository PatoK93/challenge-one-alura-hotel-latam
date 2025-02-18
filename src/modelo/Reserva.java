package modelo;

public class Reserva {

    private Integer id;

	private String fechaEntrada;
    
    private String fechaSalida;
    
    private Integer valor;
    
    private String formaPago;
    
    private Integer idHuesped;
    

    public Reserva(String fechaEntrada, String fechaSalida, Integer valor, String formaPago, Integer idHuesped) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
        this.idHuesped = idHuesped;
    }
    
    public Reserva(String fechaEntrada, String fechaSalida, Integer valor, String formaPago) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }
    
    public Reserva (String fechaEntrada, String fechaSalida, Integer valor, String formaPago, String nombre, String apellido) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
    }

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
		this.id = id;
	}

    public String getFechaEntrada() {
		return this.fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getFechaSalida() {
		return this.fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Integer getValor() {
		return this.valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return this.formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}

	public Integer getIdHuesped() {
		return this.idHuesped;
	}

	public void setIdHuesped(Integer idHuesped) {
		this.idHuesped = idHuesped;
	}


	@Override
    public String toString() {
        return String.format(
                "{ id: %d, fechaEntrada: %s, fechaSalida: %s, valor: %d, formaPago: %s, idHuesped: %d }",
                this.id, this.fechaEntrada, this.fechaSalida, this.valor, this.formaPago, this.idHuesped);
    }
	
}
