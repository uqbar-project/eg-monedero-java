/**
 * 
 */
package ar.com.tadp.examples.presentacion.jface.simple;

import org.eclipse.swt.events.SelectionEvent;
import org.uqbar.ui.jface.AbstractSelectionListener;

import ar.com.tadp.examples.jface.base.AbstractPanel;
import ar.com.tadp.examples.monedero.exceptions.UserException;

public abstract class UserAction<T> extends AbstractSelectionListener {
	private final AbstractPanel<T> container;

	public UserAction(AbstractPanel<T> container) {
		this.container = container;

	}

	@Override
	public void widgetSelected(SelectionEvent event) {
		try {
			this.execute(this.container.getModel());
		}
		catch (UserException e) {
			this.container.mostrarAdvertencia(e.getMessage());
		}
		catch (Exception e) {
			e.printStackTrace();
			this.container.mostrarError("Ocurrió un error al realizar la transacción. Consulte al Administrador del Sistema");
		}
	}

	protected abstract void execute(T t);


}