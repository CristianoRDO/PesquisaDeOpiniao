package br.edu.ifsp.dmo1.pesquisadeopiniao.data.database

import android.content.ContentValues
import br.edu.ifsp.dmo1.pesquisadeopiniao.data.model.Vote
import br.edu.ifsp.dmo1.pesquisadeopiniao.utils.OpcaoVoto

class VotoDao(private val db: DatabaseHelper) {

   fun insert(voto: Vote): Boolean {
       val writer = db.writableDatabase
       val values = ContentValues().apply {
           put(DatabaseHelper.DATABASE_KEYS.COLUMN_VOTE_CODE, voto.codigoVoto)
           put(DatabaseHelper.DATABASE_KEYS.COLUMN_VOTE_OPTION, voto.opcao.toString())
       }
       val result = writer.insert(DatabaseHelper.DATABASE_KEYS.VOTE_TABLE_NAME, null, values)
       return result != -1L
   }

    fun getTotalVoteByOption(): Map<OpcaoVoto, Int> {
        val reader = db.readableDatabase
        val query = """
            SELECT ${DatabaseHelper.DATABASE_KEYS.COLUMN_VOTE_OPTION}, COUNT(*)
            FROM ${DatabaseHelper.DATABASE_KEYS.VOTE_TABLE_NAME}
            GROUP BY ${DatabaseHelper.DATABASE_KEYS.COLUMN_VOTE_OPTION}
        """.trimIndent()

        val cursor = reader.rawQuery(query, null)
        val countByOption: MutableMap<OpcaoVoto, Int> = mutableMapOf()

        cursor.use {
            while (it.moveToNext()) {
                val optionValue = it.getString(0)
                val count = it.getInt(1)
                val option = OpcaoVoto.valueOf(optionValue)

                countByOption[option] = count
            }
        }

        return countByOption
    }

    fun getByCode(code: String): Vote? {
        val reader = db.readableDatabase
        val columns = arrayOf(
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTE_CODE,
            DatabaseHelper.DATABASE_KEYS.COLUMN_VOTE_OPTION
        )

        val where = "${DatabaseHelper.DATABASE_KEYS.COLUMN_VOTE_CODE} = ?"
        val whereArgs = arrayOf(code)
        val cursor = reader.query(DatabaseHelper.DATABASE_KEYS.VOTE_TABLE_NAME,
            columns,
            where,
            whereArgs,
            null,
            null,
            null
        )

        val voto: Vote?
        cursor.use {
            voto = if (it.moveToNext()) {
                val codeBd = it.getString(0)
                val valueOption = it.getString(1)
                val option = OpcaoVoto.valueOf(valueOption)
                Vote(codeBd, option, true)
            } else {
                null
            }
        }

        return voto
    }
}