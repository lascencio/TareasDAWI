package pe.edu.cibertec.jpa.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import pe.edu.cibertec.jpa.domain.Departamento;
import pe.edu.cibertec.jpa.domain.Empleado;

public class JPATests {

	private EntityManager entityManager;

	public JPATests(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("MyPersistence");
		EntityManager entityManager = factory.createEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		JPATests test = new JPATests(entityManager);
		tx.begin();
		try {
			// Sin storedProcedure
			test.crearEmpleadosCascada();
//			// Con storeProcedure
			test.actualizarSueldoSegunDepartamento("Trujillo");
		} catch (Exception e) {
			e.printStackTrace();
		}

		tx.commit();
		//LISTAS
		test.listarEmpleados();
		test.listarEmpleadosPorDepa("Trujillo");
		test.listarEmpleadosMayoresDe30();
		System.out.println("..done");

	}

	private void listarEmpleados() {
		Query q = entityManager.createNamedQuery("empleados");
		List<Empleado> lista = q.getResultList();
		System.err.println("-------------------------------------TODOS LOS EMPLEADOS-------------------------------------");
		for(Empleado e : lista){
			System.out.println(e.toString());
		}
	}

	private void actualizarSueldoSegunDepartamento(String departamento) {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("actualizaPorDepartamento", Empleado.class);
		query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);
		query.setParameter(1, departamento);
		query.execute();
		System.err.println("-------------------------------------EMPLEADOS DE :"+departamento +" ACTUALIZADOS-------------------------------------");
		List<Empleado> lista = query.getResultList();
		for(Empleado e : lista){
			System.out.println(e.toString());
		}
	}

	private void listarEmpleadosMayoresDe30() {
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery("listadoMayores30",Empleado.class);
		List<Empleado> lista = query.getResultList();
		System.err.println("-------------------------------------EMPLEADOS MAYPRES DE 30 AÑOS-------------------------------------");
		for(Empleado e : lista){
			System.out.println(e.toString());
		}
	}

	private void listarEmpleadosPorDepa(String nombre) {
		Query q = entityManager.createNamedQuery("empleadosPorDepa");
		q.setParameter("nombre", nombre);
		List<Empleado> lista = q.getResultList();
		System.err.println("-------------------------------------EMPLEADOS POR DEPARTAMENTO: "+ nombre +"-------------------------------------");
		for(Empleado e : lista){
			System.out.println(e.toString());
		}
	}

	public void crearEmpleadosCascada() throws ParseException {
		Departamento lima = new Departamento("Lima");
		Departamento trujillo = new Departamento("Trujillo");

		SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-YYYY");
		Date d1 = myFormat.parse("28-05-1995");
		Date d2 = myFormat.parse("29-27-1992");
		Date d3 = myFormat.parse("28-05-1984");
		Date d4 = myFormat.parse("28-05-1985");
		Date d5 = myFormat.parse("29-27-1979");
		Date d6 = myFormat.parse("28-05-1990");
		
		Empleado emp1 = new Empleado("David", "Ascencio", "71312091", 1200.00, d1, lima);
		Empleado emp2 = new Empleado("Mauricio", "Chavez", "74857498", 1500.00, d2, lima);
		Empleado emp3 = new Empleado("Joantan", "Galvez", "01874958", 900.00, d3, lima);
		
		Empleado emp4 = new Empleado("Diana", "Castillo", "79312091", 800.00, d4, trujillo);
		Empleado emp5 = new Empleado("Luis", "Mariano", "76857498", 4500.00, d5, trujillo);
		Empleado emp6 = new Empleado("Irma", "Sanchez", "08874958", 3000.00, d6, trujillo);
		
		List<Empleado> emps1 = Arrays.asList(emp1, emp2, emp3);
		List<Empleado> emps2 = Arrays.asList(emp6, emp4, emp5);
		
		lima.setEmpleados(emps1);
		trujillo.setEmpleados(emps2);
		
		entityManager.persist(lima);
		entityManager.persist(trujillo);
		
	}

}
