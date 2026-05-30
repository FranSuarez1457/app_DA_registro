package es.ulpgc.eite.da.templatedemo.first;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import es.ulpgc.eite.da.templatedemo.R;
import es.ulpgc.eite.da.templatedemo.second.SecondActivity;


// Project: Template Demo
// Created by Luis Hernandez on 17/6/24.
// Copyright (c) 2024 EITE (ULPGC).
public class   FirstActivity
    extends AppCompatActivity implements FirstContract.View {

    public static String TAG = "FirstActivity";

    private FirstContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        setTitle(R.string.first_screen_title);

        Log.e(TAG, "onCreate()");

        // do the setup
        FirstScreen.configure(this);

        Log.e(TAG, "initView()");

        // init or update the state
        if (savedInstanceState == null) {
            presenter.onCreateCalled();

        } else {
            presenter.onRecreateCalled();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume()");

        // load the data
        presenter.onResumeCalled();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e(TAG, "onBackPressed()");

        presenter.onBackButtonPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause()");

        presenter.onPauseCalled();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");

        presenter.onDestroyCalled();
    }

    @Override
    public void onRefreshViewWithUpdatedData(FirstViewModel viewModel) {
        Log.e(TAG, "onRefreshViewWithUpdatedData()");

        // deal with the data
        ((TextView) findViewById(R.id.msgTextView)).setText(viewModel.scrMsg);
    }

    public void clearButtonClicked(View view) {
        Log.e(TAG, "clearButtonClicked()");
        presenter.onClearButtonClicked();
    }

    public void nextButtonClicked(View view) {
        Log.e(TAG, "nextButtonClicked()");
        presenter.onNextButtonClicked();
    }

    @Override
    public void navigateToNextScreen() {
        Log.e(TAG, "navigateToNextScreen()");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    /*
    @Override
    public void navigateToPreviousScreen() {
        Log.e(TAG, "navigateToPreviousScreen()");

        finish();
    }
    */

    @Override
    public void injectPresenter(FirstContract.Presenter presenter) {
        Log.e(TAG, "injectPresenter()");

        this.presenter = presenter;
    }


}