package com.app.jeferson.filmez.bases;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.app.jeferson.filmez.R;
import com.app.jeferson.filmez.network.connectionService.Presenter;
import com.app.jeferson.filmez.util.Constants;
import com.app.jeferson.filmez.util.SessionManager;


public abstract class BaseActivity extends AppCompatActivity implements Constants {
    private Presenter presenter;
    public SessionManager session;
    private Toolbar toolbar;
    private Integer layoutToolbar;
    private String titleToolbar = "";
    private String subtitleToolbar = "";
    private int iconNav = 0;
    private View.OnClickListener onClickListener;
    public Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(getActivityLayout());

        session = new SessionManager(getContext());
        presenter = new Presenter(getContext());

        setLayout();
        startProperties();
        defineListeners();

        if (getLayoutToolbar() != null) {
            toolbar = (Toolbar) findViewById(getLayoutToolbar());
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(getTitleToolbar());
                if (!getSubtitleToolbar().isEmpty())
                    getSupportActionBar().setSubtitle(getSubtitleToolbar());
            }
            if (getIconNav() != 0) {
                toolbar.setNavigationIcon(getIconNav());
            }
            if (getOnClickListener() != null) {
                toolbar.setNavigationOnClickListener(getOnClickListener());
            }
        }
    }

    public Presenter getPresenter() {
        return presenter;
    }

    protected abstract void setLayout();

    protected abstract void startProperties();

    protected abstract void defineListeners();

    protected abstract int getActivityLayout();

    protected abstract Context getContext();

    public Toolbar getToolbar() {
        return toolbar;
    }

    private Integer getLayoutToolbar() {
        return layoutToolbar;
    }

    private String getTitleToolbar() {
        return titleToolbar;
    }

    public String getSubtitleToolbar() {
        return subtitleToolbar;
    }

    public void setSubtitleToolbar(String subtitleToolbar) {
        this.subtitleToolbar = subtitleToolbar;
    }

    private void setTitleToolbar(String titleToolbar) {
        this.titleToolbar = titleToolbar;
    }

    private void setLayoutToolbar(Integer layoutToolbar) {
        this.layoutToolbar = layoutToolbar;
    }

    public void setToolbar(int layout, String title) {
        setLayoutToolbar(layout);
        setTitleToolbar(title);
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        };
        setOnClickListener(onClickListener);
    }

    public void setToolbar(int layout, String title, String subtitleToolbar) {
        setLayoutToolbar(layout);
        setTitleToolbar(title);
        setSubtitleToolbar(subtitleToolbar);
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        };
        setOnClickListener(onClickListener);
    }

    public void setToolbar(int layout, String title, View.OnClickListener onClickListener) {
        setLayoutToolbar(layout);
        setTitleToolbar(title);
        setOnClickListener(onClickListener);
    }

    public void setToolbar(int layout, String title, View.OnClickListener onClickListener, int iconNav) {
        setLayoutToolbar(layout);
        setTitleToolbar(title);
        setOnClickListener(onClickListener);
        setIconNav(iconNav);
    }

    public int getIconNav() {
        return iconNav;
    }

    public void setIconNav(int iconNav) {
        this.iconNav = iconNav;
    }

    public void setToolbarFragments(int layout, String title) {
        setLayoutToolbar(layout);
        setTitleToolbar(title);
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                } else {
                    onBackPressed();
                }
            }
        };
        setOnClickListener(onClickListener);
    }

    public void setToolbarFragments(int layout, String title, String subTitle) {
        setLayoutToolbar(layout);
        setTitleToolbar(title);
        setSubtitleToolbar(subTitle);
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() != 0) {
                    getFragmentManager().popBackStack();
                } else {
                    onBackPressed();
                }
            }
        };
        setOnClickListener(onClickListener);
    }

    public void setToolbar(int layout, String title, final boolean finish) {
        setLayoutToolbar(layout);
        setTitleToolbar(title);
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                if (finish)
                    finish();

            }
        };
        setOnClickListener(onClickListener);
    }

    private View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    private void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public void setToolbar(int layout) {
        setLayoutToolbar(layout);
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        };
        setOnClickListener(onClickListener);
    }

    public void setToolbar(int layout, View.OnClickListener onClickListener) {
        setLayoutToolbar(layout);
        setOnClickListener(onClickListener);

    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }


    public void setFragment(Fragment fragment, boolean stack) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        if (!stack) {
            if (findViewById(R.id.frame) != null && savedInstanceState == null) {
                fragmentTransaction.replace(R.id.frame, fragment);
            }
        } else {
            fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
            fragmentTransaction.replace(R.id.frame, fragment);
            fragmentTransaction.addToBackStack(null);
        }

        fragmentTransaction.commit();
    }


    public void setFragmentForResut(Fragment current_fragment, Fragment new_fragment, int requestCode) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        fragmentTransaction.replace(R.id.frame, new_fragment);
        new_fragment.setTargetFragment(current_fragment, requestCode);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }





}
