package es.ulpgc.eite.da.templatedemo.status;

import java.lang.ref.WeakReference;

public interface statusContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(statusViewModel viewModel);
        void navigateToHome();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        void onAcceptButtonClicked();
    }

    interface Model {
    }
}