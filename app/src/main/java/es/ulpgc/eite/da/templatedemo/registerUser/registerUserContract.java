package es.ulpgc.eite.da.templatedemo.registerUser;

import java.lang.ref.WeakReference;

public interface registerUserContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(registerUserViewModel viewModel);
        void finishView(); // Para volver al Login al terminar
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        void onRegisterButtonClicked(String email, String password, String name);
    }

    interface Model {
        boolean registerNewUser(String email, String password, String name);
    }
}
