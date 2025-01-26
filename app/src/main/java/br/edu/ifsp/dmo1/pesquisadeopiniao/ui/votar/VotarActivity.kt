package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.votar

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.model.User
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.model.Vote
import br.edu.ifsp.dmo1.pesquisadeopiniao.databinding.ActivityVotarBinding
import br.edu.ifsp.dmo1.pesquisadeopiniao.ui.user.UserActivity
import br.edu.ifsp.dmo1.pesquisadeopiniao.ui.vote.VoteActivity
import br.edu.ifsp.dmo1.pesquisadeopiniao.utils.Constants
import br.edu.ifsp.dmo1.pesquisadeopiniao.utils.OpcaoVoto

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
        configObservers()
        configResultLauncher()
    }

    private fun configObservers(){
        viewModel.user.observe(this, Observer { user ->
            if (user != null) {
                binding.userButton.isEnabled = false
            }
        })

        viewModel.vote.observe(this, Observer { vote ->
            Toast.makeText(this, "${vote.codigoVoto}", Toast.LENGTH_SHORT).show()
            if (vote != null) {
                binding.selectVoteButton.isEnabled = false
            }
        })
    }

    private fun configListeners(){
        binding.arrowBack.setOnClickListener{ finish() }

        binding.userButton.setOnClickListener {
            if(viewModel.user.value == null){
                usuarioResultLauncher.launch(Intent(this, UserActivity::class.java))
            } else{
                Toast.makeText(this, "Usuário Já Inserido", Toast.LENGTH_LONG).show()
            }
        }

        binding.selectVoteButton.setOnClickListener {
            if(viewModel.vote.value == null){
                votoResultLauncher.launch(Intent(this, VoteActivity::class.java))
            }else{
                Toast.makeText(this, "Opcao Ja Selecionada", Toast.LENGTH_LONG).show()
            }
        }

        binding.voteButton.setOnClickListener{

            Toast.makeText(this, "${viewModel.vote.value!!.codigoVoto}, ${viewModel.vote.value!!.opcao}", Toast.LENGTH_LONG).show()

            viewModel.insertDatabaseVote()
        }
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
//                        if (prontuario == "admin" && name == "admin") {
//                            Toast.makeText(this, "TU já votou.", Toast.LENGTH_LONG).show()
//                       }
                       val findUser = viewModel.findUserByProntuario(prontuario)
                        if (findUser == null) {
                            viewModel.insertUser(User(prontuario, name))
                       } else {
                           Toast.makeText(this, "O usuário ${name} já votou.", Toast.LENGTH_LONG).show()
                       }
                    }
                }
            }
        }

        votoResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val extras = result.data?.extras
                if (extras != null) {
                    val option = extras.getString(Constants.KEY_VOTE_OPTION)

                    if (option != null) {
                        val prontuario = viewModel.getProntuarioUser()

                        if(prontuario != null){
                            viewModel.insertVote(Vote(prontuario, OpcaoVoto.valueOf(option.uppercase()), false))
                        }
                    }
                }
            }
        }
    }
}