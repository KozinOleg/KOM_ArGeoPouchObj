package com.example.ok.kom_argeopouchobj.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;

import com.example.ok.kom_argeopouchobj.R;
import com.example.ok.kom_argeopouchobj.fragments.BeyondArFragment;
import com.example.ok.kom_argeopouchobj.fragments.FaceDetectingFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ActivityContainer";

    private FragmentManager mSupportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mSupportFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            mSupportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, FaceDetectingFragment.newInstance())
                    .commit();
        }

        getFragment(findViewById(R.id.beyond_fragment));
    }

    private void getFragment(@Nullable final View view) {
        assert view != null;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragmentByTag = mSupportFragmentManager.findFragmentByTag(BeyondArFragment.TAG);
                if (fragmentByTag == null || !fragmentByTag.isAdded())
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, BeyondArFragment.newInstance())
                            .commit();
            }
        });
    }
}
