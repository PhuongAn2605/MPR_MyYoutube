package fit.hanu.myyoutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import org.w3c.dom.Text;

public class PlayActivity extends AppCompatActivity {
    private VideoView videoView;
    private int position;
    private TextView txtLike, txtDisLike;
    private ImageButton btnLike, btnDislike, btnShare;
    private int countLike = 0, countDislike = 0;

    private TextView txtCommented;
    private EditText txtComment;

    private ImageButton btnComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        txtLike = findViewById(R.id.txtLike);
        txtDisLike = findViewById(R.id.txtDislike);

        btnLike = findViewById(R.id.btnLike);
        btnDislike = findViewById(R.id.btnDislike);
        btnShare = findViewById(R.id.btnShare);

        txtCommented = findViewById(R.id.txtCommented);
        txtComment = findViewById(R.id.txtComment);
        btnComment = findViewById(R.id.btnComment);

        Intent intent = getIntent();
        String link = intent.getStringExtra("LINK");

//        TextView tvLink = findViewById(R.id.tvLink);
//        tvLink.setText(link);

        //init player
        videoView = findViewById(R.id.videoView);
        videoView.setVideoPath(link);

        //control the video
        videoView.setMediaController(new MediaController(this));



        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countLike = countLike + 1;
                txtLike.setText(countLike + "");
            }
        });

        btnDislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDislike ++;
                txtDisLike.setText(countDislike + "");
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, link);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "My favourite video");
                startActivity(shareIntent);
            }
        });

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = txtComment.getText().toString();
                txtCommented.append("\n" + "     "+comment);
                txtCommented.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
//        Log.d("onStart", this.position + "");

        videoView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
//        Log.d("onPause", this.position + "");


        position = videoView.getCurrentPosition();
    }

    @Override
    protected void onStop() {
        super.onStop();
//        Log.d("onStop", this.position + "");

        videoView.stopPlayback();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

//        Log.d("onSaveInstanceState", this.position + "");
        outState.putInt("PLAYBACK", this.position);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

//        Log.d("onRestoreInstanceState", this.position + "");
        //restore current playback position
        videoView.seekTo(savedInstanceState.getInt("PLAYBACK"));
    }
}