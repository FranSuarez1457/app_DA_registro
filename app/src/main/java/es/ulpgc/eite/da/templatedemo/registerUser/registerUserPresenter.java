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
    public void onRegisterButtonClicked(String email, String password, String name) {
        boolean isRegistered = model.registerNewUser(email, password, name);

        if (isRegistered) {
            // Si el registro va bien, cerramos la pantalla para volver al Login
            view.get().finishView();
        } else {
            state.errorMessage = "Error al crear el usuario";
            view.get().displayData(state);
        }
    }
}