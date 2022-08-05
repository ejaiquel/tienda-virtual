/* Para Resetear la base
	drop table if exists "public".ventas_productos;
	drop table if exists "public".ventas;
	drop table if EXISTS "public".tipo_pagos;
	drop table if EXISTS "public".movimiento_bodegas;
	drop table if EXISTS "public".bodegas;
	drop table if EXISTS "public".productos;
	drop table if EXISTS "public".sub_categorias;
	drop table if EXISTS "public".categorias;
	drop table if EXISTS "public".marcas;
	drop table if EXISTS "public".empresas;
	drop table if EXISTS "public".usuarios;
*/

/**Tabla de Usuarios se tiene el login **/
CREATE TABLE IF NOT EXISTS "public".usuarios(
	usuario_id serial not null,
	codigo varchar(25) not null,
	email varchar(100) not null,
	nombres varchar(150) not null,
	apellidos varchar(150),	
	telefono1 varchar(50),
	es_super boolean,
	estado_activo boolean default true,
	fecha_creacion timestamp null default current_timestamp,
	fecha_edicion timestamp null,
	CONSTRAINT PK_usuarios PRIMARY KEY(usuario_id)
);

CREATE UNIQUE INDEX IX_usuarios_email ON "public".usuarios(email);


 /**Tabla de empresas la tienda puede estar configurada para multiples empresas **/
CREATE TABLE IF NOT EXISTS "public".empresas(
	empresa_id serial not null,
	nit varchar(50) not null,
	razon_social varchar(250) not null,
	descripicion varchar(250),
	email varchar(100),	
	telefono1 varchar(50),
	telefono2 varchar(50),
	estado_activo boolean default true,
	imagen_logo varchar(10485760),
	usuario_id int not null,
	fecha_creacion timestamp null default current_timestamp,
	fecha_edicion timestamp null,
	CONSTRAINT PK_empresas PRIMARY KEY(empresa_id)
);

CREATE UNIQUE INDEX IX_empresas_nit ON "public".empresas(nit);  --no permite agregar el mismo nit 
ALTER TABLE "public".empresas ADD CONSTRAINT FK_empresas_usuarios FOREIGN KEY(usuario_id) REFERENCES "public".usuarios(usuario_id); --Integridad Referencial 


/**Tabla de marcas **/
CREATE TABLE IF NOT EXISTS "public".marcas(
	marca_id serial not null,
	empresa_id int not null,
	marca varchar(250) not null,
	descripcion varchar(500),
	imagen_logo varchar(10485760),
	estado_activo boolean default true,
	usuario_id int not null,
	fecha_creacion timestamp null default current_timestamp,
	fecha_edicion timestamp null,
	CONSTRAINT PK_marcas PRIMARY KEY(marca_id)
);

ALTER TABLE "public".marcas ADD CONSTRAINT FK_marcas_empresas FOREIGN KEY(empresa_id) REFERENCES "public".empresas(empresa_id);
ALTER TABLE "public".marcas ADD CONSTRAINT FK_marcas_usuarios FOREIGN KEY(usuario_id) REFERENCES "public".usuarios(usuario_id);


/**Tabla de categorias **/
CREATE TABLE IF NOT EXISTS "public".categorias(
	categoria_id serial not null,
	empresa_id int not null,
	descripcion varchar(500),
	abreviatura varchar(20),		
	imagen varchar(10485760),
	estado_activo boolean default true,
	usuario_id int not null,
	fecha_creacion timestamp null default current_timestamp,
	fecha_edicion timestamp null,
	CONSTRAINT PK_categorias PRIMARY KEY(categoria_id)
);

ALTER TABLE "public".categorias ADD CONSTRAINT FK_categorias_empresas FOREIGN KEY(empresa_id) REFERENCES "public".empresas(empresa_id);
ALTER TABLE "public".categorias ADD CONSTRAINT FK_categorias_usuarios FOREIGN KEY(usuario_id) REFERENCES "public".usuarios(usuario_id);


/**Tabla de categorias **/
CREATE TABLE IF NOT EXISTS "public".sub_categorias(
	sub_categoria_id serial not null,
	categoria_id int not null,
	descripcion varchar(500),
	abreviatura varchar(20),		
	imagen varchar(10485760),
	estado_activo boolean default true,
	usuario_id int not null,
	fecha_creacion timestamp null default current_timestamp,
	fecha_edicion timestamp null,
	CONSTRAINT PK_sub_categorias PRIMARY KEY(sub_categoria_id)
);

