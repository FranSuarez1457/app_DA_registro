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
        // Ejecutamos el registro en la base de datos
        boolean success = model.registerNewUser(email, password);

        if (success) {
            // Si todo ha ido bien, cerramos la pantalla de registro para volver al Login
            view.get().finishView();
        } else {
            // Aquí en un futuro se podría añadir un mensaje de error si los campos están vacíos
        }
    }
}