package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.vote

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.pesquisadeopiniao.databinding.ActivityVoteBinding
import br.edu.ifsp.dmo1.pesquisadeopiniao.utils.Constants

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

        binding.voteButton.setOnClickListener{ setOptionVote() }
    }

    private fun setOptionVote(){
        val selectedRadioButtonId = binding.radioGroup.checkedRadioButtonId

        if (selectedRadioButtonId != -1) {
            val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
            val optionVote = selectedRadioButton.text.toString()

            val resultIntent = Intent()
            resultIntent.putExtra(Constants.KEY_VOTE_OPTION, optionVote)
            setResult(RESULT_OK, resultIntent)
            finish()
        } else {
            Toast.makeText(this, "Por favor, selecione uma opção", Toast.LENGTH_SHORT).show()
        }
    }
}