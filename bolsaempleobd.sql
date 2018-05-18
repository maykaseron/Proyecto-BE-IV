CREATE DATABASE BOLSAEMPLEO;
USE BOLSAEMPLEO;

CREATE TABLE EMPRESA (
        nombreEmp varchar(20) not null, /* el not null es para evitar q no ingresen datos null*/
        ubicacionEmp varchar(60) not null,
       /* latitud varchar (20) ,
        longitud varchar (20) ,*/
        descripcionEmp varchar(20) not null,
        correoEmp varchar(20) not null,
        telefono varchar(20)not null,
		contrasena varchar(10) not null,
        idEmp  int AUTO_INCREMENT,
        CONSTRAINT PK_EMP PRIMARY KEY (idEmp,nombreEmp)
		
);

CREATE TABLE OFERENTE (
        cedulaOferente varchar (15),
        nombreOferente varchar(10),
        primerApellido varchar(10),
        celular varchar(12),
        nacionalidad varchar (10),
        correoOferente varchar(15),
        ubicacion varchar(60),
		contrasena varchar(10) not null,
 
  CONSTRAINT PK_OFERENTE PRIMARY KEY (cedulaOferente)
);

CREATE TABLE CARACTERISTICAS (
        areaTrabajo varchar(20),
        especializacion varchar (10),
		
		idCaracteristica integer auto_increment not null,
		idPadre integer,
		habilidad varchar(20),
		ifPadre boolean,
		habilitado boolean,
		
        CONSTRAINT PK_Caracteriticas PRIMARY KEY (idCAracteristica)
);

CREATE TABLE PUESTOS (
        idPuesto int AUTO_INCREMENT,
        idEmp int,
        nombrePuesto varchar(20),
        salario float,
        descripcionPuesto varchar(10) , 
        tipoPublicacion boolean,

        CONSTRAINT PK_PUESTO PRIMARY KEY(idPuesto)
);

CREATE TABLE CARACTERISTICAS_PUESTOS (  /* CARACTERISTICAS_INCLUIDOS se llamaba así */
        consecutivo integer auto_increment not null,
        valor int,
		fecha_Inclusion date,
		idPuesto int(10),
        idCaracteristica int(10),
       

        CONSTRAINT PK_CARAC_PUESTOS PRIMARY KEY(consecutivo)
);

insert into EMPRESA (nombreEmp,ubicacionEmp,descripcionEmp,correoEmp,telefono,contrasena) values ('amazon','San jose','Oportunidades','ama@gm.com','86546','1');
insert into EMPRESA (nombreEmp,ubicacionEmp,descripcionEmp,correoEmp,telefono,contrasena) values ('dhl','San jose','Nada Particular','dhl@gm.com','5789','1');
insert into EMPRESA (nombreEmp,ubicacionEmp,descripcionEmp,correoEmp,telefono,contrasena) values ('Pali','San jose','Oportunidades','pp','86546','1');
insert into OFERENTE (cedulaOferente,nombreOferente ,primerApellido ,celular,nacionalidad,correoOferente,ubicacion,contrasena) values ('111','Jacinto','BuenaVen','8888','Nicaragua','a','PriCrater','1');

insert into PUESTOS (nombrePuesto, salario, descripcionPuesto,tipoPublicacion,idEmp) values ('Progra', 100, 'desa pro', true, 1);
insert into PUESTOS (nombrePuesto, salario, descripcionPuesto,tipoPublicacion,idEmp) values ('Desa Web ', 200, 'Cretividad', true, 1);
insert into PUESTOS (nombrePuesto, salario, descripcionPuesto,tipoPublicacion,idEmp) values ('Pro Apli', 300, 'Social', true, 1);
insert into PUESTOS (nombrePuesto, salario, descripcionPuesto,tipoPublicacion,idEmp) values ('Pro Web' , 200, 'progra', true, 2);
insert into PUESTOS (nombrePuesto, salario, descripcionPuesto,tipoPublicacion,idEmp) values ('Miner Dts', 50, 'progra', true, 2);

