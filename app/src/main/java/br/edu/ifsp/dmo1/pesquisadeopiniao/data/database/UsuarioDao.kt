package br.edu.ifsp.dmo1.pesquisadeopiniao.data.database

import android.content.ContentValues
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.model.User

class UsuarioDao(private val db: DatabaseHelper) {

    fun insert(usuario: User): Boolean {
        val writer = db.writableDatabase
        val values = ContentValues().apply {
            put(DatabaseHelper.DATABASE_KEYS.COLUMN_USER_PRONTUARIO, usuario.prontuario);
            put(DatabaseHelper.DATABASE_KEYS.COLUMN_USER_NAME, usuario.nome)
        }
        val result = writer.insert(DatabaseHelper.DATABASE_KEYS.USER_TABLE_NAME, null, values)
        return result != -1L
    }

    fun findUserByProntuario(prontuario: String): User? {
        val reader = db.readableDatabase
        val columns = arrayOf(
            DatabaseHelper.DATABASE_KEYS.COLUMN_USER_PRONTUARIO,
            DatabaseHelper.DATABASE_KEYS.COLUMN_USER_NAME
        )

        val where = "${DatabaseHelper.DATABASE_KEYS.COLUMN_USER_PRONTUARIO} = ?"
        val whereArgs = arrayOf(prontuario)
        val cursor = reader.query(DatabaseHelper.DATABASE_KEYS.USER_TABLE_NAME,
            columns,
            where,
            whereArgs,
            null,
            null,
            null
        )

        val usuario: User?
        cursor.use {
            usuario = if (it.moveToNext()) {
                User(it.getString(0), it.getString(1))
            } else {
                null
            }
        }

        return usuario
    }
}