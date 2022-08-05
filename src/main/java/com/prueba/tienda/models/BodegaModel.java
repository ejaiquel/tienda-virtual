package com.prueba.tienda.models;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="bodegas")
public class BodegaModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "bodega_id")
    private Long id;

    @Getter @Setter @Column(name = "empresa_id")
    private Long empresaId;
    
    @Getter @Setter @Column(name = "descripcion")
    private String descripcion;

    @Getter @Setter @Column(name = "ubicacion")
    private String ubicacion;

    @Getter @Setter @Column(name = "imagen")
    private String imagen;

    @Getter @Setter @Column(name = "estado_activo")
    private boolean estadoActivo;
    
    @Getter @Setter @Column(name = "usuario_id")
    private Integer usuarioId;

    @Getter @Setter @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Getter @Setter @Column(name = "fecha_edicion")
    private Date fechaEdicion;

}