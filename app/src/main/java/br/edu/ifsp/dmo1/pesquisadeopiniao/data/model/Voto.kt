package br.edu.ifsp.dmo1.pesquisadeopiniao.data.model

import br.edu.ifsp.dmo1.pesquisadeopiniao.utils.OpcaoVoto
import java.security.MessageDigest
import java.time.LocalDateTime

class Voto(prontuario: String, val opcao: OpcaoVoto, fromDB: Boolean) {

    var codigoVoto: String
        private set

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
        return digest.joinToString("") { "%02x".format(it) }
    }
}