package com.app.jeferson.filmez.bases;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;

import com.app.jeferson.filmez.network.connectionService.Presenter;
import com.app.jeferson.filmez.util.Constants;
import com.app.jeferson.filmez.util.SessionManager;


public abstract class BaseFragment extends Fragment implements Constants {
    private View mView;
    private Presenter presenter;
    public SessionManager session;

    @Nullable
    @Override
    @JavascriptInterface
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mView != null) {
            ViewGroup parent = (ViewGroup) mView.getParent();
            if (parent != null) {
                parent.removeView(mView);
            }
        }
        try {
            mView = inflater.inflate(getFragmentLayout(), container, false);
        } catch (InflateException e) {
            e.getMessage();
        }
        // keep the fragment and all its data across screen rotation

        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter = new Presenter(getActivity());
        session = new SessionManager(getActivity());
        setLayout(getmView());
        startProperties();
        defineListeners();
    }

    protected void setFragment(Fragment fragment) {
        if (getActivity() instanceof BaseActivity)
            ((BaseActivity) getActivity()).setFragment(fragment, true);
    }

    protected void setFragment(Fragment fragment, boolean stack) {
        if (getActivity() instanceof BaseActivity)
            ((BaseActivity) getActivity()).setFragment(fragment, stack);
    }

    protected void setFragmentForResult(Fragment current_fragment, Fragment new_fragment, int requestCode) {
        ((BaseActivity) getActivity()).setFragmentForResut(current_fragment, new_fragment, requestCode);
    }


    public View getmView() {
        return mView;
    }

    public Presenter getPresenter() {
        return presenter;
    }

    protected abstract void setLayout(View view);

    protected abstract void startProperties();

    protected abstract void defineListeners();

    protected abstract int getFragmentLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
