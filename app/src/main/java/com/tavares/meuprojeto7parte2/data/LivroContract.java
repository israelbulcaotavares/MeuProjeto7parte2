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

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public final class LivroContract {

    public static final String CONTENT_AUTHORITY = "com.tavares.meuprojeto7pate2";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_PETS = "livros";

    private LivroContract() {
    }

    public static final class PetEntry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PETS);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PETS;

        public final static String TABLE_NAME = "livros";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUNA_TITULO = "titulo";
        public final static String COLUNA_AUTOR = "autor";
        public final static String COLUNA_PRECO = "preco";
        public final static String COLUNA_QUANTIDADE = "quantidade";
        public final static String COLUNA_NOME_FORNECEDOR = "nome_fornecedor";
        public final static String COLUNA_TELEFONE_FORNECEDOR = "telefone_fornecedor";


    }

}