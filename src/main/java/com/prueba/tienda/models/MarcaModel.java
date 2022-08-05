package com.prueba.tienda.models;

import java.util.Date;

import javax.persistence.*;
import javax.persistence.Entity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="marcas")
public class MarcaModel {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "marca_id")
    private Long id;

    @Getter @Setter @Column(name = "empresa_id")
    private Long empresaId;
    
    @Getter @Setter @Column(name = "marca")
    private String marca;

    @Getter @Setter @Column(name = "descripcion")
    private String descripcion;

    @Getter @Setter @Column(name = "imagen_logo")
    private String imagenLogo;

    @Getter @Setter @Column(name = "estado_activo")
    private boolean estadoActivo;
    
    @Getter @Setter @Column(name = "usuario_id")
    private Integer usuarioId;

    @Getter @Setter @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Getter @Setter @Column(name = "fecha_edicion")
    private Date fechaEdicion;

}