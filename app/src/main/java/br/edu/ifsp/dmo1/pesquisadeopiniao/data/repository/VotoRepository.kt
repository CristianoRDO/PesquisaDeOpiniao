package br.edu.ifsp.dmo1.pesquisadeopiniao.data.repository

import android.content.Context
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.database.DatabaseHelper
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.database.VotoDao
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.model.Voto

class VotoRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)
    private val votoDao = VotoDao(dbHelper)

    fun insert(voto: Voto) = votoDao.insert(voto)

    fun getTotalVoteByOption() = votoDao.getTotalVoteByOption()

    fun getByCode(code: String) = votoDao.getByCode(code)
}