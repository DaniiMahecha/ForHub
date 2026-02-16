package com.example.forohub.domain.topico;

public enum StatusTopico {
    CREADO("creado"),
    CERRADO("cerrado"),
    RESUELTO("resuelto");

    private String status;

    StatusTopico(String text) {
        this.status = text;
    }

    public static StatusTopico fromFront(String text) {
        for (StatusTopico especialidad : StatusTopico.values()) {
            if (especialidad.status.equalsIgnoreCase(text)) {
                return especialidad;
            }
        }
        throw new IllegalArgumentException("Status no existente: " + text);
    }
}
