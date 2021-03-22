package com.example.fjs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.chip.ChipGroup;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    FileWriter csvWriter = null;
    ChipGroup chpGrpHommeFemme = findViewById(R.id.chipGroupHommeFemme);
    ChipGroup chpGrpAge = findViewById(R.id.chipGroupAge);
    ChipGroup chpGrpSituationActuel = findViewById(R.id.chipGroupSituationActuelle);
    ChipGroup chpGrpNiveauEtude = findViewById(R.id.chipGroupNiveauEtude);
    ChipGroup chpGrpDiplomeObtenu = findViewById(R.id.chipGroupDiplomeObtenu);
    ChipGroup chpGrpDiplomeAutre = findViewById(R.id.chipGroupDiplomeAutre);
    ChipGroup chpGrpPermis = findViewById(R.id.chipGroupPermis);
    ChipGroup chpGrpVehicule = findViewById(R.id.chipGroupVehicule);
    ChipGroup chpGrpCommentInforme = findViewById(R.id.chipGroupCommentInforme);
    ChipGroup chpGrpSecteurActivite = findViewById(R.id.chipGroupSecteurActivite);
    ChipGroup chpGrpOrientation = findViewById(R.id.chipGroupOrientation);
    ChipGroup chpGrpOffreInteressante = findViewById(R.id.chipGroupOffreInteressante);
    ChipGroup chpGrpDecroche = findViewById(R.id.chipGroupDecroche);
    ChipGroup chpGrpDeplacement = findViewById(R.id.chipGroupDeplacement);
    ChipGroup chpGrpRayon = findViewById(R.id.chipGroupRayon);

    EditText AutreVille = findViewById(R.id.editTextAutreVille);
    EditText AutreDiplomeSco = findViewById(R.id.editTextAutreDiplomeScolaire);
    EditText AutreInformation = findViewById(R.id.editTextCommentInfomeAutre);
    EditText AutreActivite = findViewById(R.id.editTextActiviteAutre);
    EditText OffreCount = findViewById(R.id.editTextOffresCount);
    EditText EntrepriseCV = findViewById(R.id.editTextEntreprise);
    EditText Remarques = findViewById(R.id.editTextRemarque);

    Spinner Villes = findViewById(R.id.spinnerVille);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Button buttonValider = findViewById(R.id.buttonValider);

        buttonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date today = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String nomFichier = "Forum-SP3S-" + dateFormat.format(today) + ".csv";

                FileOutputStream fos = null;
                ArrayList<ArrayList<String>> lesDonnees = new ArrayList<ArrayList<String>>();
                File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

                String path = dir.getAbsolutePath() + "/" + nomFichier;

                File txt = new File(path);
                if(!txt.exists())
                    lesDonnees.add(getNomsColonnes());

                //lesDonnees.add(getLesInformations());
                String lesDonneesString = "";

                for(int i = 0; i < lesDonnees.size(); i++) {
                    for(int j = 0; j < lesDonnees.get(i).size(); j++) {
                        if(j == lesDonnees.get(i).size() - 1) {
                            lesDonneesString += lesDonnees.get(i).get(j) + ";\n";
                        }
                        else {
                            lesDonneesString += lesDonnees.get(i).get(j) + ";";
                        }
                    }
                }

                try {
                    FileOutputStream fOut = new FileOutputStream(txt, true);
                    OutputStreamWriter osw = new OutputStreamWriter(fOut);

                    osw.append(lesDonneesString);
                    osw.close();
                    fOut.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }

                String message = "Merci d'avoir rempli ce formulaire, veuillez rendre la tablette à un responsable";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                restartActivity();
            }
        });



    }
    /*
    Retourne les noms des colonnes du fichier .txt dans une ArrayList
     */
    public ArrayList<String> getNomsColonnes() {
        ArrayList<String> colonnes = new ArrayList<String>();

        colonnes.add("Heure de visite");
        colonnes.add("Genre");
        colonnes.add("Age");
        colonnes.add("Lieu d'habitation (ville)");
        colonnes.add("Situation actuelle");
        colonnes.add("Diplôme obtenu");
        colonnes.add("Autres diplômes");
        colonnes.add("A le permis");
        colonnes.add("A un véhicule");
        colonnes.add("Informé(e) par");
        colonnes.add("Secteur d'activité");
        colonnes.add("S'est orienté(e) dans le forum");
        colonnes.add("Offres intéressantes");
        colonnes.add("A postulé auprès de");
        colonnes.add("A décroché");
        colonnes.add("Peux se déplacer");
        colonnes.add("Remarques particulières");

        return colonnes;
    }
    public String getHeureVisite() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        String time = sdf.format(cal.getTime());
        return time;
    }

    public void restartActivity(){
        Intent mIntent = getIntent();
        finish();
        startActivity(mIntent);
    }
}