package es.ulpgc.eite.da.templatedemo.home;

import java.lang.ref.WeakReference;
import es.ulpgc.eite.da.templatedemo.app.AppMediator;

public class homePresenter implements homeContract.Presenter {

    private WeakReference<homeContract.View> view;
    private homeState state;
    private homeContract.Model model;
    private AppMediator mediator;

    public homePresenter(homeState state) {
        this.state = state;
        this.mediator = AppMediator.getInstance();
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
        state.welcomeMessage = "Bienvenido, " + model.getUserName();
        view.get().displayData(state);
    }

    @Override
    public void onRegisterProjectBtnClicked() {
        view.get().navigateToRegisterProject();
    }

    @Override
    public void onRegisterTaskBtnClicked() {
        view.get().navigateToRegisterTask();
    }

    @Override
    public void onProjectListBtnClicked() {
        view.get().navigateToProjectList();
    }

    @Override
    public void onFavoritesBtnClicked() {
        view.get().navigateToFavorites();
    }
}