package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.vote

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.pesquisadeopiniao.databinding.ActivityVoteBinding


class VoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configListeners()
    }

    private fun configListeners(){
        binding.arrowBack.setOnClickListener{ finish() }

        binding.voteButton.setOnClickListener{ }
    }
}