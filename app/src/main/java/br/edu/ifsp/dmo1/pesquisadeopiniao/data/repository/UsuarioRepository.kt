package br.edu.ifsp.dmo1.pesquisadeopiniao.data.repository

import android.content.Context
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.database.DatabaseHelper
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.database.UsuarioDao
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.model.Usuario

class UsuarioRepository(context: Context) {

    private val dbHelper = DatabaseHelper(context)
    private val userDao = UsuarioDao(dbHelper)

    fun insert(usuario: Usuario) = userDao.insert(usuario)

    fun findUserByProntuario(prontuario: String) = userDao.findUserByProntuario(prontuario)
}