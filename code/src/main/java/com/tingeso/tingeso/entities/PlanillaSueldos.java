package com.tingeso.tingeso.entities;

import lombok.AllArgsConstructor;
import lombok.ToString;

import lombok.Getter;
import lombok.Setter;

@ToString
@AllArgsConstructor
public class PlanillaSueldos {
  private @Getter @Setter String rut_empleado;
  private @Getter @Setter String nombre_empleado;
  private @Getter @Setter String apellido_empleado;
  private @Getter @Setter String categoria;
  private @Getter @Setter Integer anios_servicio;
  private @Getter @Setter double sueldo_fijo;
  private @Getter @Setter double bonificacion;
  private @Getter @Setter double pago_horas_extras;
  private @Getter @Setter double descuento;
  private @Getter @Setter double sueldo_bruto;
  private @Getter @Setter double cotizacion_previsional;
  private @Getter @Setter double cotizacion_salud;
  private @Getter @Setter double sueldo_final;
}
