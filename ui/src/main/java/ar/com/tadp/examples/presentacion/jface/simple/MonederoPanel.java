package ar.com.tadp.examples.presentacion.jface.simple;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

import ar.com.tadp.examples.jface.base.AbstractPanel;
import ar.com.tadp.examples.modelo.Monedero;
import ar.com.tadp.examples.monedero.domain.Cuenta;
import ar.com.tadp.examples.monedero.exceptions.UserException;

public class MonederoPanel extends AbstractPanel<Monedero> {
	public MonederoPanel(Composite parent) {
		super(parent, new Monedero());
	}

	@Override
	protected void describeForm(Composite formPanel) {
		this.addText(formPanel, "Monto a ingresar", Monedero.MONTO_A_INGRESAR);
		Text text = this.addText(this.getModel().getMonedero(), formPanel, "$ actual", Cuenta.SALDO);
		text.setEditable(false);
	}

	@Override
	protected void addActions(Composite actionsPanel) {
		this.addButton(actionsPanel, "Sacar plata", new UserAction<Monedero>(this) {
			@Override
			protected void execute(Monedero monedero) {
				try {
					monedero.sacar();
				}
				catch (UserException exception) {
					mostrarAdvertencia(exception.getMessage());
				}
				catch (Exception exception) {
					exception.printStackTrace();
					mostrarError("Ocurrió un error al sacar la plata. Consulte al Administrador del Sistema");
				}
			}
		});

		this.addButton(actionsPanel, "Poner plata", new UserAction<Monedero>(this) {
			@Override
			protected void execute(Monedero monedero) {
				try {
					monedero.poner();
				}
				catch (UserException exception) {
					mostrarAdvertencia(exception.getMessage());
				}
				catch (Exception exception) {
					exception.printStackTrace();
					mostrarError("Ocurrió un error al depositar la plata. Consulte al Administrador del Sistema");
				}
			}
		});
	}


}