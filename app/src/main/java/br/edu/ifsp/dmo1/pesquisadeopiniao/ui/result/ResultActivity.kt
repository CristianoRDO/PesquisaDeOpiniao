package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo1.pesquisadeopiniao.R
import br.edu.ifsp.dmo1.pesquisadeopiniao.databinding.ActivityResultBinding
import br.edu.ifsp.dmo1.pesquisadeopiniao.utils.OpcaoVoto

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var viewModel: ResultViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ResultViewModel::class.java)

        configListeners()
        configUI()
    }

    private fun configListeners(){
        binding.arrowBack.setOnClickListener { finish() }
    }

    private fun configUI(){
        val map = viewModel.getVoteResults()

        binding.otimoResult.text = (map[OpcaoVoto.OTIMO]?.toString() ?: "0")
        binding.bomResult.text = (map[OpcaoVoto.BOM]?.toString() ?: "0")
        binding.regularResult.text = (map[OpcaoVoto.REGULAR]?.toString() ?: "0")
        binding.ruimResult.text = (map[OpcaoVoto.RUIM]?.toString() ?: "0")
        binding.totalResult.text = map.values.sum().toString()
    }
}