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
        view.get().displayData(new projectDetailViewModel());
    }

    @Override
    public void onFavoriteButtonClicked() {
        boolean isSuccess = model.addToFavorites();
        if (isSuccess) {
            view.get().showFavoriteAddedMessage();
        }
    }
}