package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.votar

import android.content.ClipData
import android.content.ClipboardManager
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
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
                binding.userButton.visibility = View.GONE
                binding.selectVoteButton.visibility = View.VISIBLE
            }
        })

        viewModel.vote.observe(this, Observer { vote ->
            if (vote != null) {
                binding.selectVoteButton.visibility = View.GONE
                binding.voteButton.visibility = View.VISIBLE
            }
        })

        viewModel.insertedVote.observe(this, Observer {
            if(it){
                binding.voteButton.visibility = View.GONE
                binding.textResultVote.visibility = View.VISIBLE
                binding.codeVote.visibility = View.VISIBLE

                binding.codeVote.text = viewModel.vote.value!!.codigoVoto

                binding.copyCodeButton.visibility = View.VISIBLE
            }
        })
    }

    private fun configListeners(){
        binding.arrowBack.setOnClickListener{ finish() }

        binding.userButton.setOnClickListener {
            if(viewModel.user.value == null){
                usuarioResultLauncher.launch(Intent(this, UserActivity::class.java))
            }
        }

        binding.selectVoteButton.setOnClickListener {
            if(viewModel.vote.value == null){
                votoResultLauncher.launch(Intent(this, VoteActivity::class.java))
            }
        }
        binding.voteButton.setOnClickListener{ viewModel.insertDatabaseVote() }

        binding.copyCodeButton.setOnClickListener{
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            val clip = ClipData.newPlainText("Código", binding.codeVote.text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Texto copiado para a área de transferência!", Toast.LENGTH_SHORT).show()
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