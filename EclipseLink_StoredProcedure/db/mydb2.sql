create database mydb;
use mydb;

DELIMITER $$
create procedure listadoMayores30()
begin
select * from tb_empleado where ((year(current_date()) - (year(fechaNacimiento)))>= 30);
end $$
DELIMITER ;


DELIMITER $$
create procedure actualizaPorDepartamento(depa varchar(100))
begin
if depa = 'Lima' then 
	update tb_empleado set EMP_SALARIO = 1000.00 + EMP_SALARIO where DEP_ID = (select DEP_ID from tb_departamento where DEP_NOMBRE = 'Lima');
else 
	update tb_empleado set EMP_SALARIO = 500.00 + EMP_SALARIO where DEP_ID = (select DEP_ID from tb_departamento where DEP_NOMBRE = 'Trujillo');
end if;
select * from tb_empleado e join tb_departamento d on e.DEP_ID = d.DEP_ID and DEP_NOMBRE = depa;
end $$
DELIMITER ;

DELIMITER $$
create procedure prueba()
begin
SELECT * FROM tb_empleado;
end $$
DELIMITER ;

CALL actualizaPorDepartamento ('truJillo');

call listadoMayores30();

