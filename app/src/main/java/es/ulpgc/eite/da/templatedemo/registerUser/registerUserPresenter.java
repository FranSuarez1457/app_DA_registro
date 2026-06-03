package es.ulpgc.eite.da.templatedemo.registerUser;

import java.lang.ref.WeakReference;

public class registerUserPresenter implements registerUserContract.Presenter {

    private WeakReference<registerUserContract.View> view;
    private registerUserState state;
    private registerUserContract.Model model;

    public registerUserPresenter(registerUserState state) {
        this.state = state;
    }

    @Override
    public void injectView(WeakReference<registerUserContract.View> view) {
        this.view = view;
    }

    @Override
    public void injectModel(registerUserContract.Model model) {
        this.model = model;
    }

    @Override
    public void onResume() {
        view.get().displayData(state);
    }

    @Override
    public void onRegisterButtonClicked(String email, String password) {
        boolean success = model.registerNewUser(email, password);

        if (success) {
            view.get().finishView();
        } else {
            view.get().showErrorMessage();
        }
    }
}