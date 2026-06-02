package es.ulpgc.eite.da.templatedemo.registerProject;

import java.lang.ref.WeakReference;

public interface registerProjectContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(registerProjectViewModel viewModel);
        void navigateToStatus(); // ¡La ruta hacia nuestra pantalla de feedback!
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        // Recibe los datos tecleados por el usuario
        void onRegisterBtnClicked(String name, String description, String date);
    }

    interface Model {
        // En el futuro, esto insertará los datos en la base de datos Room
        boolean registerNewProject(String name, String description, String date);
    }
}