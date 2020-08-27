package edu.ifes.ci.si.les.scdcb.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import lombok.*;

@Entity
@Table(name="instituicoes")
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name="cdUsuario")
public class Instituicao extends Usuario {

    private static final long serialVersionUID = 1L;
    
    @Column(length = 50)
    @NotBlank(message = "CNPJ da Instituição deve ser preenchido")
    @Size(min = 2, max = 50, message = "CNPJ da Instituição deve ter entre 2 e 50 letras")
    @Pattern(regexp="\\d{2}.\\d{3}.\\d{3}/\\d{4}-\\d{2}", message = "CNPJ da Instituição deve seguir o padrão NN.NNN.NNN/NNNN-NN")
    private String cnpj;

    @NotNull(message = "Limite de Doacoes da Instituição deve ser preenchido")
    @Min(value = 0L, message = "Limite de Doacoes da Instituição deve ser preenchido com um valor inteiro")
    private Integer limiteDoacoes;

    @Min(value = 0L, message = "Número do Imóvel da Instituição deve ser preenchido com um valor inteiro ou deixe em branco")
    private Integer numImovel;
    
    @Column(length = 50)
    @NotBlank(message = "Rua da Instituição deve ser preenchido")
    @Size(min = 2, max = 50, message = "Rua da Instituição deve ter entre 2 e 50 letras")
    private String rua;

    @NotNull(message = "Bairro da Instituição deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "cdBairro")
    private Bairro bairro;
    
    @Builder
    public Instituicao(String cnpj, Integer limiteDoacoes, Integer numImovel, String rua, Bairro bairro, String nome, String login, String senha){
    	super(null, nome, login, senha, null);
    	this.cnpj = cnpj;
        this.limiteDoacoes = limiteDoacoes;
        this.numImovel = numImovel;
        this.rua = rua;
        this.bairro = bairro;
    }

}
