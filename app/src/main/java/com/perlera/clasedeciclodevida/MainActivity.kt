package com.perlera.clasedeciclodevida

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //Views
    private lateinit var teamAScoreTextView: TextView
    private lateinit var teamBScoreTextView: TextView
    private lateinit var teamAAddButton: Button
    private lateinit var teamBAddButton: Button
    private lateinit var saveButton: Button

    //Data
    private var scoreTeamA = 0
    private var scoreTeamB = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // log clase especialq ue nos permite mostrar en la consola de desarrolo un mensaje de debug para
        //que podamos ver que esta pasando
        Log.d(TAG, "onCreate")
        bind()
        setupScores(savedInstanceState)
        addListeners()
        //el let nos devuelve el mismo savedINstantState pero
        //siendo no nulo xd

    }

    //utiliza un objeto de parametro de tipo bundle(objeto que nos permite almacenar valores mixtos)
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_SCORE_TEAM_A,scoreTeamA)
        outState.putInt(KEY_SCORE_TEAM_B,scoreTeamB)
        Log.d(TAG, "onSaveInstanState")
    }

    private fun addListeners(){
        teamAAddButton.setOnClickListener{
            scoreTeamA++
            updateVisualScore(teamAScoreTextView, scoreTeamA)
        }

        teamBAddButton.setOnClickListener{
            scoreTeamB++
            updateVisualScore(teamBScoreTextView, scoreTeamB)
        }

        saveButton.setOnClickListener{
            onSave()
        }
    }

    //funcion para guardar
    private fun onSave(){
        Log.d(TAG, "onSave")
        //Intent
        val intent = Intent(this, ScoreActivity::class.java)
        //con esto ya mandamos los datos xd
        intent.putExtra(KEY_SCORE_TEAM_A, scoreTeamA)
        intent.putExtra(KEY_SCORE_TEAM_B, scoreTeamB)
        startActivity(intent)
    }

    private fun updateVisualScore(view: TextView, score: Int){
        view.text = score.toString()
    }

    private fun bind(){
        //team A
        teamAScoreTextView = findViewById(R.id.status_team_a_text_view)
        teamAAddButton = findViewById(R.id.action_add_one_team_a)
        //team B
        teamBScoreTextView = findViewById(R.id.score_team_b_text_view)
        teamBAddButton = findViewById(R.id.action_add_one_team_b)
        //Save
        saveButton = findViewById(R.id.action_Save)
    }

    private fun setupScores(savedInstanceState: Bundle?) {
        savedInstanceState?.let{ bundle ->
            scoreTeamA = bundle.getInt(KEY_SCORE_TEAM_A, 0)
            scoreTeamB = bundle.getInt(KEY_SCORE_TEAM_B, 0)
        }
        updateVisualScore(teamAScoreTextView, scoreTeamA)
        updateVisualScore(teamBScoreTextView, scoreTeamB)
    }
    //funcion de ciclo de vida de la actividad
    //al estar iniciada
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    //que esta a punto de iniciar
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    //cuando esta pausandose
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    //cuando esta pausada
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart")
    }

    companion object {
        val TAG = MainActivity::class.simpleName
        const val KEY_SCORE_TEAM_A = "ScoreTeamA"
        const val KEY_SCORE_TEAM_B = "ScoreTeamB"
        //automaticamente va a tomar el nombre de la actividad
    }
}

//Declarar valores quemados es mala practica
