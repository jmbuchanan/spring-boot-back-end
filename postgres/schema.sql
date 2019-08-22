--
-- PostgreSQL database dump
--

-- Dumped from database version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)
-- Dumped by pg_dump version 10.10 (Ubuntu 10.10-0ubuntu0.18.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: auctions; Type: TABLE; Schema: public; Owner: matt
--

CREATE TABLE public.auctions (
    auction_id integer,
    item_id integer,
    owner character varying(50),
    bid bigint,
    buyout bigint,
    quantity integer,
    date date
);


ALTER TABLE public.auctions OWNER TO matt;

--
-- Name: contents; Type: TABLE; Schema: public; Owner: matt
--

CREATE TABLE public.contents (
    id integer NOT NULL,
    paragraph_number integer,
    paragraph_text text,
    code_example text,
    image_pathway character varying(100),
    section_id integer NOT NULL
);


ALTER TABLE public.contents OWNER TO matt;

--
-- Name: contents_id_seq; Type: SEQUENCE; Schema: public; Owner: matt
--

CREATE SEQUENCE public.contents_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contents_id_seq OWNER TO matt;

--
-- Name: contents_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: matt
--

ALTER SEQUENCE public.contents_id_seq OWNED BY public.contents.id;


--
-- Name: contents_section_id_seq; Type: SEQUENCE; Schema: public; Owner: matt
--

CREATE SEQUENCE public.contents_section_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contents_section_id_seq OWNER TO matt;

--
-- Name: contents_section_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: matt
--

ALTER SEQUENCE public.contents_section_id_seq OWNED BY public.contents.section_id;


--
-- Name: items; Type: TABLE; Schema: public; Owner: matt
--

CREATE TABLE public.items (
    id integer,
    name character varying(100),
    category character varying(100)
);


ALTER TABLE public.items OWNER TO matt;

--
-- Name: sections; Type: TABLE; Schema: public; Owner: matt
--

CREATE TABLE public.sections (
    id integer NOT NULL,
    number real,
    title character varying(100),
    subject_id integer NOT NULL
);


ALTER TABLE public.sections OWNER TO matt;

--
-- Name: sections_id_seq; Type: SEQUENCE; Schema: public; Owner: matt
--

CREATE SEQUENCE public.sections_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sections_id_seq OWNER TO matt;

--
-- Name: sections_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: matt
--

ALTER SEQUENCE public.sections_id_seq OWNED BY public.sections.id;


--
-- Name: sections_subject_id_seq; Type: SEQUENCE; Schema: public; Owner: matt
--

CREATE SEQUENCE public.sections_subject_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sections_subject_id_seq OWNER TO matt;

--
-- Name: sections_subject_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: matt
--

ALTER SEQUENCE public.sections_subject_id_seq OWNED BY public.sections.subject_id;


--
-- Name: subjects; Type: TABLE; Schema: public; Owner: matt
--

CREATE TABLE public.subjects (
    id integer NOT NULL,
    title character varying(50)
);


ALTER TABLE public.subjects OWNER TO matt;

--
-- Name: subjects_id_seq; Type: SEQUENCE; Schema: public; Owner: matt
--

CREATE SEQUENCE public.subjects_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.subjects_id_seq OWNER TO matt;

--
-- Name: subjects_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: matt
--

ALTER SEQUENCE public.subjects_id_seq OWNED BY public.subjects.id;


--
-- Name: contents id; Type: DEFAULT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.contents ALTER COLUMN id SET DEFAULT nextval('public.contents_id_seq'::regclass);


--
-- Name: contents section_id; Type: DEFAULT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.contents ALTER COLUMN section_id SET DEFAULT nextval('public.contents_section_id_seq'::regclass);


--
-- Name: sections id; Type: DEFAULT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.sections ALTER COLUMN id SET DEFAULT nextval('public.sections_id_seq'::regclass);


--
-- Name: sections subject_id; Type: DEFAULT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.sections ALTER COLUMN subject_id SET DEFAULT nextval('public.sections_subject_id_seq'::regclass);


--
-- Name: subjects id; Type: DEFAULT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.subjects ALTER COLUMN id SET DEFAULT nextval('public.subjects_id_seq'::regclass);


--
-- Name: auctions auctions_auction_id_key; Type: CONSTRAINT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.auctions
    ADD CONSTRAINT auctions_auction_id_key UNIQUE (auction_id);


--
-- Name: contents contents_pkey; Type: CONSTRAINT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.contents
    ADD CONSTRAINT contents_pkey PRIMARY KEY (id);


--
-- Name: contents contents_section_id_paragraph_number_key; Type: CONSTRAINT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.contents
    ADD CONSTRAINT contents_section_id_paragraph_number_key UNIQUE (section_id, paragraph_number);


--
-- Name: sections sections_pkey; Type: CONSTRAINT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.sections
    ADD CONSTRAINT sections_pkey PRIMARY KEY (id);


--
-- Name: sections sections_subject_id_section_number_key; Type: CONSTRAINT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.sections
    ADD CONSTRAINT sections_subject_id_section_number_key UNIQUE (subject_id, number);


--
-- Name: subjects subjects_pkey; Type: CONSTRAINT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT subjects_pkey PRIMARY KEY (id);


--
-- Name: subjects subjects_title_key; Type: CONSTRAINT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.subjects
    ADD CONSTRAINT subjects_title_key UNIQUE (title);


--
-- Name: contents contents_section_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.contents
    ADD CONSTRAINT contents_section_id_fkey FOREIGN KEY (section_id) REFERENCES public.sections(id);


--
-- Name: sections sections_subject_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: matt
--

ALTER TABLE ONLY public.sections
    ADD CONSTRAINT sections_subject_id_fkey FOREIGN KEY (subject_id) REFERENCES public.subjects(id);


--
-- PostgreSQL database dump complete
--