ALTER TABLE "public".sub_categorias ADD CONSTRAINT FK_sub_categorias_categorias FOREIGN KEY(categoria_id) REFERENCES "public".categorias(categoria_id);
ALTER TABLE "public".sub_categorias ADD CONSTRAINT FK_sub_categorias_usuarios FOREIGN KEY(usuario_id) REFERENCES "public".usuarios(usuario_id);

/**Tabla de productos **/
CREATE TABLE IF NOT EXISTS "public".productos(
	producto_id serial not null,
	codigo varchar(50) not null,
	empresa_id int not null,
	marca_id int not null,
	sub_categoria_id int not null,
	descripcion varchar(500) not null,	
	especificaciones varchar(5000),
	valor decimal,
	imagen varchar(2000),
	cantidad_stock int,
	estado_activo boolean default true,
	usuario_id int not null,
	fecha_creacion timestamp null default current_timestamp,
	fecha_edicion timestamp null,
	CONSTRAINT PK_productos PRIMARY KEY(producto_id)
);

CREATE UNIQUE INDEX IX_productos_codigo_empresa_id ON "public".productos(empresa_id, codigo); --Constraint para no permitir el mismo producto en la empresa 

ALTER TABLE "public".productos ADD CONSTRAINT FK_productos_empresas FOREIGN KEY(empresa_id) REFERENCES "public".empresas(empresa_id);
ALTER TABLE "public".productos ADD CONSTRAINT FK_productos_marcas FOREIGN KEY(marca_id) REFERENCES "public".marcas(marca_id);
ALTER TABLE "public".productos ADD CONSTRAINT FK_productos_usuarios FOREIGN KEY(usuario_id) REFERENCES "public".usuarios(usuario_id);
ALTER TABLE "public".productos ADD CONSTRAINT FK_productos_sub_categorias FOREIGN KEY(sub_categoria_id) REFERENCES "public".sub_categorias(sub_categoria_id);


/**Tabla de bodegas para crear los putos de ventas ejemplo Ktronix Titan Plaza **/
CREATE TABLE IF NOT EXISTS "public".bodegas(
	bodega_id serial not null,	
	empresa_id int not null,
	descripcion varchar(500) not null,	
	ubicacion varchar(500) not null,
	estado_activo boolean default true,
	usuario_id int not null,
	fecha_creacion timestamp null default current_timestamp,
	fecha_edicion timestamp null,
	CONSTRAINT PK_bodegas PRIMARY KEY(bodega_id)
);

ALTER TABLE "public".bodegas ADD CONSTRAINT FK_bodegas_empresas FOREIGN KEY(empresa_id) REFERENCES "public".empresas(empresa_id);

insert into "public".bodegas (empresa_id, descripcion, ubicacion , usuario_id) select 1, 'Bodega Ventas Virtual','Bogota D.C.',1;
insert into "public".bodegas (empresa_id, descripcion, ubicacion , usuario_id) select 1, 'Bodega Centro Comercial Portal 80','Bogota D.C. Portal 80',1;
insert into "public".bodegas (empresa_id, descripcion, ubicacion , usuario_id) select 1, 'Bodega Centro Tienda Calle 13','Bogota D.C. Centro Calle 13',1;


/**Tabla de transacciones de bodega S= Salida por venta, E= Entrda por devolución o ingreso de existencias **/
CREATE TABLE IF NOT EXISTS "public".movimiento_bodegas(
	mov_bodega_id serial not null,	
	bodega_id int not null,	
	tipo_movimiento varchar(1) not null,			--E= Entrada, S=Salida	
	descripcion varchar(500) not null,	
	cantidad int not null,	
	usuario_id int not null,
	fecha_creacion timestamp null default current_timestamp,
	fecha_edicion timestamp null,
	CONSTRAINT PK_movimiento_bodegas PRIMARY KEY(mov_bodega_id)
);

ALTER TABLE "public".movimiento_bodegas ADD CONSTRAINT FK_movimiento_bodegas_bodegas FOREIGN KEY(bodega_id) REFERENCES "public".bodegas(bodega_id);

/**Cataologos para tipos de pagos **/
CREATE TABLE IF NOT EXISTS "public".tipo_pagos(
	tipo_pago_id serial not null,
	empresa_id int not null,
	descripcion varchar(500) not null,
	abreviatura varchar(4),
	usuario_id int not null,
	fecha_creacion timestamp null default current_timestamp,
	fecha_edicion timestamp null,
	CONSTRAINT PK_tipo_pagos PRIMARY KEY(tipo_pago_id)
);

