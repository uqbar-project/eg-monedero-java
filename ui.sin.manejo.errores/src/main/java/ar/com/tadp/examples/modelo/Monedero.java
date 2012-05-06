package ar.com.tadp.examples.modelo;

import java.math.BigDecimal;

import org.uqbar.commons.model.ObservableObject;

import ar.com.tadp.examples.monedero.domain.Cuenta;

public class Monedero extends ObservableObject {
	public static final String MONTO_A_INGRESAR = "montoAIngresar";

	private Cuenta monedero;
	private String montoAIngresar;

	// ********************************************************
	// ** Constructor & initialization
	// ********************************************************

	public Monedero() {
		this.monedero = new Cuenta(15.0);
		this.limpiar();
	}

	protected void limpiar() {
		this.setMontoAIngresar("");
	}

	// ********************************************************
	// ** Actions
	// ********************************************************

	public void sacar() {
		this.monedero.sacar(this.getMontoAsNumber());
		this.limpiar();
	}

	public void poner() {
		this.monedero.poner(this.getMontoAsNumber());
		this.limpiar();
	}

	// ********************************************************
	// ** Accessors
	// ********************************************************

	public Cuenta getMonedero() {
		return this.monedero;
	}

	public String getMontoAIngresar() {
		return this.montoAIngresar;
	}

	public void setMontoAIngresar(String montoAIngresar) {
		String oldMontoAIngresar = this.montoAIngresar;
		this.montoAIngresar = montoAIngresar;
		this.firePropertyChange(MONTO_A_INGRESAR, oldMontoAIngresar, this.montoAIngresar);
	}

	// ********************************************************
	// ** Internal
	// ********************************************************

	protected BigDecimal getMontoAsNumber() {
		return new BigDecimal(this.montoAIngresar);
	}
}