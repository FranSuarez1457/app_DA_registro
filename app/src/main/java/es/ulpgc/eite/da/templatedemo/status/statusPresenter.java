package es.ulpgc.eite.da.templatedemo.status;

import java.lang.ref.WeakReference;

public class statusPresenter implements statusContract.Presenter {

    private WeakReference<statusContract.View> view;
    private statusState state;
    private statusContract.Model model;

    public statusPresenter(statusState state) {
        this.state = state;
    }

    @Override
    public void injectView(WeakReference<statusContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(statusContract.Model model) {
        this.model = model;
    }

    @Override
    public void onResume() {
        view.get().displayData(state);
    }

    @Override
    public void onAcceptButtonClicked() {
        view.get().navigateToHome();
    }
}