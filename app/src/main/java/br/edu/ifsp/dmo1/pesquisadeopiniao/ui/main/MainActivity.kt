package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.pesquisadeopiniao.databinding.ActivityMainBinding
import br.edu.ifsp.dmo1.pesquisadeopiniao.ui.checkVote.CheckVoteActivity
import br.edu.ifsp.dmo1.pesquisadeopiniao.ui.result.ResultActivity
import br.edu.ifsp.dmo1.pesquisadeopiniao.ui.votar.VotarActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configListeners()
    }

    private fun configListeners(){
        binding.participateResearchButton.setOnClickListener{ startActivity(Intent(this, VotarActivity::class.java)) }

        binding.checkVoteButton.setOnClickListener{ startActivity(Intent(this, CheckVoteActivity::class.java)) }

        binding.countVotesButton.setOnClickListener{ startActivity(Intent(this, ResultActivity::class.java)) }
    }
}