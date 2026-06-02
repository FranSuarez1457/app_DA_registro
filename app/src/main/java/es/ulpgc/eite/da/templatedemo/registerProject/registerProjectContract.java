package es.ulpgc.eite.da.templatedemo.registerProject;

import java.lang.ref.WeakReference;

public interface registerProjectContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(registerProjectViewModel viewModel);
        void finishView(); // Para cerrar la pantalla cuando termine
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        // Recibe los textos de la Activity
        void onSaveButtonClicked(String projectName, String projectDescription);
    }

    interface Model {
        // Devuelve true si se guarda bien
        boolean saveProject(String name, String description);
    }
}