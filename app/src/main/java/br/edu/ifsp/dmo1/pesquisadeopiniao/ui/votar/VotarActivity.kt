package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.votar

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.dmo1.pesquisadeopiniao.R
import br.edu.ifsp.dmo1.pesquisadeopiniao.databinding.ActivityMainBinding
import br.edu.ifsp.dmo1.pesquisadeopiniao.databinding.ActivityVotarBinding
import br.edu.ifsp.dmo1.pesquisadeopiniao.ui.user.UserActivity
import br.edu.ifsp.dmo1.pesquisadeopiniao.ui.vote.VoteActivity

class VotarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVotarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVotarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configListeners()
    }

    private fun configListeners(){
        binding.arrowBack.setOnClickListener{ finish() }

        binding.userButton.setOnClickListener{ startActivity(Intent(this, UserActivity::class.java)) }

        binding.selectVoteButton.setOnClickListener{ startActivity(Intent(this, VoteActivity::class.java)) }

        binding.voteButton.setOnClickListener{  }
    }
}