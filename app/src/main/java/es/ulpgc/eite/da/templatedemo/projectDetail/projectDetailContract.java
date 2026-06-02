package es.ulpgc.eite.da.templatedemo.projectDetail;

import java.lang.ref.WeakReference;

public interface projectDetailContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(projectDetailViewModel viewModel);
        void showFavoriteAddedMessage(); // NUEVO: Para avisar al usuario de que se guardó
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        void onFavoriteButtonClicked(); // NUEVO: Reacciona al botón de la estrella
    }

    interface Model {
        // Devuelve true si se guarda correctamente
        boolean addToFavorites();
    }
}
