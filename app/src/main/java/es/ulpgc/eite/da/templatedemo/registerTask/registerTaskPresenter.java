package es.ulpgc.eite.da.templatedemo.registerTask;

import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;
import es.ulpgc.eite.da.templatedemo.status.statusState;

public class registerTaskPresenter implements registerTaskContract.Presenter {

    private WeakReference<registerTaskContract.View> view;
    private registerTaskState state;
    private registerTaskContract.Model model;

    public registerTaskPresenter(registerTaskState state) {
        this.state = state;
    }

    @Override
    public void injectView(WeakReference<registerTaskContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(registerTaskContract.Model model) {
        this.model = model;
    }

    @Override
    public void onResume() {
        view.get().displayData(state);
    }

    @Override
    public void onRegisterBtnClicked(String project, String taskName, String responsible) {
        // 1. Verificamos si la tarea es válida
        boolean isSuccess = model.registerNewTask(project, taskName, responsible);

        // 2. Preparamos el mensaje para la pantalla Status
        statusState newStatus = new statusState();
        newStatus.isSuccess = isSuccess;

        if (isSuccess) {
            newStatus.message = "La tarea '" + taskName + "' ha sido asignada correctamente a " + responsible + ".";
        } else {
            newStatus.message = "Error: Es obligatorio indicar un nombre para la tarea.";
        }

        // 3. Guardamos el estado en el Mediador
        AppMediator.getInstance().setStatusState(newStatus);

        // 4. Viajamos a la pantalla de estado
        view.get().navigateToStatus();
    }
}
