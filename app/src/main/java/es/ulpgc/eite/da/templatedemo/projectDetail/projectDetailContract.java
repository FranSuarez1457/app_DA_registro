package es.ulpgc.eite.da.templatedemo.projectDetail;

import java.lang.ref.WeakReference;
import java.util.List;

import es.ulpgc.eite.da.templatedemo.database.ProjectEntity;

public interface projectDetailContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(projectDetailViewModel viewModel);
        void showFavoriteAddedMessage();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        void onFavoriteButtonClicked();
    }

    interface Model {
        boolean addToFavorites();
        ProjectEntity getProjectByName(String name);
        List<String> getTaskNamesByProjectId(int projectId);
    }
}