package com.alva.util;

public class RaResponseService<T> {
    public String mensajeError;
    public Boolean estado;
    public T content;

    public RaResponseService()
    {
        estado = true;
    }

    public RaResponseService(String mensajeError, Boolean estado, T content)
    {
        this.mensajeError = mensajeError;
        this.estado = estado;
        this.content = content;
    }

    public String GetMensajeDeError(Exception ex)
    {
        return "\nMensaje:" + ex.getMessage();
    }

    public void SetException(Exception ex)
    {
        this.estado = false;
        this.mensajeError = GetMensajeDeError(ex);
    }
    public void SetMensajeError(String mensaje)
    {
        this.estado = false;
        this.mensajeError = mensaje;

    }

}
