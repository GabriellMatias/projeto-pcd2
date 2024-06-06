package org.projetoPCD.subClasses;

import com.google.gson.annotations.SerializedName;

public class Livro {
    @SerializedName("titulo")
    private String titulo;

    @SerializedName("autor")
    private String autor;

    @SerializedName("genero")
    private String genero;

    @SerializedName("numero_exemplares")
    private int numeroExemplares;

    private boolean alugado; // Flag para indicar se o livro está alugado ou não

    public Livro(String titulo, String autor, String genero, int numeroExemplares) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.numeroExemplares = numeroExemplares;
        this.alugado = false; // Por padrão, o livro não está alugado ao ser criado
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public int getNumeroExemplares() {
        return numeroExemplares;
    }

    public void setNumeroExemplares(int numeroExemplares) {
        this.numeroExemplares = numeroExemplares;
    }

    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", genero='" + genero + '\'' +
                ", numeroExemplares=" + numeroExemplares +
                ", alugado=" + alugado +
                '}';
    }
}
