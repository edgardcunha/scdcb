package edu.ifes.ci.si.les.scdcb.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(of = {"cdUsuario"})
public abstract class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer cdUsuario;

    @Column(length = 50)
    @NotBlank(message = "Nome do Usuário deve ser preenchido")
    @Size(min = 2, max = 50, message = "Nome do Usuário deve ter entre 2 e 50 letras")
    private String nome;

    @Column(length = 20)
    @NotBlank(message = "Login do Usuário deve ser preenchido")
    @Size(min = 2, max = 50, message = "Login do Usuário deve ter entre 2 e 20 caracteres")
    private String login;

    @Column(length = 20)
    @NotBlank(message = "Senha do Usuário deve ser preenchida")
    @Size(min = 3, max = 20, message = "Senha do Usuário deve ter entre 3 e 20 caracteres")
    private String senha;
    
    @OneToMany(cascade = {CascadeType.PERSIST})
    private Set<Telefone> telefones = new HashSet<Telefone>();

    public Set<Telefone> getTelefones() {
        return telefones;
    }

}
