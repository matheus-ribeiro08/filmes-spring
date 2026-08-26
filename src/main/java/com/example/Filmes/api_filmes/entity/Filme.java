package com.example.Filmes.api_filmes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "filme")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "filme_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "titulo", nullable = false, length = 255)
    private String titulo;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "ano_de_lancamento")
    private Integer anoDeLancamento;

    @ManyToOne
    @JoinColumn(name = "idioma_id", nullable = false)
    private Idioma idioma;

    @ManyToOne
    @JoinColumn(name = "idioma_original_id")
    private Idioma idiomaOriginal;

    @Column(name = "duracao_da_locacao", nullable = false)
    private int duracaoLocacao;

    @Column(name = "preco_da_locacao", nullable = false)
    private double precoLocacao;

    @Column(name = "duracao_do_filme")
    private Integer duracaoFilme;

    @Column(name = "custo_de_substituicao", nullable = false)
    private double custoDeSubstituicao;

    @Column(name = "classificacao")
    private String classificacao;

    @Column(name = "recursos_especiais")
    private String recursosEspeciais;

    @UpdateTimestamp
    @Column(name = "ultima_atualizacao", nullable = false)
    private LocalDateTime ultimaAtualizacao;
}