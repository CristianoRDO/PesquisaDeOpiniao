package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.checkVote

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.model.Vote
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.repository.VotoRepository

class CheckVoteViewModel(application: Application) : AndroidViewModel(application) {
    private val voteRepository = VotoRepository(application)

    fun findVoteByCode(code: String): Vote? = voteRepository.getByCode(code)
}