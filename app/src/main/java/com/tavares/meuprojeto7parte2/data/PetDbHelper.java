/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tavares.meuprojeto7parte2.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class PetDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ryr5.db";
    private static final int DATABASE_VERSION = 1;

    public PetDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CRIAR_LIVROS = "CREATE TABLE " + PetContract.PetEntry.TABLE_NAME + "("
                + PetContract.PetEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PetContract.PetEntry.COLUNA_TITULO + " TEXT NOT NULL, "
                + PetContract.PetEntry.COLUNA_AUTOR + " TEXT, "
                + PetContract.PetEntry.COLUNA_PRECO + " TEXT, "
                + PetContract.PetEntry.COLUNA_QUANTIDADE + " TEXT, "
                + PetContract.PetEntry.COLUNA_NOME_FORNECEDOR + " TEXT, "
                + PetContract.PetEntry.COLUNA_TELEFONE_FORNECEDOR + " TEXT) ";

        db.execSQL(CRIAR_LIVROS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}