package es.ulpgc.eite.da.templatedemo.app;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import es.ulpgc.eite.da.templatedemo.database.*;

// Importa todos tus estados
import es.ulpgc.eite.da.templatedemo.home.homeState;
import es.ulpgc.eite.da.templatedemo.login.loginState;
import es.ulpgc.eite.da.templatedemo.projectList.projectListState;
import es.ulpgc.eite.da.templatedemo.projectDetail.projectDetailState;
import es.ulpgc.eite.da.templatedemo.registerProject.registerProjectState;
import es.ulpgc.eite.da.templatedemo.registerTask.registerTaskState;
import es.ulpgc.eite.da.templatedemo.registerUser.registerUserState;
import es.ulpgc.eite.da.templatedemo.status.statusState;

// Importa tus Activities
import es.ulpgc.eite.da.templatedemo.home.homeActivity;
import es.ulpgc.eite.da.templatedemo.login.loginActivity;
import es.ulpgc.eite.da.templatedemo.projectList.projectListActivity;
import es.ulpgc.eite.da.templatedemo.projectDetail.projectDetailActivity;
import es.ulpgc.eite.da.templatedemo.registerProject.registerProjectActivity;
import es.ulpgc.eite.da.templatedemo.registerTask.registerTaskActivity;
import es.ulpgc.eite.da.templatedemo.registerUser.registerUserActivity;
import es.ulpgc.eite.da.templatedemo.status.statusActivity;

public class AppMediator {

    public static String TAG = "AppMediator";
    private static AppMediator INSTANCE;

    // --- ¡NUESTRO INTERRUPTOR PARA EL FILTRO! ---
    public boolean isFavoriteFilterActive = false;

    // Estados guardados
    private homeState savedHomeState;
    private loginState savedLoginState;
    private projectListState savedProjectListState;
    private projectDetailState savedProjectDetailState;
    private registerProjectState savedRegisterProjectState;
    private registerTaskState savedRegisterTaskState;
    private registerUserState savedRegisterUserState;
    private statusState savedStatusState;

    private UserEntity loggedUser;

    public UserEntity getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(UserEntity loggedUser) {
        this.loggedUser = loggedUser;
    }
    private AppMediator() { Log.e(TAG, "AppMediator creado"); }

    public static AppMediator getInstance() {
        if (INSTANCE == null) { INSTANCE = new AppMediator(); }
        return INSTANCE;
    }

    // --- MÉTODOS DE GET/SET DE ESTADOS ---
    public homeState getHomeState() { return savedHomeState; }
    public void setHomeState(homeState state) { savedHomeState = state; }

    public loginState getLoginState() { return savedLoginState; }
    public void setLoginState(loginState state) { savedLoginState = state; }

    public projectListState getProjectListState() { return savedProjectListState; }
    public void setProjectListState(projectListState state) { savedProjectListState = state; }

    public projectDetailState getProjectDetailState() { return savedProjectDetailState; }
    public void setProjectDetailState(projectDetailState state) { savedProjectDetailState = state; }

    public registerProjectState getRegisterProjectState() { return savedRegisterProjectState; }
    public void setRegisterProjectState(registerProjectState state) { savedRegisterProjectState = state; }

    public registerTaskState getRegisterTaskState() { return savedRegisterTaskState; }
    public void setRegisterTaskState(registerTaskState state) { savedRegisterTaskState = state; }

    public registerUserState getRegisterUserState() { return savedRegisterUserState; }
    public void setRegisterUserState(registerUserState state) { savedRegisterUserState = state; }

    public statusState getStatusState() { return savedStatusState; }
    public void setStatusState(statusState state) { savedStatusState = state; }

    // --- MÉTODOS DE NAVEGACIÓN ---

    public void goToLogin(Context context) {
        context.startActivity(new Intent(context, loginActivity.class));
    }

    public void goToHome(Context context) {
        context.startActivity(new Intent(context, homeActivity.class));
    }

    public void goToProjectList(Context context) {
        context.startActivity(new Intent(context, projectListActivity.class));
    }

    public void goToProjectDetail(Context context) {
        context.startActivity(new Intent(context, projectDetailActivity.class));
    }

    public void goToRegisterProject(Context context) {
        context.startActivity(new Intent(context, registerProjectActivity.class));
    }

    public void goToRegisterTask(Context context) {
        context.startActivity(new Intent(context, registerTaskActivity.class));
    }

    public void goToRegisterUser(Context context) {
        context.startActivity(new Intent(context, registerUserActivity.class));
    }

    public void goToStatus(Context context) {
        context.startActivity(new Intent(context, statusActivity.class));
    }
}