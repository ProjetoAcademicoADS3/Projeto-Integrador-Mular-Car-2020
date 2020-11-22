package br.com.mulacar.model;

import br.com.mulacar.enumeration.EnumTipoTelefone;
import java.util.Objects;

/**
 *
 * @author Gabriel Cunha <gabrielcunhadev@gmail.com>
 */
public class Contato {
    
    private Long id;
    private EnumTipoTelefone tipoTelefone;
    private String telefone;
    private String email;
    private Cliente cliente;
    private Motorista motorista;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EnumTipoTelefone getTipoTelefone() {
        return tipoTelefone;
    }

    public void setTipoTelefone(EnumTipoTelefone tipoTelefone) {
        this.tipoTelefone = tipoTelefone;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
        return "Contato{" + "id=" + id + ", tipoTelefone=" + tipoTelefone + ", telefone=" + telefone + ", email=" + email + ", cliente=" + cliente + ", motorista=" + motorista + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
