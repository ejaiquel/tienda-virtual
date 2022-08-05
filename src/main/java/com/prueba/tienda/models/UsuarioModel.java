package com.prueba.tienda.models;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="usuarios")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "usuario_id")
    private Long id;
    
    @Getter @Setter @Column(name = "codigo")
    private String codigo;

    @Getter @Setter @Column(name = "identificacion")
    private String identificacion;

    @Getter @Setter @Column(name = "nombres")
    private String nombres;

    @Getter @Setter @Column(name = "apellidos")
    private String apellidos;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "telefono")
    private String telefono;

    @Getter @Setter @Column(name = "password")
    private String password;

    @Getter @Setter @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Getter @Setter @Column(name = "fecha_edicion")
    private Date fechaEdicion;

}