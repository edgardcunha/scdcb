package edu.ifes.ci.si.les.scdcb.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.RandomStringUtils;
import lombok.*;
import edu.ifes.ci.si.les.scdcb.model.enums.StatusSolicitacao;

@Entity
@Table(name="solicitacoes")
@Data
@EqualsAndHashCode(of = {"cdSolicitacao"})
public class Solicitacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdSolicitacao;

    @Column(nullable = true, length = 8, unique = true, updatable = false)
    private String codigo;

    @Column(updatable = false)
    @NotNull(message = "Data da Solicitação deve ser preenchido")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dtSolicitacao;

    @NotNull(message = "Status da Solicitação deve ser preenchido")
    @Min(value = 0L, message = "Status da Solicitação deve ser preenchido com um valor inteiro")
    private Integer status;

    @NotNull(message = "Quantidade de Cestas deve ser preenchido")
    @Min(value = 0L, message = "Quantidade de Cestas deve ser no mínimo 1")
    private Integer qtdCestas;

    @NotNull(message = "Tipo de Cesta deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "cdTpCesta")
    private TipoDeCesta tpCesta;

    @NotNull(message = "Instituição deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "cdInstituicao")
    private Instituicao instituicao;

    @NotNull(message = "Beneficiado deve ser preenchido")
    @ManyToOne
    @JoinColumn(name = "cdBeneficiado")
    private Beneficiado beneficiado;

    @Builder
    public Solicitacao(LocalDate dtSolicitacao, Integer qtdCestas, TipoDeCesta tpCesta, StatusSolicitacao status, Instituicao instituicao, Beneficiado beneficiado) {
        super();
        this.codigo = gerarCodigo();
        this.dtSolicitacao = dtSolicitacao;
        this.qtdCestas = qtdCestas;
        this.status = (status == null) ? null : status.getCod();
        this.tpCesta = tpCesta;
        this.instituicao = instituicao;
        this.beneficiado = beneficiado;
    }

    @Builder
    public Solicitacao() { }

    public StatusSolicitacao getStatus() {
        return StatusSolicitacao.toEnum(status);
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status.getCod();
    }

    public String gerarCodigo() {
        return (this.codigo == null) ? RandomStringUtils.randomAlphanumeric(8) : null;
    }

}
