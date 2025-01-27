package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.checkVote

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo1.pesquisadeopiniao.R
import br.edu.ifsp.dmo1.pesquisadeopiniao.databinding.ActivityCheckVoteBinding

class CheckVoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckVoteBinding
    private lateinit var viewModel: CheckVoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckVoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(CheckVoteViewModel::class.java)

        configListeners()
    }

    private fun configListeners(){
        binding.arrowBack.setOnClickListener { finish() }

        binding.checkVoteButton.setOnClickListener { searchVote() }
    }

    private fun searchVote(){
        val code = binding.textCodeVote.text.toString()

        if(code.isNotBlank()){
            val vote = viewModel.findVoteByCode(code)

            if(vote != null){
                binding.titleVoteResult.visibility = View.VISIBLE
                binding.voteResult.text = vote.opcao.description
            }
            else{
                binding.voteResult.text = getString(R.string.error_invalid_code)
                binding.voteResult.setTextColor(getColor(R.color.red_error))
            }

        }else{
            Toast.makeText(this, getString(R.string.error_code_blank), Toast.LENGTH_SHORT).show()
        }
    }
}