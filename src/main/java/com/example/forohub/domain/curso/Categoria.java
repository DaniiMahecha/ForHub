package com.example.forohub.domain.curso;

import com.example.forohub.domain.topico.StatusTopico;

public enum Categoria {
    BACKEND("backend"),
    FRONTEND("frontend"),
    CLAUD("claud");

    private String categoria;

    Categoria(String categoria) {
        this.categoria = categoria;
    }

    public static Categoria categoria(String text) {
        for (Categoria especialidad : Categoria.values()) {
            if (especialidad.categoria.equalsIgnoreCase(text)) {
                return especialidad;
            }
        }
        throw new IllegalArgumentException("Status no existente: " + text);
    }


}
