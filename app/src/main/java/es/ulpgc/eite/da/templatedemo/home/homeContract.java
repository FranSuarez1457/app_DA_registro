package es.ulpgc.eite.da.templatedemo.home;

import java.lang.ref.WeakReference;

public interface homeContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(homeViewModel viewModel);
        void navigateToRegisterProject();
        void navigateToRegisterTask();
        void navigateToProjectList();
        void showGuestMessage();
        void showFavoritesMessage(); // <-- AÑADE ESTA LÍNEA
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        void onRegisterProjectBtnClicked();
        void onRegisterTaskBtnClicked();
        void onProjectListBtnClicked();
        void onFavoritesBtnClicked(); // <-- AÑADE ESTA LÍNEA
    }

    interface Model {
        boolean isGuestUser();
    }
}