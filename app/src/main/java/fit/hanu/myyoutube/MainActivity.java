package fit.hanu.myyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editLink = findViewById(R.id.edtLink);
        //get ref to button
        ImageButton btnPlay = findViewById(R.id.btnPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = editLink.getText().toString();
                //Toast.makeText(MainActivity.this, link, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, PlayActivity.class);
                intent.putExtra("LINK", link);

                startActivity(intent);

            }
        });
    }
}