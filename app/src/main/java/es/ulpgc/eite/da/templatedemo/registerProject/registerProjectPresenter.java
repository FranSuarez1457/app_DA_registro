package es.ulpgc.eite.da.templatedemo.registerProject;

import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;
import es.ulpgc.eite.da.templatedemo.status.statusState; // Importamos el estado del Status

public class registerProjectPresenter implements registerProjectContract.Presenter {

    private WeakReference<registerProjectContract.View> view;
    private registerProjectState state;
    private registerProjectContract.Model model;

    public registerProjectPresenter(registerProjectState state) {
        this.state = state;
    }

    @Override
    public void injectView(WeakReference<registerProjectContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(registerProjectContract.Model model) {
        this.model = model;
    }

    @Override
    public void onResume() {
        view.get().displayData(state);
    }

    @Override
    public void onRegisterBtnClicked(String name, String description, String date) {
        // 1. Intentamos guardar el proyecto
        boolean isSuccess = model.registerNewProject(name, description, date);

        // 2. Preparamos el "paquete" de estado para la pantalla Status
        statusState newStatus = new statusState();
        newStatus.isSuccess = isSuccess;

        if (isSuccess) {
            newStatus.message = "El proyecto '" + name + "' se ha creado correctamente. Contacte con el administrador si necesita asignar personal.";
        } else {
            newStatus.message = "Error: El nombre del proyecto no puede estar vacío. Por favor, inténtelo de nuevo.";
        }

        // 3. Se lo guardamos al Mediador
        AppMediator.getInstance().setStatusState(newStatus);

        // 4. Damos la orden de saltar de pantalla
        view.get().navigateToStatus();
    }
}
