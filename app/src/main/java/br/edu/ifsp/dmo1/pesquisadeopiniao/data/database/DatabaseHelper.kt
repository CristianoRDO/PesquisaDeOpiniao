package br.edu.ifsp.dmo1.pesquisadeopiniao.data.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_KEYS.DATABASE_NAME, null, DATABASE_KEYS.DATABASE_VERSION){

    object DATABASE_KEYS {
        const val DATABASE_NAME = "voto.db"
        const val DATABASE_VERSION = 1
        const val VOTE_TABLE_NAME = "voto_tb"
        const val USER_TABLE_NAME = "user_tb"
        const val COLUMN_USER_NAME = "name"
        const val COLUMN_USER_PRONTUARIO = "prontuario"
        const val COLUMN_VOTE_CODE = "code"
        const val COLUMN_VOTE_OPTION = "option"
    }

    private companion object {
        const val CREATE_USER_TABLE = """CREATE TABLE ${DATABASE_KEYS.USER_TABLE_NAME} (
            ${DATABASE_KEYS.COLUMN_USER_PRONTUARIO} TEXT PRIMARY KEY,
            ${DATABASE_KEYS.COLUMN_USER_NAME} TEXT NOT NULL
        );"""

        const val CREATE_VOTE_TABLE = """CREATE TABLE ${DATABASE_KEYS.VOTE_TABLE_NAME} (
            ${DATABASE_KEYS.COLUMN_VOTE_CODE} TEXT PRIMARY KEY,
            ${DATABASE_KEYS.COLUMN_VOTE_OPTION} TEXT CHECK (${DATABASE_KEYS.COLUMN_VOTE_OPTION} IN ('OTIMO', 'BOM', 'REGULAR', 'RUIM'))
        );"""
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_VOTE_TABLE)
        db.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onCreate(db);
    }
}