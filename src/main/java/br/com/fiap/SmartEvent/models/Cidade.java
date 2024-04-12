package br.com.fiap.SmartEvent.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_cidade")
public class Cidade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 2, message = "o nome do evento deve conter no minimo 2 caracteres")
    private String nome;

    @NotBlank(message = "Campo requerido")
    @Size(min = 2, message = "o nome do evento deve conter no minimo 2 caracteres")
    private String estado;

    @OneToMany(mappedBy = "cidade")
    private List<Evento> eventos = new ArrayList<>();



}