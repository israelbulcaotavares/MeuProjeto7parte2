package com.tavares.meuprojeto7parte2.adapter;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.tavares.meuprojeto7parte2.R;
import com.tavares.meuprojeto7parte2.data.PetContract;

public class PetCursorAdapter extends CursorAdapter {

    public PetCursorAdapter(Context context, Cursor c) {
        super(context, c, 0 /* flags */);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.celula_livro, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tituloTextView =  view.findViewById(R.id.titulo);
        TextView autorTextView =  view.findViewById(R.id.autor);
        TextView precoTextView =  view.findViewById(R.id.preco);
        TextView quantidadeTextView =  view.findViewById(R.id.quantidade);
        TextView nomeFornecedorTextView =  view.findViewById(R.id.nome_fornecedor);
        TextView telefoneFornecedorTextView =  view.findViewById(R.id.telefone_fornecedor);

        int tituloColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUNA_TITULO);
        int autorColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUNA_AUTOR);
        int precoColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUNA_PRECO);
        int quantidadeColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUNA_QUANTIDADE);
        int nomeFornecedorColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUNA_NOME_FORNECEDOR);
        int telefoneFornecedorColumnIndex = cursor.getColumnIndex(PetContract.PetEntry.COLUNA_TELEFONE_FORNECEDOR);

        String tituloLivro = cursor.getString(tituloColumnIndex);
        String nomeAutor = cursor.getString(autorColumnIndex);
        String precoAutor = cursor.getString(precoColumnIndex);
        String quantidadeAutor = cursor.getString(quantidadeColumnIndex);
        String nomeFornecedorAutor = cursor.getString(nomeFornecedorColumnIndex);
        String telefoneFornecedorAutor = cursor.getString(telefoneFornecedorColumnIndex);

        tituloTextView.setText(tituloLivro);
        if (TextUtils.isEmpty(nomeAutor)) {
            nomeAutor = context.getString(R.string.unknown);
        }
        autorTextView.setText(nomeAutor);
        precoTextView.setText(precoAutor);
        quantidadeTextView.setText(quantidadeAutor);
        nomeFornecedorTextView.setText(nomeFornecedorAutor);
        telefoneFornecedorTextView.setText(telefoneFornecedorAutor);
     }
}