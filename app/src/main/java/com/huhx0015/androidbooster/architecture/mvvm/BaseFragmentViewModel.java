package com.huhx0015.androidbooster.architecture.mvvm;

/**
 * Created by Michael Yoon Huh on 6/22/2017.
 */

@Deprecated
public interface BaseFragmentViewModel {

    void onAttach();

    void onCreateView();

    void onActivityCreated();

    void onDestroyView();
}