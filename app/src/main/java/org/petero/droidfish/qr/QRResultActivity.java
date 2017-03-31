package org.petero.droidfish.qr;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.petero.droidfish.R;

public class QRResultActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrresult);

        Intent intent = getIntent();
        String PGNText = intent.getStringExtra("pgn_text");

        ImageView qrView = (ImageView)findViewById(R.id.qrView);
        ProgressBar qrProgressBar = (ProgressBar)findViewById(R.id.qrProgressBar);

        AsyncQRGenerator qrGenerator = new AsyncQRGenerator(qrView,qrProgressBar);
        qrGenerator.execute(PGNText);

    }
}
