package es.ulpgc.eite.da.templatedemo.registerTask;

import java.lang.ref.WeakReference;

public interface registerTaskContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(registerTaskViewModel viewModel);
        void navigateToStatus();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        void onRegisterBtnClicked(String project, String taskName, String responsible);
    }

    interface Model {
        boolean registerNewTask(String project, String taskName, String responsible);
    }
}