package com.shehrozwali.travelpartner1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.net.URI;

public class WonderWorldActivity extends AppCompatActivity {
    ImageButton greatWallMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wonder_world);


    }


    public void machuPicchu(View view) {
        gotoUrl("https://www.google.com/maps/place/Machu+Picchu/@-13.1632045,-72.5452412,20z/data=!4m5!3m4!1s0x916d9a5f89555555:0x3a10370ea4a01a27!8m2!3d-13.1631412!4d-72.5449629");

    }



    public void chichenItza(View view) {
        gotoUrl("https://www.google.com/maps/place/Chichen+Itza,+Playacar,+77717+Playa+del+Carmen,+Q.R.,+Mexico/@20.6176723,-87.0865916,19z/data=!4m5!3m4!1s0x8f4e4314b7875827:0x2bde299e2f580df0!8m2!3d20.6172958!4d-87.0861437");
    }

    public void effelTower(View view) {
        gotoUrl("https://www.google.com/maps/place/Eiffel+Tower,+Paris,+France/@48.8582231,2.2934114,17z/data=!4m5!3m4!1s0x47e6701f7e8337b5:0xa2cb58dd28914524!8m2!3d48.8560934!4d2.2930458");
    }

    public void leanTower(View view) {
        gotoUrl("https://www.google.com/maps/place/Leaning+Tower+of+Pisa/@43.7229559,10.3944083,17z/data=!3m1!4b1!4m5!3m4!1s0x12d591a6c44e88cd:0x32eca9b1d554fc03!8m2!3d43.722952!4d10.396597");
    }

    public void taj(View view) {
        gotoUrl("https://www.google.com/maps/place/Taj+Mahal/@27.1751496,78.041595,19z/data=!4m5!3m4!1s0x39747121d702ff6d:0xdd2ae4803f767dde!8m2!3d27.1751448!4d78.0421422");
    }

    public void Roman(View view) {
        gotoUrl("https://www.google.com/maps/place/Colosseum/@41.8902142,12.4911366,18z/data=!4m5!3m4!1s0x132f61b6532013ad:0x28f1c82e908503c4!8m2!3d41.8902102!4d12.4922309");
    }

    public void christRedeemer(View view) {
        gotoUrl("https://www.google.com/maps/place/Machu+Picchu/@-13.1632045,-72.5452412,20z/data=!4m5!3m4!1s0x916d9a5f89555555:0x3a10370ea4a01a27!8m2!3d-13.1631412!4d-72.5449629");
        gotoUrl("");

    }

    public void greatWall(View view) {
        gotoUrl("https://www.google.com/maps/place/Great+Wall+of+China/@40.4319118,116.5681862,17z/data=!3m1!4b1!4m5!3m4!1s0x35f121d7687f2ccf:0xd040259b950522df!8m2!3d40.4319077!4d116.5703749");
    }


    private void gotoUrl(String url) {
        Uri uri= Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}