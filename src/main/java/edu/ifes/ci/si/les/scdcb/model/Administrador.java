package edu.ifes.ci.si.les.scdcb.model;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@Table(name="administradores")
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="cdUsuario")
public class Administrador extends Usuario {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "Permissoes deve ser preenchido")
    @Min(value = 0L, message = "Permissoes deve ser preenchido com um valor inteiro")
    private Integer permissoes;

    @Builder
    public Administrador(final int permissoes, final String nome, final String login, final String senha) {
        super(null, nome, login, senha, null);
        this.permissoes = permissoes;
    }

    @Override
    public Set<Telefone> getTelefones() { return null; }

    @Override
    public void setTelefones(Set<Telefone> telefone) { }
    
}
