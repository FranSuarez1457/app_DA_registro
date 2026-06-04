package es.ulpgc.eite.da.templatedemo.registerProject;

import java.lang.ref.WeakReference;

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
    public void onSaveButtonClicked(String projectName, String projectDescription) {
        boolean success = model.saveProject(projectName, projectDescription);

        if (success) {
            view.get().finishView();
        } else {
            view.get().showErrorMessage("Error: Sesión expirada o datos inválidos");
        }
    }
}