ALTER TABLE "public".tipo_pagos ADD CONSTRAINT FK_tipo_pagos_empresas FOREIGN KEY(empresa_id) REFERENCES "public".empresas(empresa_id);


/**Cataologo encabezado para registrar las ventas, por ejemplo lo adquirido en el carro de compras**/
CREATE TABLE IF NOT EXISTS "public".ventas(			
	venta_id serial not null,		
	usuario_id int not null,	
	tipo_pago_id int not null,	
	direccion_envio varchar(500),
	observaciones varchar(2000),
	fecha_creacion timestamp null default current_timestamp,
	fecha_edicion timestamp null,
	CONSTRAINT PK_ventas PRIMARY KEY(venta_id)
);

ALTER TABLE "public".ventas ADD CONSTRAINT FK_ventas_tipo_pagos FOREIGN KEY(tipo_pago_id) REFERENCES "public".tipo_pagos(tipo_pago_id);
ALTER TABLE "public".ventas ADD CONSTRAINT FK_ventas_usuarios FOREIGN KEY(usuario_id) REFERENCES "public".usuarios(usuario_id);


/**Cataologo detalle para registrar las ventas, por ejemplo lo adquirido en el carro de compras**/
CREATE TABLE IF NOT EXISTS "public".ventas_productos(			
	venta_producto_id serial not null,			
	venta_id int not null,
	bodega_id int not null,
	producto_id int not null,	
	cantidad int not null,
	usuario_id int not null,		
	fecha_creacion timestamp null default current_timestamp,
	fecha_edicion timestamp null,
	CONSTRAINT PK_ventas_productos PRIMARY KEY(venta_producto_id)
);

ALTER TABLE "public".ventas_productos ADD CONSTRAINT FK_ventas_productos_ventas FOREIGN KEY(venta_id) REFERENCES "public".ventas(venta_id);
ALTER TABLE "public".ventas_productos ADD CONSTRAINT FK_ventas_productos_bodegas FOREIGN KEY(bodega_id) REFERENCES "public".bodegas(bodega_id);
ALTER TABLE "public".ventas_productos ADD CONSTRAINT FK_ventas_productos_productos FOREIGN KEY(producto_id) REFERENCES "public".productos(producto_id);
ALTER TABLE "public".ventas_productos ADD CONSTRAINT FK_ventas_productos_usuarios FOREIGN KEY(usuario_id) REFERENCES "public".usuarios(usuario_id);


/***Inserts y configuracion parametros ****/

insert into "public".usuarios(codigo, email, nombres, apellidos,telefono1, es_super) select 'ejaiquel', 'ejaiquel@gmail.com', 'EDWIN ALEXANDER','JAIQUEL GONGORA','3012766819',true;
insert into "public".empresas(nit, razon_social, descripicion, email, usuario_id ) select '11222014','Linio Demo','tienda virtual tecnologica','ventas@linio.com.co', 1;


insert into "public".marcas (empresa_id, marca, descripcion, usuario_id) select 1, 'SAMSUNG','Tecnologia', 1;
insert into "public".marcas (empresa_id, marca, descripcion, usuario_id) select 1, 'LG','Tecnologia', 1;
insert into "public".marcas (empresa_id, marca, descripcion, usuario_id) select 1, 'HISENSE','Tecnologia', 1;
insert into "public".marcas (empresa_id, marca, descripcion, usuario_id) select 1, 'XIAOMI','Tecnologia', 1;
insert into "public".marcas (empresa_id, marca, descripcion, usuario_id) select 1, 'TCL','Tecnologia', 1;
insert into "public".marcas (empresa_id, marca, descripcion, usuario_id) select 1, 'ASUS','Tecnologia', 1;


insert into "public".categorias (empresa_id, descripcion, abreviatura, usuario_id) select 1, 'Televisores','TV',1;
insert into "public".categorias (empresa_id, descripcion, abreviatura, usuario_id) select 1, 'Computadores','PCS',1;
insert into "public".categorias (empresa_id, descripcion, abreviatura, usuario_id) select 1, 'Enfriamiento','NEV',1;
insert into "public".categorias (empresa_id, descripcion, abreviatura, usuario_id) select 1, 'Sonido','',1;


