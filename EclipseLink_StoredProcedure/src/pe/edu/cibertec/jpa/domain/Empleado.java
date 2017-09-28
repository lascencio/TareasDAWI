package pe.edu.cibertec.jpa.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.eclipse.persistence.annotations.NamedStoredFunctionQuery;


@Entity
@Table(name = "TB_EMPLEADO")
@NamedQueries({
	@NamedQuery(name="empleados", query="select e from Empleado e"),
	@NamedQuery(name="empleadosPorDepa", query="select e from Empleado e where e.departamento.nombre = :nombre"),
	
})
public class Empleado {
	@Id
	@Column(name = "EM_ID")
	@GeneratedValue
	private Long id;

	@Column(name = "EM_NOMBRE", length = 100)
	private String nombre;

	@Column(name = "EM_APELLIDO", length = 100)
	private String apellido;

	@Column(name = "EM_DNI", columnDefinition = "CHAR(8)", unique=true)
	private String dni;

	@Column(name = "EMP_SALARIO", precision = 8, scale = 2)
	private double salario;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "")
	private Date fechaNacimiento;

	@ManyToOne
	@JoinColumn(name = "DEP_ID")
	private Departamento departamento;

	public Empleado() {
		super();
	}

	public Empleado(String nombre, String apellido, String dni, double salario, Date fechaNacimiento,
			Departamento departamento) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.salario = salario;
		this.fechaNacimiento = fechaNacimiento;
		this.departamento = departamento;
	}


	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	public String fechaCorrecta(Date fecha){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYY");
		String fechita = sdf.format(fecha);
		return fechita;
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IdEmpleado : " + id).append(", Nombre : " + nombre).append(", Apellido : " + apellido).append(", DNI : " + dni);
		builder.append(", Fecha Nacimiento : " + fechaCorrecta(fechaNacimiento)).append(", Departamento : ");
		builder.append(this.departamento.getNombre()).append(", Sueldo : ").append(salario);
		return builder.toString();
	}

}
