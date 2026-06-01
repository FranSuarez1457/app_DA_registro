package es.ulpgc.eite.da.templatedemo.home;

import java.lang.ref.WeakReference;

public interface homeContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(homeViewModel viewModel);

        // Rutas de navegación actualizadas según tu XML
        void navigateToRegisterProject();
        void navigateToRegisterTask();
        void navigateToProjectList();
        void navigateToFavorites();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();

        // Clics de tus cuatro botones
        void onRegisterProjectBtnClicked();
        void onRegisterTaskBtnClicked();
        void onProjectListBtnClicked();
        void onFavoritesBtnClicked();
    }

    interface Model {
        String getUserName();
    }
}