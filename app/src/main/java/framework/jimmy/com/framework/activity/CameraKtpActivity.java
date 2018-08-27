package framework.jimmy.com.framework.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import framework.jimmy.com.framework.R;
import framework.jimmy.com.framework.customview.CameraPreview;
import framework.jimmy.com.framework.helper.HelperCamera;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;


public class CameraKtpActivity extends AppCompatActivity implements SurfaceHolder.Callback {

    @BindView(R.id.img_preview)
    ImageView imgPreview;

    @OnClick(R.id.fab_capture)
    public void onClickFabCapture() {
        mCamera.takePicture(null, null, mPictureCallback);
    }

    String TAG = "CameraKtpActivity";
    SurfaceView mSurfaceView;
    SurfaceHolder mSurfaceHolder;
    Camera mCamera;
    Boolean mPreviewRunning = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_ktp);
        ButterKnife.bind(this);

        // Create an instance of Camera
        mSurfaceView = (SurfaceView) findViewById(R.id.surface_camera);

        mSurfaceHolder = mSurfaceView.getHolder();

        mSurfaceHolder.addCallback(this);

        mSurfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    public void surfaceCreated(SurfaceHolder holder) {

        mCamera = Camera.open();
        try {

            mCamera.setPreviewDisplay(holder);

        } catch (IOException e) {

            e.printStackTrace();

        }
        Camera.Parameters parameters = mCamera.getParameters();
        if (this.getResources().getConfiguration().orientation !=
                Configuration.ORIENTATION_LANDSCAPE)
        {
            parameters.set("orientation", "portrait"); //<----THis gets the job done!!!
                // For Android Version 2.2 and above
                mCamera.setDisplayOrientation(90);
            // For Android Version 2.0 and above
            parameters.setRotation(90);
        }


        // End Effects for Android Version 2.0 and higher
        mCamera.setParameters(parameters);
        mPreviewRunning = true;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {


        if (mPreviewRunning) {

            mCamera.stopPreview();

        }

        //Camera.Parameters p = mCamera.getParameters();

        //p.setPreviewSize(w, h);

        //mCamera.setParameters(p);

        try {

            mCamera.setPreviewDisplay(holder);

        } catch (IOException e) {

            e.printStackTrace();

        }

        mCamera.startPreview();

        mPreviewRunning = true;

    }


    public void surfaceDestroyed(SurfaceHolder holder) {
        mCamera.stopPreview();
        mPreviewRunning = false;
        mCamera.release();
    }

    Camera.PictureCallback mPictureCallback = new Camera.PictureCallback() {
        public void onPictureTaken(byte[] imageData, Camera c) {
            Bitmap bmp = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            //imgPreview.setImageBitmap(bmp);
            Intent intent = new Intent(CameraKtpActivity.this, UpgradeKeOvoPremierKtpActivity.class);
            intent.putExtra("image_ktp",bmp);
            startActivity(intent);
        }
    };
}