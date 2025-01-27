package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.user

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo1.pesquisadeopiniao.R
import br.edu.ifsp.dmo1.pesquisadeopiniao.databinding.ActivityUserBinding
import br.edu.ifsp.dmo1.pesquisadeopiniao.utils.Constants

class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configListeners()
    }

    private fun configListeners() {
        binding.arrowBack.setOnClickListener { finish() }

        binding.nextButton.setOnClickListener { setUser() }
    }

    private fun setUser(){
        val prontuario = binding.textProntuaio.text.toString()
        val name = binding.textName.text.toString()

        if(prontuario.isNotBlank() && name.isNotBlank()){
            val resultIntent = Intent()
            resultIntent.putExtra(Constants.KEY_USER_PRONTUARIO, prontuario)
            resultIntent.putExtra(Constants.KEY_USER_NAME, name)
            setResult(RESULT_OK, resultIntent)
            finish()
        } else{
            Toast.makeText(
                this,
                getString(R.string.fill_all_fields_error),
                Toast.LENGTH_SHORT).show()
        }
    }
}