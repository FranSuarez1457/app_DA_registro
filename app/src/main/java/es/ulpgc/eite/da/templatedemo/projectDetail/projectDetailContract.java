package es.ulpgc.eite.da.templatedemo.projectDetail;

import java.lang.ref.WeakReference;

public interface projectDetailContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(projectDetailViewModel viewModel);
        void updateFavoriteIcon(boolean isFavorite); // Para cambiar la estrella de vacía a llena
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        void onFavoriteBtnClicked(); // Cuando el usuario toque la estrella
    }

    interface Model {
        // En el futuro, buscará los datos reales del proyecto en la BBDD
        projectDetailState getSimulatedProject();
    }
}
