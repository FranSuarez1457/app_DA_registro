package es.ulpgc.eite.da.templatedemo.projectList;

import java.lang.ref.WeakReference;
import java.util.List;
import es.ulpgc.eite.da.templatedemo.database.ProjectEntity;

public interface projectListContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(projectListState state); // O projectListViewModel si usas ViewModel
        void navigateToProjectDetail();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        void onProjectClicked(String projectName);
    }

    interface Model {
        List<ProjectEntity> getProjectList();
        // --- LA NUEVA ORDEN PARA EL FILTRO ---
        List<ProjectEntity> getFavoriteProjects();
    }
}
