package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.votar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.model.User
import br.edu.ifsp.dmo1.pesquisadeopiniao.databinding.ActivityVotarBinding
import br.edu.ifsp.dmo1.pesquisadeopiniao.ui.user.UserActivity
import br.edu.ifsp.dmo1.pesquisadeopiniao.utils.Constants

class VotarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVotarBinding
    private lateinit var usuarioResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var votoResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var viewModel: VotarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVotarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(VotarViewModel::class.java)

        configListeners()
    }

    private fun configListeners(){
        binding.arrowBack.setOnClickListener{ finish() }

        binding.userButton.setOnClickListener {
            usuarioResultLauncher.launch(Intent(this, UserActivity::class.java))
        }

        binding.selectVoteButton.setOnClickListener {
            votoResultLauncher.launch(Intent(this, VotarActivity::class.java))
        }

        binding.voteButton.setOnClickListener{  }
    }

    private fun configResultLauncher() {
        usuarioResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val extras = result.data?.extras
                if (extras != null) {
                    val name = extras.getString(Constants.KEY_USER_NAME)
                    val prontuario = extras.getString(Constants.KEY_USER_PRONTUARIO)

                    if (prontuario != null && name != null) {
                        if (prontuario == "admin" && name == "admin") {
                            Toast.makeText(this, "TU já votou.", Toast.LENGTH_LONG).show()
                        }
//                        val findUser = viewModel.findUserByProntuario(prontuario)
//                        if (findUser != null) {
//                            viewModel.insertUser(User(prontuario, name))
//                        } else {
//                            Toast.makeText(this, "O usuário ${name} já votou.", Toast.LENGTH_LONG)
//                                .show()
//                        }
                    }
                }
            }
        }

        votoResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK) {

            }
        }
    }

    private fun setRedirectTo(activity: Activity) {
        val mIntent: Intent
        if (activity == UserActivity::class.java) {
            mIntent = Intent(this, UserActivity::class.java)
        }
    }

    private fun redirectTo(mIntent: Intent) {

    }
}