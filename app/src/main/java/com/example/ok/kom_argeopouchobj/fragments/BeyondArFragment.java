package com.example.ok.kom_argeopouchobj.fragments;


import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beyondar.android.view.BeyondarGLSurfaceView;
import com.beyondar.android.view.OnClickBeyondarObjectListener;
import com.beyondar.android.view.OnTouchBeyondarViewListener;
import com.beyondar.android.world.BeyondarObject;
import com.beyondar.android.world.World;
import com.example.ok.kom_argeopouchobj.R;
import com.example.ok.kom_argeopouchobj.ar_beyond.CustomWorldHelper;

import java.util.ArrayList;

/**
 * Created by ok on 05.05.16.
 */
public class BeyondArFragment extends com.beyondar.android.fragment.BeyondarFragmentSupport
        implements OnTouchBeyondarViewListener, OnClickBeyondarObjectListener {

    public static final String TAG = "BeyondArFragment";

    private TextView mLabelText;

    public static BeyondArFragment newInstance() {
        return new BeyondArFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        container.addView(inflater.inflate(R.layout.fragment_beyond_ar_text, container, false));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mLabelText = (TextView) view.findViewById(R.id.labelText);
    }

    @Override
    public void onResume() {
        super.onResume();
        World mWorld = CustomWorldHelper.generateObjects(getActivity());
        setWorld(mWorld);
        showFPS(true);

        setOnTouchBeyondarViewListener(this);
        setOnClickBeyondarObjectListener(this);
    }

    @Override
    public void onClickBeyondarObject(ArrayList<BeyondarObject> arrayList) {
        if (arrayList.size() > 0) {
            arrayList.get(0).setImageResource(R.drawable.fire_touched_image);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onTouchBeyondarView(MotionEvent motionEvent, BeyondarGLSurfaceView beyondarGLSurfaceView) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();

        ArrayList<BeyondarObject> geoObjects = new ArrayList<>();

        // This method call is better to don't do it in the UI thread!
        beyondarGLSurfaceView.getBeyondarObjectsOnScreenCoordinates(x, y, geoObjects);

        String textEvent = "";

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                textEvent = "Event type ACTION_DOWN: ";
                break;
            case MotionEvent.ACTION_UP:
                textEvent = "Event type ACTION_UP: ";
                break;
            case MotionEvent.ACTION_MOVE:
                textEvent = "Event type ACTION_MOVE: ";
                break;
            default:
                break;
        }

        for (BeyondarObject geoObject : geoObjects) {
            textEvent = textEvent + " " + geoObject.getName();
        }

        if (mLabelText != null) {
            mLabelText.setText("Event: " + textEvent);
        }
    }
}
