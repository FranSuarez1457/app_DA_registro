package es.ulpgc.eite.da.templatedemo.projectList;

import java.lang.ref.WeakReference;
import java.util.List;

public interface projectListContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(projectListViewModel viewModel);
        void navigateToProjectDetail(); // Para saltar al detalle al tocar un proyecto
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        void onProjectClicked(String projectName); // Detecta cuando se toca un proyecto
    }

    interface Model {
        // En el futuro leerá de la base de datos Room
        List<String> getSimulatedProjects();
    }
}
