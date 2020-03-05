package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static id.putraprima.skorbola.ResultActivity.KEY_PEMAIN;
import static id.putraprima.skorbola.ResultActivity.KEY_RESULT;
import static id.putraprima.skorbola.ScorerActivity.AWAY_SENDER;
import static id.putraprima.skorbola.ScorerActivity.HOME_SENDER;
import static id.putraprima.skorbola.ScorerActivity.SENDER_KEY;

public class MatchActivity extends AppCompatActivity {

    //VARIABEL KEY
    public  static  final  String WINNER = "winner";
    public  static final String  KEY_TIM = "tim";
    private static final int REQUEST_SCORER = 1;
    public  static final int RESULT_HOME = 2;
    public static  final int RESULT_AWAY = 3;
    public static final String NAME_SCORER = "scorer_name";

    //VARIABEL
    private TextView homeTeamName;
    private TextView awayTeamName;
    private MainActivity main;
    private Integer skorHome;
    private Integer skorAway;
    private TextView poinHome;
    private TextView pointAway;

    private ImageView logoHome;
    private ImageView logoAway;

    private ArrayList<String> homeSkor = new ArrayList<>();
    private ArrayList<String> awaySkor = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        homeTeamName = findViewById(R.id.txt_home);
        awayTeamName = findViewById(R.id.txt_away);
        logoHome = findViewById(R.id.home_logo);
        logoAway = findViewById(R.id.away_logo);
        poinHome = findViewById(R.id.score_home);
        pointAway = findViewById(R.id.score_away);

        poinHome.setText("0");
        pointAway.setText("0");
        skorHome = 0;
        skorAway =0;

        Bundle extras = getIntent().getExtras();
        Bundle bundle = getIntent().getExtras();

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        if(bundle != null){
            String homename = getIntent().getExtras().getString("homeTeam");
            String awayname = getIntent().getExtras().getString("awayTeam");
            homeTeamName.setText(homename);
            awayTeamName.setText(awayname);
        }
        Bitmap logohome = extras.getParcelable("logohome");
        logoHome.setImageBitmap(logohome);

        Bitmap logoaway = extras.getParcelable("logoaway");
        logoAway.setImageBitmap(logoaway);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_SCORER) {
            if (resultCode == RESULT_HOME) {
                int homeScore = Integer.parseInt(poinHome.getText().toString()) + 1;
                poinHome.setText(homeScore + "");
                homeSkor.add(data.getStringExtra(NAME_SCORER));

            }else if(resultCode == RESULT_AWAY){
                int awayScore = Integer.parseInt(pointAway.getText().toString()) + 1;
                pointAway.setText(awayScore + "");
                awaySkor.add(data.getStringExtra(NAME_SCORER) +1);
            }

        }
    }

    //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
    //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
    //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",

    public void addHomeButton(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        intent.putExtra(SENDER_KEY,HOME_SENDER);
        startActivityForResult(intent,REQUEST_SCORER);
    }

    public void addAwayButton(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        intent.putExtra(SENDER_KEY,AWAY_SENDER);
        startActivityForResult(intent,REQUEST_SCORER);

    }
    public void cekHasil(View view) {
        String winner;
        int scoreHome = Integer.parseInt(poinHome.getText().toString());
        int scoreAway = Integer.parseInt(pointAway.getText().toString());

        Intent intent = new Intent(this, ResultActivity.class);
        if (scoreHome == scoreAway){
            intent.putExtra(KEY_RESULT, "DRAW");
        }else if (scoreAway < scoreHome){
            intent.putExtra(KEY_RESULT, homeTeamName.getText().toString());
            intent.putExtra(KEY_PEMAIN, homeSkor);
        }else{
            intent.putExtra(KEY_RESULT, awayTeamName.getText().toString());
            intent.putExtra(KEY_PEMAIN, awaySkor);
        }
        startActivity(intent);

    }
}
