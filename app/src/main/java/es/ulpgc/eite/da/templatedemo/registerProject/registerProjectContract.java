package es.ulpgc.eite.da.templatedemo.registerProject;

import java.lang.ref.WeakReference;

public interface registerProjectContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(registerProjectViewModel viewModel);
        void finishView();

        void showErrorMessage(String s);
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        void onSaveButtonClicked(String projectName, String projectDescription);
    }

    interface Model {
        boolean saveProject(String name, String description);
    }
}