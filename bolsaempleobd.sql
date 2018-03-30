CREATE DATABASE BOLSAEMPLEO;
USE BOLSAEMPLEO;

CREATE TABLE HABILIDADES (
	   idHabilidad int AUTO_INCREMENT ,
	   nombreHabilidad varchar(10) ,
	   areaTrabajo varchar(20),
	   especializacion varchar (10),
   
   CONSTRAINT PK_Caracteriticas PRIMARY KEY ( idHabilidad , nombreHabilidad)
);

CREATE TABLE OFERENTE (
	  cedulaOferente varchar (15),
	  nombreOferente varchar(10),
	  primerApellido varchar(10),
	  celular varchar(12),
	  nacionalidad varchar (10),
	  correoOferente varchar(15),
	  ubicacion varchar(60),
 
  CONSTRAINT PK_OFERENTE PRIMARY KEY (cedulaOferente)
  );

CREATE TABLE SERVICIOS (
	   idServicio int AUTO_INCREMENT,
	   nombreServicio varchar(10),
	   salarioEsperado float,
	   descripcionDescripcion varchar(10) , 

CONSTRAINT PK_SERVICIO PRIMARY KEY(idServicio)
);


CREATE TABLE SERVICIOS_PUBLICADOS  (
	    cedulaOferente varchar (15),
	     idServicio int,
	    estadoServicio boolean,
	   
	    CONSTRAINT PK_SP PRIMARY KEY (idServicio , cedulaOferente),
	    CONSTRAINT FK_SP1 FOREIGN KEY (cedulaOferente) REFERENCES OFERENTE (cedulaOferente),
	    CONSTRAINT FK_SP2 FOREIGN KEY (idServicio) REFERENCES SERVICIOS (idServicio)
);

CREATE TABLE HABILIDADES_INCLUIDAS (    /**/
	   idServicio int,
	   idHabilidad int,
	   fecha_Inclusion date,

	   CONSTRAINT PK_PI PRIMARY KEY (idHabilidad, idServicio ),
	   CONSTRAINT FK_HI1 FOREIGN KEY (idServicio ) REFERENCES 
	   SERVICIOS (idServicio),
	   CONSTRAINT FK_HI2 FOREIGN KEY (idHabilidad ) REFERENCES 
	   HABILIDADES (idHabilidad),
);


CREATE TABLE EMPRESA (
		nombreEmp varchar(20) not null,
		ubicacionEmp varchar(60) not null,
		latitud varchar (20) ,
		longitud varchar (20) ,
		descripcionEmp varchar(20) not null,
		correoEmp varchar(20) not null,
		telefono varchar(20)not null,
		idEmp  int AUTO_INCREMENT,
		/*
		fechaRegistro date,
		*/
		CONSTRAINT PK_EMP PRIMARY KEY (idEmp)
);


CREATE TABLE PUESTOS (
		nombrePuesto varchar(10),
		salario float,
		 descripcionPuesto varchar(10) , 
		ubicacion varchar(10), 
		idPuesto int AUTO_INCREMENT,

		CONSTRAINT PK_PUESTO PRIMARY KEY(idPuesto)
);

CREATE TABLE CARACTERISTICAS (
	   idCaracteristica varchar(5) ,
	   areaTrabajo varchar(20),
	   especializacion varchar (10),
	   
	   CONSTRAINT PK_Caracteriticas PRIMARY KEY (idCAracteristica)
);

CREATE TABLE CARACTERISTICAS_INCLUIDOS (    /*		*/
	   idPuesto int,
	   idCaracteristica varchar(5),
	   fecha_Inclusion date,

	   CONSTRAINT PK_PI PRIMARY KEY (idPuesto, idCaracteristica ),
	   CONSTRAINT FK_PI1 FOREIGN KEY (idPuesto) REFERENCES 
	   PUESTOS (idPuesto),
	   CONSTRAINT FK_PI2 FOREIGN KEY (idCaracteristica) REFERENCES 
	   CARACTERISTICA (idCaracteristica)
);

CREATE TABLE PUESTOS_PUBLICADOS  (
	    idEmp int,
	    idPuesto int,
	    estadoPuesto boolean,
	   
	    CONSTRAINT PK_PP PRIMARY KEY (idPuesto , idEmp),
	    CONSTRAINT FK_PP1 FOREIGN KEY (idEmp) REFERENCES EMPRESA (idEmp),
	    CONSTRAINT FK_PP2 FOREIGN KEY (idPuesto) REFERENCES PUESTOS (idPuesto)
);

CREATE TABLE APLICADO  (
	  fechaAplicacion date,
	  cedulaOferente varchar (15),
	  idPuesto int,
	  
	  CONSTRAINT PK_AP PRIMARY KEY (cedulaOferente, idPuesto),
	  CONSTRAINT FK_AP1 FOREIGN KEY (cedulaOferente) REFERENCES OFERENTE (cedulaOferente),
	   CONSTRAINT FK_AP2 FOREIGN KEY (idPuesto) REFERENCES PUESTOS (idPuesto)
);

insert into EMPRESA (nombreEmp,ubicacionEmp,descripcionEmp,correoEmp,telefono) values ('amazon','San jose','nada','ama@gm.com','86546');
insert into OFERENTE (cedulaOferente,nombreOferente ,primerApellido ,celular,nacionalidad,correoOferente,ubicacion)   
	values ('111','Jacinto','BuenaVen','8888','Marte','jac@gm.com','PriCrater');


