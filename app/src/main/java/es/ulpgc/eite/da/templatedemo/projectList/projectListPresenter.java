package es.ulpgc.eite.da.templatedemo.projectList;

import java.lang.ref.WeakReference;

public class projectListPresenter implements projectListContract.Presenter {

    private WeakReference<projectListContract.View> view;
    private projectListState state;
    private projectListContract.Model model;

    public projectListPresenter(projectListState state) {
        this.state = state;
    }

    @Override
    public void injectView(WeakReference<projectListContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(projectListContract.Model model) {
        this.model = model;
    }

    @Override
    public void onResume() {
        // Pedimos los proyectos al modelo y actualizamos la vista
        state.projectList = model.getSimulatedProjects();
        view.get().displayData(state);
    }

    @Override
    public void onProjectClicked(String projectName) {
        // En el futuro, aquí le diremos al Mediador: "Oye, guarda este proyecto
        // para que la pantalla de Detalles sepa cuál enseñar".

        view.get().navigateToProjectDetail();
    }
}
