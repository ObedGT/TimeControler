package com.parcial.parcialito;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Video extends AppCompatActivity {
    private VideoView video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        video = findViewById(R.id.video);

        String direccionVideo = "http://techslides.com/demos/sample-videos/small.mp4";
        Uri direccion = Uri.parse(direccionVideo);
        video.setVideoURI(direccion); //dandole al videoView la direccion del video

        MediaController mediaController = new MediaController(this);
        video.setMediaController(mediaController);
        mediaController.setAnchorView(video);

    }

}
