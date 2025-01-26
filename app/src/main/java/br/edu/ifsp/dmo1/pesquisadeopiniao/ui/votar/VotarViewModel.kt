package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.votar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.model.User
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.model.Vote
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.repository.UsuarioRepository
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.repository.VotoRepository
import br.edu.ifsp.dmo1.pesquisadeopiniao.utils.OpcaoVoto

class VotarViewModel(application: Application) : AndroidViewModel(application) {
    private val voteRepository = VotoRepository(application)
    private val userRepository = UsuarioRepository(application)

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user

    private val _vote = MutableLiveData<Vote>()
    val vote: LiveData<Vote> get() = _vote

    private val _insertedVote = MutableLiveData<Boolean>()
    val insertedVote: LiveData<Boolean> = _insertedVote

    fun insertUser(user: User) {
        _user.value = user
    }

    fun insertVote(vote: Vote) {
        _vote.value = vote
    }

    fun findUserByProntuario(prontuario: String): User? = userRepository.findUserByProntuario(prontuario)

    fun insertDatabaseVote() {
        val vote = _vote.value
        val user = _user.value

        if (user != null && vote != null) {
            voteRepository.insert(vote)
            userRepository.insert(user)
            _insertedVote.value = true
        }
    }

    fun getProntuarioUser(): String? {
        return _user.value?.prontuario
    }
}