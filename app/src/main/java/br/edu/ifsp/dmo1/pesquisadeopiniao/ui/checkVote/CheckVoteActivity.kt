package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.checkVote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.pesquisadeopiniao.R
import br.edu.ifsp.dmo1.pesquisadeopiniao.databinding.ActivityCheckVoteBinding

class CheckVoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckVoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckVoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configListeners()
    }

    private fun configListeners(){
        binding.arrowBack.setOnClickListener { finish() }
    }
}