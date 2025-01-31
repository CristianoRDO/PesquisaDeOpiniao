package br.edu.ifsp.dmo1.pesquisadeopiniao.data.model

import br.edu.ifsp.dmo1.pesquisadeopiniao.utils.OpcaoVoto
import java.io.Serializable
import java.security.MessageDigest
import java.time.LocalDateTime

class Vote(prontuario: String, val opcao: OpcaoVoto, fromDB: Boolean) {

    val codigoVoto: String

    init {
        if (!fromDB) {
            this.codigoVoto = criptografar(prontuario)
        } else {
            this.codigoVoto = prontuario
        }
    }

    private fun criptografar(prontuario: String): String {
        val text = prontuario + LocalDateTime.now().toString()
        val bytes = text.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        val hash = digest.joinToString("") { "%02x".format(it) }
        return hash.take(10)
    }
}