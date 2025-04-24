CREATE TABLE etablissement (
   id_etb NUMBER(19) GENERATED ALWAYS AS IDENTITY,
   adresse VARCHAR2(255),
   city VARCHAR2(255),
   name VARCHAR2(255),
   CONSTRAINT pk_etablissement PRIMARY KEY (id_etb)
);

CREATE TABLE pdf_file (
  id VARCHAR2(255) NOT NULL,
  data BLOB,
  name VARCHAR2(255),
  CONSTRAINT pk_pdf_file PRIMARY KEY (id)
);

CREATE TABLE prestataire (
     id_pres NUMBER(19) GENERATED ALWAYS AS IDENTITY,
     email VARCHAR2(255),
     raison_social VARCHAR2(255),
     telephone VARCHAR2(255),
     CONSTRAINT pk_prestataire PRIMARY KEY (id_pres)
);

CREATE TABLE proprietaire (
  id_prop NUMBER(19) GENERATED ALWAYS AS IDENTITY,
  email VARCHAR2(255),
  first_name VARCHAR2(255),
  last_name VARCHAR2(255),
  telephone VARCHAR2(255),
  CONSTRAINT pk_proprietaire PRIMARY KEY (id_prop)
);

CREATE TABLE type_materiel (
    id_type_mat NUMBER(19) GENERATED ALWAYS AS IDENTITY,
    name VARCHAR2(255),
    CONSTRAINT pk_type_materiel PRIMARY KEY (id_type_mat)
);

CREATE TABLE materiel (
  id_mat NUMBER(19) GENERATED ALWAYS AS IDENTITY,
  model VARCHAR2(255),
  quantity NUMBER(10) NOT NULL,
  id_type_mat NUMBER(19) CONSTRAINT fk_materiel_type REFERENCES type_materiel(id_type_mat),
  CONSTRAINT pk_materiel PRIMARY KEY (id_mat)
);

CREATE TABLE commande (
  id_cmd NUMBER(19) GENERATED ALWAYS AS IDENTITY,
  date DATE,
  num_bon_cmd VARCHAR2(255),
  quantity NUMBER(10) NOT NULL,
  status VARCHAR2(20) CONSTRAINT chk_commande_status CHECK (status IN ('CREATED', 'PENDING', 'DELIVERED', 'CANCELED')),
  bon_cmd_id VARCHAR2(255) CONSTRAINT uk_bon_cmd UNIQUE CONSTRAINT fk_commande_pdf REFERENCES pdf_file(id),
  id_etb NUMBER(19) CONSTRAINT fk_commande_etablissement REFERENCES etablissement(id_etb),
  id_mat NUMBER(19) CONSTRAINT fk_commande_materiel REFERENCES materiel(id_mat),
  id_pres NUMBER(19) CONSTRAINT fk_commande_prestataire REFERENCES prestataire(id_pres),
  CONSTRAINT pk_commande PRIMARY KEY (id_cmd)
);

CREATE TABLE livraison (
   id_liv NUMBER(19) GENERATED ALWAYS AS IDENTITY,
   date DATE,
   num_bon_liv VARCHAR2(255),
   quantity NUMBER(10) NOT NULL,
   bon_liv_id VARCHAR2(255) CONSTRAINT uk_bon_liv UNIQUE CONSTRAINT fk_livraison_pdf REFERENCES pdf_file(id),
   id_cmd NUMBER(19) CONSTRAINT fk_livraison_commande REFERENCES commande(id_cmd),
   CONSTRAINT pk_livraison PRIMARY KEY (id_liv)
);

CREATE TABLE materiel_detail (
     id_mat_det NUMBER(19) GENERATED ALWAYS AS IDENTITY,
     inventaire_cih VARCHAR2(255),
     num_serie VARCHAR2(255),
     usage_count NUMBER(10) NOT NULL,
     id_mat NUMBER(19) CONSTRAINT fk_materiel_detail_materiel REFERENCES materiel(id_mat),
     CONSTRAINT pk_materiel_detail PRIMARY KEY (id_mat_det)
);

CREATE TABLE affectation (
     id_aff NUMBER(19) GENERATED ALWAYS AS IDENTITY,
     date DATE,
     motif VARCHAR2(255),
     id_mat_det NUMBER(19) CONSTRAINT fk_affectation_materiel_det REFERENCES materiel_detail(id_mat_det),
     id_prop NUMBER(19) CONSTRAINT fk_affectation_proprietaire REFERENCES proprietaire(id_prop),
     CONSTRAINT pk_affectation PRIMARY KEY (id_aff)
);