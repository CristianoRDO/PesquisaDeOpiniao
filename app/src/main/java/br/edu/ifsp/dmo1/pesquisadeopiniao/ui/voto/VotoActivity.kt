package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.voto

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.dmo1.pesquisadeopiniao.R
import br.edu.ifsp.dmo1.pesquisadeopiniao.databinding.ActivityVotoBinding

class VotoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}