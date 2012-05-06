package ar.edu.design.examples.monedero.ui.jface.simple;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.uqbar.ui.jface.AbstractSelectionListener;

import ar.com.tadp.examples.presentacion.jface.simple.AbstractPanel;
import ar.edu.design.examples.monedero.domain.Monedero;
import ar.edu.design.examples.monedero.exceptions.BusinessException;
import ar.edu.design.examples.monedero.modelo.MonederoModel;

public class MonederoPanel extends AbstractPanel<MonederoModel> {
	public MonederoPanel(Composite parent) {
		super(parent, new MonederoModel());
	}

	@Override
	protected void armarFormulario(Composite campos) {
		this.addText(campos, "Monto a ingresar", MonederoModel.MONTO_A_INGRESAR);
		Text text = this.addText(this.getModel().getMonedero(), campos, "$ actual", Monedero.MONTO);
		text.setEditable(false);
	}

	@Override
	protected void agregarBotones(Composite botonesStd) {
		this.addBoton(botonesStd, "Sacar plata", new AbstractSelectionListener() {
			@Override
					public void widgetSelected(SelectionEvent event) {
						try {
							MonederoPanel.this.getModel().sacar();
						} catch (BusinessException e) {
							mostrarAdvertencia(e.getMessage());
						} catch (Exception e) {
							e.printStackTrace();
							mostrarError("Ocurri� un error al sacar la plata. Consulte al Administrador del Sistema");
						}
					}
		});

		this.addBoton(botonesStd, "Poner plata", new AbstractSelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				try {
					MonederoPanel.this.getModel().poner();
				} catch (BusinessException e) {
					// ojo, no se puede usar this porque estamos dentro de una
					// clase an�nima
					mostrarAdvertencia(e.getMessage());
				} catch (Exception e) {
					e.printStackTrace();
					mostrarError("Ocurri� un error al depositar la plata. Consulte al Administrador del Sistema");
				}
			}

		});

	}

}
