package ar.edu.design.examples.monedero.domain;
import java.math.BigDecimal;

import org.uqbar.commons.model.ObservableObject;

import ar.edu.design.examples.monedero.exceptions.BusinessException;

public class Monedero extends ObservableObject {
	
	public static final String MONTO = "monto";
	private BigDecimal monto;
	
	public Monedero() {
		this.monto = new BigDecimal(0);
	}
	
	public Monedero(double montoInicial) {
		this.monto = new BigDecimal(montoInicial);
	}
 
	public void sumarMonto(BigDecimal cuanto) {
		BigDecimal oldMonto = this.monto;
		this.monto = this.monto.add(cuanto);
		this.firePropertyChange(Monedero.MONTO, oldMonto, this.monto);		
	}

	public void poner(BigDecimal cuanto) throws BusinessException {
		this.validarMonto(cuanto);
		this.sumarMonto(cuanto);
	}

	public void sacar(BigDecimal cuanto) throws BusinessException {
		this.validarMonto(cuanto);
		if (this.monto.subtract(cuanto).doubleValue() < 0) {
			throw new BusinessException("No puede sacar m�s de " + this.monto + " $");
		}
		this.sumarMonto(cuanto.negate());
	}

	private void validarMonto(BigDecimal cuanto) throws BusinessException {
		if (cuanto.doubleValue() <= 0) {
			throw new BusinessException(cuanto + ": el monto a ingresar debe ser un valor positivo");
		}
	}

	public BigDecimal getMonto() {
		return this.monto;
	}
	
}
