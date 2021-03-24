package com.example.fjs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    FileWriter csvWriter = null;


    String Ville = null;

    ChipGroup chpGrpHommeFemme;
    ChipGroup chpGrpAge;
    ChipGroup chpGrpSituationActuel;
    ChipGroup chpGrpNiveauEtude;
    ChipGroup chpGrpDiplomeObtenu;
    ChipGroup chpGrpDiplomeAutre;
    ChipGroup chpGrpPermis;
    ChipGroup chpGrpVehicule;
    ChipGroup chpGrpCommentInforme;
    ChipGroup chpGrpSecteurActivite;
    ChipGroup chpGrpOrientation;
    ChipGroup chpGrpOffreInteressante;
    ChipGroup chpGrpDecroche;
    ChipGroup chpGrpDeplacement;
    ChipGroup chpGrpRayon;

    Spinner spinnerVille;

    EditText AutreVille;
    EditText AutreDiplomeSco;
    EditText AutreInformation;
    EditText AutreActivite;
    EditText OffreCount;
    EditText EntrepriseCV;
    EditText Remarques;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        Button buttonValider = findViewById(R.id.buttonValider);

        chpGrpHommeFemme = findViewById(R.id.chipGroupHommeFemme);
        chpGrpAge = findViewById(R.id.chipGroupAge);
        chpGrpSituationActuel = findViewById(R.id.chipGroupSituationActuelle);
        chpGrpNiveauEtude = findViewById(R.id.chipGroupNiveauEtude);
        chpGrpDiplomeObtenu = findViewById(R.id.chipGroupDiplomeObtenu);
        chpGrpDiplomeAutre = findViewById(R.id.chipGroupDiplomeAutre);
        chpGrpPermis = findViewById(R.id.chipGroupPermis);
        chpGrpVehicule = findViewById(R.id.chipGroupVehicule);
        chpGrpCommentInforme = findViewById(R.id.chipGroupCommentInforme);
        chpGrpSecteurActivite = findViewById(R.id.chipGroupSecteurActivite);
        chpGrpOrientation = findViewById(R.id.chipGroupOrientation);
        chpGrpOffreInteressante = findViewById(R.id.chipGroupOffreInteressante);
        chpGrpDecroche = findViewById(R.id.chipGroupDecroche);
        chpGrpDeplacement = findViewById(R.id.chipGroupDeplacement);
        chpGrpRayon = findViewById(R.id.chipGroupRayon);

        spinnerVille = findViewById(R.id.spinnerVille);

        AutreVille = findViewById(R.id.editTextAutreVille);
        AutreDiplomeSco = findViewById(R.id.editTextAutreDiplomeScolaire);
        AutreInformation = findViewById(R.id.editTextCommentInfomeAutre);
        AutreActivite = findViewById(R.id.editTextActiviteAutre);
        OffreCount = findViewById(R.id.editTextOffresCount);
        EntrepriseCV = findViewById(R.id.editTextEntreprise);
        Remarques = findViewById(R.id.editTextRemarque);

        TextView test = findViewById(R.id.textViewAge);

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
                if (!txt.exists())
                    lesDonnees.add(getNomsColonnes());

                lesDonnees.add(getInformations());
                String lesDonneesString = "";

                for (int i = 0; i < lesDonnees.size(); i++) {
                    for (int j = 0; j < lesDonnees.get(i).size(); j++) {
                        if (j == lesDonnees.get(i).size() - 1) {
                            lesDonneesString += lesDonnees.get(i).get(j) + ";\n";
                        } else {
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
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String message = "Merci d'avoir rempli ce formulaire, veuillez rendre la tablette à un responsable";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                restartActivity();
            }
        });

        spinnerVille.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Ville = spinnerVille.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Ville = AutreVille.getText().toString();
            }
        });


    }

    public ArrayList<String> getInformations() {
        ArrayList<String> info = new ArrayList<>();

        Chip tmp;

        //genre
        tmp = findViewById(chpGrpHommeFemme.getCheckedChipId());
        String genre = tmp.getText().toString();


        //age
        tmp = findViewById(chpGrpAge.getCheckedChipId());
        String age = tmp.getText().toString();


        //ville

        if (Ville.contains("--Selectionnez une ville--")) {

            Ville = AutreVille.getText().toString();

        }


        //Situation
        tmp = findViewById(chpGrpSituationActuel.getCheckedChipId());
        String Situation = tmp.getText().toString();

        //niveau etudes
        String Etude;
        if (Situation != "A la recherche d'un emploi") {
            tmp = findViewById(chpGrpNiveauEtude.getCheckedChipId());
            Etude = tmp.getText().toString();
        } else {
            Etude = "";
        }

        //Diplome
        tmp = findViewById(chpGrpDiplomeObtenu.getCheckedChipId());
        String Diplome = tmp.getText().toString();


        //autre Diplome
        List<Integer> DiplomeIDs;
        DiplomeIDs = chpGrpDiplomeAutre.getCheckedChipIds();
        String lesDiplomes = "";
        if (DiplomeIDs != null) {

            for (Integer id : DiplomeIDs) {
                tmp = findViewById(id);
                if (!lesDiplomes.isEmpty()) {

                    lesDiplomes += ", " + tmp.getText().toString();

                } else lesDiplomes += tmp.getText().toString();
            }

        }

        if (AutreDiplomeSco.getText().length() != 0) {

            if (!lesDiplomes.isEmpty()) {

                lesDiplomes += ", " + AutreDiplomeSco.getText().toString();

            } else lesDiplomes += AutreDiplomeSco.getText().toString();
        }


        //voiture/permis
        tmp = findViewById(chpGrpPermis.getCheckedChipId());
        String permis = tmp.getText().toString();

        tmp = findViewById(chpGrpVehicule.getCheckedChipId());
        String voiture = tmp.getText().toString();


        //information du forum
        List<Integer> InfoIDs;
        InfoIDs = chpGrpCommentInforme.getCheckedChipIds();
        String lesInfo = "";
        if (InfoIDs != null) {

            for (Integer id : InfoIDs) {
                tmp = findViewById(id);
                if (!lesInfo.isEmpty()) {

                    lesInfo += ", " + tmp.getText().toString();

                } else lesInfo += tmp.getText().toString();
            }

        }

        if (AutreInformation.getText().length() != 0) {

            if (!lesInfo.isEmpty()) {

                lesInfo += ", " + AutreInformation.getText().toString();

            } else lesInfo += AutreInformation.getText().toString();
        }


        //Secteur activite
        List<Integer> ActiviteIDs;
        ActiviteIDs = chpGrpSecteurActivite.getCheckedChipIds();
        String lesActivites = "";
        if (ActiviteIDs != null) {

            for (Integer id : ActiviteIDs) {
                tmp = findViewById(id);
                if (!lesActivites.isEmpty()) {

                    lesActivites += ", " + tmp.getText().toString();

                } else lesActivites += tmp.getText().toString();
            }

        }

        if (AutreActivite.getText().length() != 0) {

            if (!lesActivites.isEmpty()) {

                lesActivites += ", " + AutreActivite.getText().toString();

            } else lesActivites += AutreActivite.getText().toString();
        }


        //orientation
        tmp = findViewById(chpGrpOrientation.getCheckedChipId());
        String orientation = tmp.getText().toString();


        //offres interessante
        tmp = findViewById(chpGrpOffreInteressante.getCheckedChipId());
        String offre = tmp.getText().toString();


        //Nombre d'offre
        String NBoffre = "";
        NBoffre = OffreCount.getText().toString();


        //depose CV
        String cv = "";
        cv = EntrepriseCV.getText().toString();


        //decroche
        List<Integer> decrocheIDs;
        decrocheIDs = chpGrpDecroche.getCheckedChipIds();
        String lesDecroche = "";
        if (decrocheIDs != null) {

            for (Integer id : decrocheIDs) {
                tmp = findViewById(id);
                if (!lesDecroche.isEmpty()) {

                    lesDecroche += ", " + tmp.getText().toString();

                } else lesDecroche += tmp.getText().toString();
            }

        }


        // deplacement

        tmp = findViewById(chpGrpDeplacement.getCheckedChipId());
        String deplacement = tmp.getText().toString();


        //rayon deplacement

        tmp = findViewById(chpGrpRayon.getCheckedChipId());
        String rayon = tmp.getText().toString();


        //remarques

        String remarques = Remarques.getText().toString();


        info.add(getHeureVisite());
        info.add(genre);//inportant
        info.add(age);//inportant
        info.add(Ville);//inportant
        info.add(Situation);//inportant
        info.add(Etude);
        info.add(Diplome);//inportant
        info.add(lesDiplomes);
        info.add(permis);//inportant
        info.add(voiture);//inportant
        info.add(lesInfo);//inportant
        info.add(lesActivites);//inportant
        info.add(orientation);//inportant
        info.add(offre);//inportant
        info.add(NBoffre);
        info.add(cv);
        info.add(lesDecroche);//inportant
        info.add(deplacement);
        info.add(rayon);
        info.add(remarques);

        return info;

    }

    /*
    Retourne les noms des colonnes du fichier .txt dans une ArrayList
     */
    public ArrayList<String> getNomsColonnes() {
        ArrayList<String> colonnes = new ArrayList<>();

        colonnes.add("Heure de visite");
        colonnes.add("Genre");//inportant
        colonnes.add("Age");//inportant
        colonnes.add("Lieu d'habitation (ville)");//inportant
        colonnes.add("Situation actuelle");//inportant
        colonnes.add("Niveau d'études");
        colonnes.add("Diplôme obtenu");//inportant
        colonnes.add("Autres diplômes");
        colonnes.add("A le permis");//inportant
        colonnes.add("A un véhicule");//inportant
        colonnes.add("Informé(e) par");//inportant
        colonnes.add("Secteur d'activité");//inportant
        colonnes.add("S'est orienté(e) dans le forum");//inportant
        colonnes.add("Offres intéressantes");//inportant
        colonnes.add("Nombre d'offres");
        colonnes.add("A postulé auprès de");
        colonnes.add("A décroché");//inportant
        colonnes.add("Peux se déplacer");
        colonnes.add("Distance de déplacement");
        colonnes.add("Remarques particulières");

        return colonnes;
    }

    public String getHeureVisite() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        return sdf.format(cal.getTime());
    }

    public void restartActivity() {
        Intent mIntent = getIntent();
        finish();
        startActivity(mIntent);
    }
}