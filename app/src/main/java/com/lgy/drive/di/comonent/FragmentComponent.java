package com.lgy.drive.di.comonent;

import android.app.Activity;

import com.lgy.drive.di.module.FragmentModule;
import com.lgy.drive.ui.fragment.AllOrderFragment;

import dagger.Component;

@Component( modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

    void inject(AllOrderFragment allOrderFragment);
}
