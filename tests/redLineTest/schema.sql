--
-- PostgreSQL database dump
--

-- Dumped from database version 9.4.5
-- Dumped by pg_dump version 9.4.5
-- Started on 2016-04-22 14:42:49 CEST

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 178 (class 3079 OID 11903)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2070 (class 0 OID 0)
-- Dependencies: 178
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 176 (class 1259 OID 16407)
-- Name: auteur; Type: TABLE; Schema: public; Owner: imiedev; Tablespace: 
--

CREATE TABLE auteur (
    id integer NOT NULL,
    lib character varying
);


ALTER TABLE auteur OWNER TO imiedev;

--
-- TOC entry 177 (class 1259 OID 16410)
-- Name: auteur_id_seq; Type: SEQUENCE; Schema: public; Owner: imiedev
--

CREATE SEQUENCE auteur_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE auteur_id_seq OWNER TO imiedev;

--
-- TOC entry 2071 (class 0 OID 0)
-- Dependencies: 177
-- Name: auteur_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: imiedev
--

ALTER SEQUENCE auteur_id_seq OWNED BY auteur.id;


--
-- TOC entry 174 (class 1259 OID 16395)
-- Name: exemplaire; Type: TABLE; Schema: public; Owner: imiedev; Tablespace: 
--

CREATE TABLE exemplaire (
    id integer NOT NULL,
    lib character varying,
    video_id integer
);


ALTER TABLE exemplaire OWNER TO imiedev;

--
-- TOC entry 175 (class 1259 OID 16398)
-- Name: exemplaire_id_seq; Type: SEQUENCE; Schema: public; Owner: imiedev
--

CREATE SEQUENCE exemplaire_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE exemplaire_id_seq OWNER TO imiedev;

--
-- TOC entry 2072 (class 0 OID 0)
-- Dependencies: 175
-- Name: exemplaire_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: imiedev
--

ALTER SEQUENCE exemplaire_id_seq OWNED BY exemplaire.id;


--
-- TOC entry 172 (class 1259 OID 16386)
-- Name: video; Type: TABLE; Schema: public; Owner: imiedev; Tablespace: 
--

CREATE TABLE video (
    id integer NOT NULL,
    libelle character varying(200),
    datesortie date,
    duree integer,
    auteur_id integer
);


ALTER TABLE video OWNER TO imiedev;

--
-- TOC entry 173 (class 1259 OID 16389)
-- Name: video_id_seq; Type: SEQUENCE; Schema: public; Owner: imiedev
--

CREATE SEQUENCE video_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE video_id_seq OWNER TO imiedev;

--
-- TOC entry 2073 (class 0 OID 0)
-- Dependencies: 173
-- Name: video_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: imiedev
--

ALTER SEQUENCE video_id_seq OWNED BY video.id;


--
-- TOC entry 1943 (class 2604 OID 16412)
-- Name: id; Type: DEFAULT; Schema: public; Owner: imiedev
--

ALTER TABLE ONLY auteur ALTER COLUMN id SET DEFAULT nextval('auteur_id_seq'::regclass);


--
-- TOC entry 1942 (class 2604 OID 16400)
-- Name: id; Type: DEFAULT; Schema: public; Owner: imiedev
--

ALTER TABLE ONLY exemplaire ALTER COLUMN id SET DEFAULT nextval('exemplaire_id_seq'::regclass);


--
-- TOC entry 1941 (class 2604 OID 16391)
-- Name: id; Type: DEFAULT; Schema: public; Owner: imiedev
--

ALTER TABLE ONLY video ALTER COLUMN id SET DEFAULT nextval('video_id_seq'::regclass);


--
-- TOC entry 1951 (class 2606 OID 16424)
-- Name: auteur_pkey; Type: CONSTRAINT; Schema: public; Owner: imiedev; Tablespace: 
--

ALTER TABLE ONLY auteur
    ADD CONSTRAINT auteur_pkey PRIMARY KEY (id);


--
-- TOC entry 1948 (class 2606 OID 16422)
-- Name: exemplaire_pkey; Type: CONSTRAINT; Schema: public; Owner: imiedev; Tablespace: 
--

ALTER TABLE ONLY exemplaire
    ADD CONSTRAINT exemplaire_pkey PRIMARY KEY (id);


--
-- TOC entry 1946 (class 2606 OID 16420)
-- Name: video_pkey; Type: CONSTRAINT; Schema: public; Owner: imiedev; Tablespace: 
--

ALTER TABLE ONLY video
    ADD CONSTRAINT video_pkey PRIMARY KEY (id);


--
-- TOC entry 1949 (class 1259 OID 16436)
-- Name: fki_exemplaire_video; Type: INDEX; Schema: public; Owner: imiedev; Tablespace: 
--

CREATE INDEX fki_exemplaire_video ON exemplaire USING btree (video_id);


--
-- TOC entry 1944 (class 1259 OID 16430)
-- Name: fki_video_auteur; Type: INDEX; Schema: public; Owner: imiedev; Tablespace: 
--

CREATE INDEX fki_video_auteur ON video USING btree (auteur_id);


--
-- TOC entry 1953 (class 2606 OID 16431)
-- Name: fk_exemplaire_video; Type: FK CONSTRAINT; Schema: public; Owner: imiedev
--

ALTER TABLE ONLY exemplaire
    ADD CONSTRAINT fk_exemplaire_video FOREIGN KEY (video_id) REFERENCES video(id);


--
-- TOC entry 1952 (class 2606 OID 16425)
-- Name: fk_video_auteur; Type: FK CONSTRAINT; Schema: public; Owner: imiedev
--

ALTER TABLE ONLY video
    ADD CONSTRAINT fk_video_auteur FOREIGN KEY (auteur_id) REFERENCES auteur(id);


--
-- TOC entry 2069 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2016-04-22 14:42:49 CEST

--
-- PostgreSQL database dump complete
--

