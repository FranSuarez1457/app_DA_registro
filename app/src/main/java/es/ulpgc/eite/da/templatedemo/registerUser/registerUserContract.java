package es.ulpgc.eite.da.templatedemo.registerUser;

import java.lang.ref.WeakReference;

public interface registerUserContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(registerUserViewModel viewModel);
        void finishView();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        // Solo recibe dos parámetros ahora
        void onRegisterButtonClicked(String email, String password);
    }

    interface Model {
        // En el futuro, aquí se guardará en base de datos
    }
}
