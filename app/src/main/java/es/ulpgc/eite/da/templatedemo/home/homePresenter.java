package es.ulpgc.eite.da.templatedemo.home;

import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class homePresenter implements homeContract.Presenter {

    private WeakReference<homeContract.View> view;
    private homeState state;
    private homeContract.Model model;

    public homePresenter(homeState state) {
        this.state = state;
    }

    @Override
    public void injectView(WeakReference<homeContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(homeContract.Model model) {
        this.model = model;
    }

    @Override
    public void onResume() {
        view.get().displayData(state);
    }

    @Override
    public void onRegisterProjectBtnClicked() {
        if (model.isGuestUser()) {
            view.get().showGuestMessage();
        } else {
            view.get().navigateToRegisterProject();
        }
    }

    @Override
    public void onRegisterTaskBtnClicked() {
        if (model.isGuestUser()) {
            view.get().showGuestMessage();
        } else {
            view.get().navigateToRegisterTask();
        }
    }

    @Override
    public void onProjectListBtnClicked() {
        // Apagamos el filtro para ver todos los proyectos
        AppMediator.getInstance().isFavoriteFilterActive = false;
        view.get().navigateToProjectList();
    }

    @Override
    public void onFavoritesBtnClicked() {
        // Encendemos el filtro para ver solo favoritos
        AppMediator.getInstance().isFavoriteFilterActive = true;

        // ¡OJO! Viajamos a la MISMA pantalla de proyectos, reciclando la vista
        view.get().navigateToProjectList();
    }
}