package edu.ifes.ci.si.les.scdcb.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "telefones")
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="numero")
public class Telefone implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(length = 50, unique = true, nullable = false)
    @NotBlank(message = "O número do telefone deve ser preenchido")
    @Size(min = 2, max = 50, message = "O número do telefon deve ter entre 2 e 50 letras")
	private String numero;
	
	@ManyToOne
    @JoinColumn(name="cd_usuario")
	private Usuario usuario;

}
