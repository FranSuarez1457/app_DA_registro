package es.ulpgc.eite.da.templatedemo.registerTask;

import java.lang.ref.WeakReference;

public interface registerTaskContract {

    interface View {
        void injectPresenter(Presenter presenter);
        void displayData(registerTaskViewModel viewModel);
        void navigateToStatus(); // Salto a la pantalla de estado
    }

    interface Presenter {
        void injectView(WeakReference<View> view);
        void injectModel(Model model);
        void onResume();
        // Recibe los tres datos del formulario
        void onRegisterBtnClicked(String project, String taskName, String responsible);
    }

    interface Model {
        // Lógica simulada de registro
        boolean registerNewTask(String project, String taskName, String responsible);
    }
}