/* antes 
insert into CARACTERISTICAS (areaTrabajo, especializacion) values ('Programacion','Java');
insert into CARACTERISTICAS (areaTrabajo, especializacion) values ('Programacion','HTML');
insert into CARACTERISTICAS (areaTrabajo, especializacion) values ('Programacion','C++');
insert into CARACTERISTICAS (areaTrabajo, especializacion) values ('Idiomas','Ingles');
insert into CARACTERISTICAS (areaTrabajo, especializacion) values ('Tec Salud','Doctor');
insert into CARACTERISTICAS (areaTrabajo, especializacion) values ('Chef','Artesanal');
*/
/*  DESPUES */
insert into CARACTERISTICAS (habilidad, idPadre,ifPadre,habilitado) values ('Ing Siste',null,true,false);
insert into CARACTERISTICAS (habilidad, idPadre,ifPadre,habilitado) values ('Len Progra',1,false,false); /*  2 */
insert into CARACTERISTICAS (habilidad, idPadre,ifPadre,habilitado) values ('HTML',2,false,true);
insert into CARACTERISTICAS (habilidad, idPadre,ifPadre,habilitado) values ('C++',2,false,true);  /* 4 */
insert into CARACTERISTICAS (habilidad, idPadre,ifPadre,habilitado) values ('Java',2,false,false);
insert into CARACTERISTICAS (habilidad, idPadre,ifPadre,habilitado) values ('Datos',1,false,false);  /*  6  */
insert into CARACTERISTICAS (habilidad, idPadre,ifPadre,habilitado) values ('Desarro',6,false,true);
insert into CARACTERISTICAS (habilidad, idPadre,ifPadre,habilitado) values ('Admi',6,false,true);   /*   8    */

insert into CARACTERISTICAS (habilidad, idPadre,ifPadre,habilitado) values ('Cocina saludable',null,true,false);
insert into CARACTERISTICAS (habilidad, idPadre,ifPadre,habilitado) values ('Artesanal',9,false,true); /*  10 */ 
insert into CARACTERISTICAS (habilidad, idPadre,ifPadre,habilitado) values ('Tropical',9,false,true);

insert into CARACTERISTICAS (habilidad, idPadre,ifPadre,habilitado) values ('Java Web',5,false,true);



insert into bolsaempleo.CARACTERISTICAS_PUESTOS (idPuesto,idCaracteristica, valor ) values (1,4,80);
insert into bolsaempleo.CARACTERISTICAS_PUESTOS (idPuesto,idCaracteristica, valor ) values (2,3,80);
insert into bolsaempleo.CARACTERISTICAS_PUESTOS (idPuesto,idCaracteristica, valor ) values (2,5,90);
insert into bolsaempleo.CARACTERISTICAS_PUESTOS (idPuesto,idCaracteristica, valor ) values (3,4,95);
insert into bolsaempleo.CARACTERISTICAS_PUESTOS (idPuesto,idCaracteristica, valor ) values (4,3,90);
insert into bolsaempleo.CARACTERISTICAS_PUESTOS (idPuesto,idCaracteristica, valor ) values (5,8,80);
insert into bolsaempleo.CARACTERISTICAS_PUESTOS (idPuesto,idCaracteristica, valor ) values (5,7,50);

/*
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

CREATE TABLE HABILIDADES_INCLUIDAS (    
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
		nombreEmp varchar(20) not null,*/ /* el not null es para evitar q no ingresen datos null*/
		/*ubicacionEmp varchar(60) not null,
		latitud varchar (20) ,
		longitud varchar (20) ,
		descripcionEmp varchar(20) not null,
		correoEmp varchar(20) not null,
		telefono varchar(20)not null,
		idEmp  int AUTO_INCREMENT,
*/
		/*
		fechaRegistro date,
		*/
/*		CONSTRAINT PK_EMP PRIMARY KEY (idEmp)
);


CREATE TABLE PUESTOS (
		nombrePuesto varchar(10),
		salario float,
		descripcionPuesto varchar(10) , 
		tipoPublicacion boolean,
		idPuesto int AUTO_INCREMENT,

		CONSTRAINT PK_PUESTO PRIMARY KEY(idPuesto)
);

CREATE TABLE CARACTERISTICAS (
	   idCaracteristica varchar(5) ,
	   areaTrabajo varchar(20),
	   especializacion varchar (10),
	   
	   CONSTRAINT PK_Caracteriticas PRIMARY KEY (idCAracteristica)
);

CREATE TABLE CARACTERISTICAS_INCLUIDOS (    
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
*/
