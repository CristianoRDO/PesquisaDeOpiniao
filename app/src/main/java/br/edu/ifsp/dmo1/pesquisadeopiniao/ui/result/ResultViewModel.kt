package br.edu.ifsp.dmo1.pesquisadeopiniao.ui.result

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.repository.VotoRepository
import br.edu.ifsp.dmo1.pesquisadeopiniao.utils.OpcaoVoto

class ResultViewModel(application: Application) : AndroidViewModel(application) {
    private val voteRepository = VotoRepository(application)

    fun getVoteResults(): Map<OpcaoVoto, Int> {
        return voteRepository.getTotalVoteByOption()
    }
}