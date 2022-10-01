package com.tingeso.tingeso;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tingeso.tingeso.entities.EmployeeEntity;
import com.tingeso.tingeso.repostories.EmployeeRepository;
import com.tingeso.tingeso.services.OfficeRRHH;
import com.tingeso.tingeso.services.ReadilyService;

@SpringBootTest
class OfficeRRHHTests {
  @Autowired
  EmployeeRepository employeeRepository;
	@Autowired
  OfficeRRHH officeRRHH;
  @Test
  void testSueldoBase(){
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("12345678-9");
    employee.setCategory("A");
    double sueldo_base = officeRRHH.get_sueldo_base(employee.getCategory());
    assertEquals(1700000, sueldo_base, 0.0);
  }
  @Test
  void testValorHorasExtra(){
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("12345678-9");
    employee.setCategory("A");
    int horas_extra = 20;
    double valor_horas_extra = officeRRHH.valor_horas_extra(employee.getCategory(), horas_extra);
    assertEquals(500000, valor_horas_extra, 0.0);
  }
  @Test
  void testAniosServicio() throws ParseException{
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("12345678-9");
    employee.setCategory("A");
    employee.setEntry_date("2019/01/01");
    int anios_servicio = officeRRHH.calcular_anios_servicio(employee.getEntry_date());
    assertEquals(3, anios_servicio, 0.0);
  }
  @Test
  void testBonificaciones() throws ParseException{
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("24541761-9");
    employee.setCategory("A");
    employee.setEntry_date("2000/01/01");
    double sueldo_base = officeRRHH.get_sueldo_base(employee.getCategory());
    int anios_servicio = officeRRHH.calcular_anios_servicio(employee.getEntry_date());
    double bonificaciones = officeRRHH.calcular_bonificaciones(anios_servicio,sueldo_base);
    assertEquals(238000, bonificaciones, 0.0);
  }
  @Test
  void testDescuentosTarde(){
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("24541761-9");
    employee.setCategory("A");
    double sueldo_base = officeRRHH.get_sueldo_base(employee.getCategory());
    int minutos_tarde = 30;
    double descuentos_tarde = officeRRHH.descuentos_tardanza(minutos_tarde, sueldo_base);
    assertEquals(51000, descuentos_tarde, 0.0);
  }
  @Test
  void cotizacionSalud(){
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("24541761-9");
    employee.setCategory("A");
    double sueldo_base = officeRRHH.get_sueldo_base(employee.getCategory());
    double cotizacion_salud = officeRRHH.calcular_cotizacion_salud(sueldo_base);
    assertEquals(136000, cotizacion_salud, 0.0);
  }
  @Test
  void cotizacionPrevisional(){
    EmployeeEntity employee = new EmployeeEntity();
    employee.setRut("24541761-9");
    employee.setCategory("A");
    double sueldo_base = officeRRHH.get_sueldo_base(employee.getCategory());
    double cotizacion_previsional = officeRRHH.calcular_cotizacion_previsional(sueldo_base);
    assertEquals(170000, cotizacion_previsional, 0.0);
  }
  // @Test
  // void calcular_sueldo() throws ParseException{
  //   double sueldo = officeRRHH.calcular_sueldo_bruto(employeeRepository.findOne().getRut());
  //   assertEquals(204000, sueldo, 0.0);
  // }
  @Test
  void testSueldoFinal() throws ParseException{
    double sueldo_bruto = 3000000;
    double sueldo_final = officeRRHH.calcular_sueldo_final(sueldo_bruto);
    assertEquals(2460000, sueldo_final, 0.0);
  }
}
@SpringBootTest
class ReadilyServiceTests {
  @Autowired
  ReadilyService readilyService;
  
  @Test
  void getLateMinutesTest() throws ParseException{
    String entry_time = "09:40";
    int lateMinutes = readilyService.getLateMinutes(entry_time);
    assertEquals(100, lateMinutes);
  }
  @Test
  void extraHoursTest() throws ParseException, FileNotFoundException{
    boolean success = readilyService.readFile(1);
    assertEquals(true, success);
  }
}