insert into "public".sub_categorias (categoria_id, descripcion, abreviatura, usuario_id) select 1, 'Smart TV','',1;
insert into "public".sub_categorias (categoria_id, descripcion, abreviatura, usuario_id) select 1, 'LED','',1;
insert into "public".sub_categorias (categoria_id, descripcion, abreviatura, usuario_id) select 1, 'OLED','',1;
insert into "public".sub_categorias (categoria_id, descripcion, abreviatura, usuario_id) select 1, 'QLED','',1;
insert into "public".sub_categorias (categoria_id, descripcion, abreviatura, usuario_id) select 2, 'Portatiles','Notebook',1;
insert into "public".sub_categorias (categoria_id, descripcion, abreviatura, usuario_id) select 2, 'PC de Escritorio','Desktop',1;
insert into "public".sub_categorias (categoria_id, descripcion, abreviatura, usuario_id) select 3, 'Neveras','',1;
insert into "public".sub_categorias (categoria_id, descripcion, abreviatura, usuario_id) select 3, 'Nevecones','',1;
insert into "public".sub_categorias (categoria_id, descripcion, abreviatura, usuario_id) select 4, 'Barra de Sonido','',1;
insert into "public".sub_categorias (categoria_id, descripcion, abreviatura, usuario_id) select 4, 'Equipos de Sonido','',1;
insert into "public".sub_categorias (categoria_id, descripcion, abreviatura, usuario_id) select 4, 'Paralantes Portatiles','',1;


insert into "public".productos(codigo, empresa_id, marca_id, sub_categoria_id, descripcion, valor, imagen, cantidad_stock, usuario_id) 
select '55H6000FY', 1, 3, 1,'Televisor Hisense 55 pulgadas 4K UHD Smart TV'::varchar,1699000,'https://http2.mlstatic.com/D_NQ_NP_779752-MLA48624351835_122021-F.webp'::varchar,5,1;

insert into "public".productos(codigo, empresa_id, marca_id, sub_categoria_id, descripcion, valor, imagen, cantidad_stock, usuario_id) 
select '70A6G', 1, 3, 1,'Smart TV Hisense A6G Series 70A6G LCD 4K 70" 120V'::varchar,2599000,'https://http2.mlstatic.com/D_NQ_NP_813706-MLA49591693970_042022-V.webp'::varchar,5,1;


insert into "public".productos(codigo, empresa_id, marca_id, sub_categoria_id, descripcion, valor, imagen, cantidad_stock, usuario_id) 
select 'QS55H6000FY', 1, 1, 1,'Televisor SAMSUNG 55 pulgadas 4K UHD Smart TV'::varchar,2199000,'https://http2.mlstatic.com/D_NQ_NP_769801-MLA46796527976_072021-V.webp'::varchar,5,1;

insert into "public".productos(codigo, empresa_id, marca_id, sub_categoria_id, descripcion, valor, imagen, cantidad_stock, usuario_id) 
select '6GQS5H60FO', 1, 1, 1,'Smart TV SAMSUNG 70A6G LCD 4K 70" 120V'::varchar,1710000,'https://http2.mlstatic.com/D_NQ_NP_769801-MLA46796527976_072021-V.webp'::varchar,5,1;

insert into "public".productos(codigo, empresa_id, marca_id, sub_categoria_id, descripcion, valor, imagen, cantidad_stock, usuario_id) 
select 'UGQS5H60FO', 1, 1, 1,'Smart TV SAMSUNG 55 LCD 4K 120V'::varchar,2399000,'https://http2.mlstatic.com/D_NQ_NP_769801-MLA46796527976_072021-V.webp'::varchar,5,1;


insert into "public".tipo_pagos (empresa_id, descripcion, abreviatura, usuario_id) select 1, 'Efectivo', 'E', 1;
insert into "public".tipo_pagos (empresa_id, descripcion, abreviatura, usuario_id) select 1, 'Tarjeta Debito', 'TD',1;
insert into "public".tipo_pagos (empresa_id, descripcion, abreviatura, usuario_id) select 1, 'Tarjeta Credito', 'TC',1;
insert into "public".tipo_pagos (empresa_id, descripcion, abreviatura, usuario_id) select 1, 'Transferencia Nequi', 'NQ',1;
insert into "public".tipo_pagos (empresa_id, descripcion, abreviatura, usuario_id) select 1, 'Transferencia Daviplata', 'DP',1;
