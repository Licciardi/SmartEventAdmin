package br.com.fiap.SmartEvent.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tb_evento")
public class Evento {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo requerido")
    @Size(min = 2, message = "o nome do evento deve conter no minimo 2 caracteres")
    private String nome;

    @NotBlank(message = "Campo requerido")
    private String data;

    @NotBlank(message = "Campo requerido")
    private String url;


}

