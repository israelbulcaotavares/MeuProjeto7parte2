package com.tavares.meuprojeto7parte2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Livro implements Parcelable {
    private String titulo;
    private String autor;
    private String preco;
    private String quantidade;
    private String fornecedor;
    private String telefone;

    public Livro() {
    }

    protected Livro(Parcel in) {
        titulo = in.readString();
        autor = in.readString();
        preco = in.readString();
        quantidade = in.readString();
        fornecedor = in.readString();
        telefone = in.readString();
    }

    public static final Creator<Livro> CREATOR = new Creator<Livro>() {
        @Override
        public Livro createFromParcel(Parcel in) {
            return new Livro(in);
        }

        @Override
        public Livro[] newArray(int size) {
            return new Livro[size];
        }
    };

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(autor);
        dest.writeString(preco);
        dest.writeString(quantidade);
        dest.writeString(fornecedor);
        dest.writeString(telefone);
    }
}
