package id.co.imastudio.lesson2input;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //1 deklarasi
    EditText edTinggi, edBerat;
    Button btnHitung;
    TextView textHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //2 find
        edTinggi = findViewById(R.id.ed_tinggi);
        edBerat = findViewById(R.id.ed_berat);
        btnHitung = findViewById(R.id.btn_hitung);
        textHasil = findViewById(R.id.text_hasil);


        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hitungBMI();
            }
        });
    }

    private void hitungBMI() {
        Double nilaiTinggi = Double.valueOf(edTinggi.getText().toString());
        Double nilaiBerat = Double.valueOf(edBerat.getText().toString());

        //rumus BMI = berat / (tinggi(m) x tinggi(m))
        Double nilaiTinggiDalamMeter = nilaiTinggi/100;
        Double nilaiHasil = nilaiBerat / (nilaiTinggiDalamMeter * nilaiTinggiDalamMeter);

        if (nilaiHasil < 16){
            textHasil.setText("Nilai BMI :"+ nilaiHasil+" Kamu Sangat Kurus" );
        } else if (nilaiHasil < 18.5){
            textHasil.setText("Nilai BMI :"+ nilaiHasil+" Kamu Kurus" );
        } else if (nilaiHasil < 25){
            textHasil.setText("Nilai BMI :"+ nilaiHasil+" Kamu Normal" );
        } else if (nilaiHasil < 30){
            textHasil.setText("Nilai BMI :"+ nilaiHasil+" Kamu Gemuk" );
        } else {
            textHasil.setText("Nilai BMI :"+ nilaiHasil+" Kamu Obesitas" );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //
            return true;
        } else if (id == R.id.action_call) {
            //Toas
            //2 intent
            // explicit intent --> jelas tujuannya
            // implicit intent --> nggak jelas tujuannya
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE, Manifest.permission.ACCESS_COARSE_LOCATION},
                        2);
            } else {
                Intent telfon = new Intent(Intent.ACTION_CALL, Uri.parse("tel:085740482440"));
                startActivity(telfon);
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //onReq
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 2) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission diterima, kamu udah bisa nelpon", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Oops, permission kamu tolak", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
