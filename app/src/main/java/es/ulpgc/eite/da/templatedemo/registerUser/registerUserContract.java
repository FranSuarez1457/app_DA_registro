package es.ulpgc.eite.da.templatedemo.registerUser;

import java.lang.ref.WeakReference;

import es.ulpgc.eite.da.templatedemo.database.UserEntity;

public interface registerUserContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(registerUserViewModel viewModel);
        void finishView();
        void showErrorMessage();
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        void onRegisterButtonClicked(String email, String password);
    }

    interface Model {
        boolean registerNewUser(String email, String password);

        UserEntity getUserByEmail(String email);
    }
}
