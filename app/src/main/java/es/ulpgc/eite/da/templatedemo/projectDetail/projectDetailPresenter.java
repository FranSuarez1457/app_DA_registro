package es.ulpgc.eite.da.templatedemo.projectDetail;

import java.lang.ref.WeakReference;

public class projectDetailPresenter implements projectDetailContract.Presenter {

    private WeakReference<projectDetailContract.View> view;
    private projectDetailState state;
    private projectDetailContract.Model model;

    public projectDetailPresenter(projectDetailState state) {
        this.state = state;
    }

    @Override
    public void injectView(WeakReference<projectDetailContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(projectDetailContract.Model model) {
        this.model = model;
    }

    @Override
    public void onResume() {
        // Obtenemos los datos simulados y los pasamos al estado
        projectDetailState loadedState = model.getSimulatedProject();
        state.projectName = loadedState.projectName;
        state.projectDate = loadedState.projectDate;
        state.projectDescription = loadedState.projectDescription;
        state.isFavorite = loadedState.isFavorite;

        view.get().displayData(state);
    }

    @Override
    public void onFavoriteBtnClicked() {
        // Efecto interruptor: cambiamos de true a false, o de false a true
        state.isFavorite = !state.isFavorite;

        // Le decimos a la vista que cambie la imagen de la estrella
        view.get().updateFavoriteIcon(state.isFavorite);
    }
}