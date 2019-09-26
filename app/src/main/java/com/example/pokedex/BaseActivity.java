package com.example.pokedex;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pokedex.network.PokemonLoader;
import com.example.pokedex.utils.DialogManager;

public class BaseActivity extends AppCompatActivity {
    public PokemonLoader loader;
    private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loader = new PokemonLoader();
        progress = new ProgressDialog(this);
    }

    public void showProgress () {
        progress.setCancelable(false); //Esto hace que cuando el user aprete afuera de la pantalla no se cancele
        progress.setMessage("Loading...");
        progress.show();
    }

    public void hideProgress () {
        if(progress.isShowing()){
            progress.dismiss();
        }

    }

    public void showDialogError() {
        DialogManager manager = new DialogManager(BaseActivity.this, "ERROR", "Error with server");

        Dialog dialog = manager.buildDialog();

        dialog.show();

        manager.getBtDialog().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
