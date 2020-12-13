package br.com.mulacar.model;

import br.com.mulacar.enumeration.EnumTipoTelefone;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class Contato {
    
    private int id;
    private EnumTipoTelefone tipoTelefone;
    private String numero;
    private String email;
    private Cliente cliente;
    private Motorista motorista;

    public Contato() {
    }

    public Contato(int id, String email, EnumTipoTelefone tipoTelefone, String numero) {
        this.id     = id;
        this.email  = email;
        this.tipoTelefone = tipoTelefone;
        this.numero = numero;
    }
    
    public Contato(Cliente cliente) {
        this.cliente = cliente;
    }

    public Contato(Motorista motorista) {
        this.motorista = motorista;
    }
    
    public Contato(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnumTipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(EnumTipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    @Override
    public String toString() {
        return "Contato{" + "id=" + id + ", tipoTelefone=" + tipoTelefone + ", numero=" + numero + ", email=" + email + ", cliente=" + cliente + ", motorista=" + motorista + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Contato other = (Contato) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
