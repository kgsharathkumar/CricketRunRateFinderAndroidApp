package com.kbtci.kbpl;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText teamRun_A,teamBalls_A, teamWkts_A,teamRun_B,teamBalls_B, teamWkts_B, teamNameA, teamNameB;
    EditText rr_Team_b,rr_Team_a, nrr_team_a, nrr_team_b;
    private double noOfBallsTeamA;
    private double noOfBallsTeamB;
    private double runRateTeamA,runRateTeamB;
    private double netRunRate_team_A, netRunRate_team_B;
    private ProgressDialog busyDialog;
    TextView winnerTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //INPUTS
        teamRun_A =(EditText)findViewById(R.id.team_a_run);
        teamRun_B =(EditText)findViewById(R.id.team_b_run);
        teamBalls_A = (EditText)findViewById(R.id.team_a_balls);
        teamBalls_B = (EditText)findViewById(R.id.team_b_balls);
        teamWkts_A = (EditText) findViewById(R.id.team_a_wkts);
        teamWkts_B = (EditText) findViewById(R.id.team_b_wkts);
        teamNameA = (EditText) findViewById(R.id.team_a_name);
        teamNameB = (EditText) findViewById(R.id.team_b_name);

        Button teamArrBtn = (Button) findViewById(R.id.team_a_rr);
        Button teamBrrBtn = (Button) findViewById(R.id.team_b_rr);
        Button teamNetRunRate = (Button) findViewById(R.id.teams_net_runrate_btn);


        //OUTPUTS
        rr_Team_a = (EditText) findViewById(R.id.team_a_runrate);
        rr_Team_b = (EditText) findViewById(R.id.team_b_runrate);
        nrr_team_a = (EditText) findViewById(R.id.team_a_net_runrate);
        nrr_team_b = (EditText) findViewById(R.id.team_b_net_runrate);
        winnerTeam =(TextView)findViewById(R.id.winnerTeam);

        // Team-A RunRate
        teamArrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    double teamRunA = Integer.parseInt(teamRun_A.getText().toString());
                    double teamBallsA = Integer.parseInt(teamBalls_A.getText().toString());
                    double teamWktsA = Integer.parseInt(teamWkts_A.getText().toString());
                    if(teamWktsA >= 10.0) {
                        if(teamWktsA == 10) {
                            noOfBallsTeamA = 60.0;
                        } else {
                            alertUser("In Cricket, Wkts should not be greater than 10 wkts");
                        }
                    } else {
                        noOfBallsTeamA = teamBallsA;
                    }
                    runRateTeamA = (teamRunA * 6)/noOfBallsTeamA;
                    rr_Team_a.setText(Double.toString(runRateTeamA));
                } catch (Exception e){
                    alertUser("Please Enter Team-A Match Infomation");
                }
            }
        });
        // Team-B RunRate
        teamBrrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    double teamRunB = Integer.parseInt(teamRun_B.getText().toString());
                    double teamBallsB = Integer.parseInt(teamBalls_B.getText().toString());
                    double teamWktsB = Integer.parseInt(teamWkts_B.getText().toString());

                    if(teamWktsB >= 10.0) {
                        if(teamWktsB == 10) {
                            noOfBallsTeamB = 60.0;
                        } else {
                            alertUser("In Cricket, Wkts should not be greater than 10 wkts");
                        }
                    } else {
                        noOfBallsTeamB = teamBallsB;
                    }
                    runRateTeamB = (teamRunB * 6)/noOfBallsTeamB;
                    rr_Team_b.setText(Double.toString(runRateTeamB));
                } catch( Exception e) {
                    alertUser("Please Enter Team-B Match Infomation");
                }
            }
        });

        teamNetRunRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    netRunRate_team_A = runRateTeamA - runRateTeamB;
                    netRunRate_team_B = runRateTeamB - runRateTeamA;
                    String wTeamNameA = teamNameA.getText().toString();
                    String wTeamNameB = teamNameB.getText().toString();
                    if(netRunRate_team_A > netRunRate_team_B) {
                        winnerTeam.setText(wTeamNameA);
                    } else {
                        winnerTeam.setText(wTeamNameB);
                    }
                    nrr_team_a.setText(Double.toString(netRunRate_team_A));
                    nrr_team_b.setText(Double.toString(netRunRate_team_B));

                } catch (Exception e) {
                    alertUser("First Calculate Teams RunRate");
                }
            }
        });
    }

    private void alertUser(final String msg) {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {

                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Alert");
                alertDialog.setMessage(msg);
                alertDialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        if (busyDialog != null) {
                            busyDialog.dismiss();
                        }
                    }
                });
                alertDialog.show();
            }
        });
    }
}
