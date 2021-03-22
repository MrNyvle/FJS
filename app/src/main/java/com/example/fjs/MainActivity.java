package com.example.fjs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

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


    }
}