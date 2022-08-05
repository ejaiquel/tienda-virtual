package com.prueba.tienda.models;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="empresas")
public class EmpresaModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "empresa_id")
    private Long id;
    
    @Getter @Setter @Column(name = "nit")
    private String nit;

    @Getter @Setter @Column(name = "razon_social")
    private String razonSocial;

    @Getter @Setter @Column(name = "descripicion")
    private String descripicion;

    @Getter @Setter @Column(name = "email")
    private String email;

    @Getter @Setter @Column(name = "telefono1")
    private String telefono1;
    
    @Getter @Setter @Column(name = "telefono2")
    private String telefono2;

    @Getter @Setter @Column(name = "estado_activo")
    private boolean estadoActivo;

    @Getter @Setter @Column(name = "imagen_logo")
    private String imagenLogo;

    @Getter @Setter @Column(name = "usuario_id")
    private Integer usuarioId;

    @Getter @Setter @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Getter @Setter @Column(name = "fecha_edicion")
    private Date fechaEdicion;

}