
package com.ashokkumarshrestha.cam.camera;

import android.app.Activity;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ashokkumarshrestha.cam.R;

import java.util.ArrayList;
import java.util.List;


public class CameraPreviewActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "CameraPreviewActivity";
    /**
     * Id of the camera to access. 0 is the first camera.
     */
    private static final int CAMERA_ID = 0;
    private Button btnPicture;

    private Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Open an instance of the first camera and retrieve its info.
        mCamera = getCameraInstance(CAMERA_ID);
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(CAMERA_ID, cameraInfo);

        if (mCamera == null) {
            // Camera is not available, display error message
            setContentView(R.layout.activity_camera_unavailable);
        } else {

            setContentView(R.layout.activity_camera);

            // Get the rotation of the screen to adjust the preview image accordingly.
            int displayRotation = getWindowManager().getDefaultDisplay().getRotation();

            // Create the Preview view and set it as the content of this Activity.
            CameraPreview cameraPreview = new CameraPreview(this, null,
                    0, mCamera, cameraInfo, displayRotation);
            FrameLayout preview = findViewById(R.id.camera_preview);
            preview.addView(cameraPreview);
        }

        initIDs();
    }

    private void initIDs() {
        btnPicture = (Button)findViewById(R.id.btnPicture);

        btnPicture.setOnClickListener(this);

    }

    @Override
    public void onPause() {
        super.onPause();
        releaseCamera();
    }

    private Camera getCameraInstance(int cameraId) {
        Camera c = null;
        try {
            c = Camera.open(cameraId);
        } catch (Exception e) {
            Log.e(TAG, "Camera " + cameraId + " is not available: " + e.getMessage());
        }
        return c;
    }

    private void releaseCamera() {
        if (mCamera != null) {
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPicture:
                //save image to phone

                break;
        }
    }
}
