PGDMP  !                 	    |            indiestream    12.6    16.4 �                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    16497    indiestream    DATABASE     �   CREATE DATABASE indiestream WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Portuguese_Brazil.1252';
    DROP DATABASE indiestream;
                postgres    false                        2615    2200    public    SCHEMA        CREATE SCHEMA public;
    DROP SCHEMA public;
                postgres    false                       0    0    SCHEMA public    COMMENT     6   COMMENT ON SCHEMA public IS 'standard public schema';
                   postgres    false    7                       0    0    SCHEMA public    ACL     Q   REVOKE USAGE ON SCHEMA public FROM PUBLIC;
GRANT ALL ON SCHEMA public TO PUBLIC;
                   postgres    false    7            �            1259    16978    cidade    TABLE     �   CREATE TABLE public.cidade (
    id_cidade integer NOT NULL,
    id_estado integer NOT NULL,
    nome character varying(100)
);
    DROP TABLE public.cidade;
       public         heap    postgres    false    7            �            1259    16976    cidade_id_cidade_seq    SEQUENCE     �   CREATE SEQUENCE public.cidade_id_cidade_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.cidade_id_cidade_seq;
       public          postgres    false    228    7                       0    0    cidade_id_cidade_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.cidade_id_cidade_seq OWNED BY public.cidade.id_cidade;
          public          postgres    false    227            �            1259    16641 
   comentario    TABLE     �   CREATE TABLE public.comentario (
    id_comentario integer NOT NULL,
    id_usuario integer NOT NULL,
    id_projeto integer NOT NULL,
    comentario text NOT NULL
);
    DROP TABLE public.comentario;
       public         heap    postgres    false    7            �            1259    16639    comentario_id_comentario_seq    SEQUENCE     �   CREATE SEQUENCE public.comentario_id_comentario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.comentario_id_comentario_seq;
       public          postgres    false    210    7                       0    0    comentario_id_comentario_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.comentario_id_comentario_seq OWNED BY public.comentario.id_comentario;
          public          postgres    false    209            �            1259    16970    estado    TABLE     u   CREATE TABLE public.estado (
    id_estado integer NOT NULL,
    nome character varying(100),
    id_pais integer
);
    DROP TABLE public.estado;
       public         heap    postgres    false    7            �            1259    16968    estado_id_estado_seq    SEQUENCE     �   CREATE SEQUENCE public.estado_id_estado_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.estado_id_estado_seq;
       public          postgres    false    226    7                       0    0    estado_id_estado_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.estado_id_estado_seq OWNED BY public.estado.id_estado;
          public          postgres    false    225            �            1259    16946 
   habilidade    TABLE     h   CREATE TABLE public.habilidade (
    id_habilidade integer NOT NULL,
    nome character varying(100)
);
    DROP TABLE public.habilidade;
       public         heap    postgres    false    7            �            1259    16944    habilidade_id_habilidade_seq    SEQUENCE     �   CREATE SEQUENCE public.habilidade_id_habilidade_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.habilidade_id_habilidade_seq;
       public          postgres    false    222    7            	           0    0    habilidade_id_habilidade_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.habilidade_id_habilidade_seq OWNED BY public.habilidade.id_habilidade;
          public          postgres    false    221            �            1259    16996    pais    TABLE     e   CREATE TABLE public.pais (
    id_pais integer NOT NULL,
    nome character varying(100) NOT NULL
);
    DROP TABLE public.pais;
       public         heap    postgres    false    7            �            1259    16994    pais_id_pais_seq    SEQUENCE     �   CREATE SEQUENCE public.pais_id_pais_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public.pais_id_pais_seq;
       public          postgres    false    7    230            
           0    0    pais_id_pais_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public.pais_id_pais_seq OWNED BY public.pais.id_pais;
          public          postgres    false    229            �            1259    16768    plano    TABLE     f   CREATE TABLE public.plano (
    id_plano integer NOT NULL,
    nome character varying(40) NOT NULL
);
    DROP TABLE public.plano;
       public         heap    postgres    false    7            �            1259    16766    plano_id_plano_seq    SEQUENCE     �   CREATE SEQUENCE public.plano_id_plano_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.plano_id_plano_seq;
       public          postgres    false    7    220                       0    0    plano_id_plano_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.plano_id_plano_seq OWNED BY public.plano.id_plano;
          public          postgres    false    219            �            1259    17033 	   profissao    TABLE     T   CREATE TABLE public.profissao (
    id_profissao integer NOT NULL,
    nome text
);
    DROP TABLE public.profissao;
       public         heap    postgres    false    7            �            1259    17031    profissao_id_profissao_seq    SEQUENCE     �   CREATE SEQUENCE public.profissao_id_profissao_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.profissao_id_profissao_seq;
       public          postgres    false    7    234                       0    0    profissao_id_profissao_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.profissao_id_profissao_seq OWNED BY public.profissao.id_profissao;
          public          postgres    false    233            �            1259    16603    projeto    TABLE     I  CREATE TABLE public.projeto (
    id_projeto integer NOT NULL,
    titulo character varying(40) NOT NULL,
    descricao character varying(40) NOT NULL,
    imagem_url text,
    id_status integer NOT NULL,
    id_tipo integer NOT NULL,
    id_localizacao integer NOT NULL,
    id_cidade integer,
    id_usuario_criador integer
);
    DROP TABLE public.projeto;
       public         heap    postgres    false    7            �            1259    16601    projeto_id_projeto_seq    SEQUENCE     �   CREATE SEQUENCE public.projeto_id_projeto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.projeto_id_projeto_seq;
       public          postgres    false    7    206                       0    0    projeto_id_projeto_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.projeto_id_projeto_seq OWNED BY public.projeto.id_projeto;
          public          postgres    false    205            �            1259    17354    projeto_linha_do_tempo    TABLE     �   CREATE TABLE public.projeto_linha_do_tempo (
    id_evento integer NOT NULL,
    id_projeto integer,
    descricao text,
    data_evento timestamp without time zone,
    imagem_url text
);
 *   DROP TABLE public.projeto_linha_do_tempo;
       public         heap    postgres    false    7            �            1259    17352 $   projeto_linha_do_tempo_id_evento_seq    SEQUENCE     �   CREATE SEQUENCE public.projeto_linha_do_tempo_id_evento_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ;   DROP SEQUENCE public.projeto_linha_do_tempo_id_evento_seq;
       public          postgres    false    7    238                       0    0 $   projeto_linha_do_tempo_id_evento_seq    SEQUENCE OWNED BY     m   ALTER SEQUENCE public.projeto_linha_do_tempo_id_evento_seq OWNED BY public.projeto_linha_do_tempo.id_evento;
          public          postgres    false    237            �            1259    16721    rede_social    TABLE     �   CREATE TABLE public.rede_social (
    id_rede_social integer NOT NULL,
    nome character varying(40) NOT NULL,
    imagem_url text NOT NULL,
    perfil_url text
);
    DROP TABLE public.rede_social;
       public         heap    postgres    false    7            �            1259    16719    rede_social_id_rede_social_seq    SEQUENCE     �   CREATE SEQUENCE public.rede_social_id_rede_social_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 5   DROP SEQUENCE public.rede_social_id_rede_social_seq;
       public          postgres    false    7    216                       0    0    rede_social_id_rede_social_seq    SEQUENCE OWNED BY     a   ALTER SEQUENCE public.rede_social_id_rede_social_seq OWNED BY public.rede_social.id_rede_social;
          public          postgres    false    215            �            1259    16687    status_projeto    TABLE     g   CREATE TABLE public.status_projeto (
    id_status integer NOT NULL,
    nome character varying(40)
);
 "   DROP TABLE public.status_projeto;
       public         heap    postgres    false    7            �            1259    16685    status_projeto_id_status_seq    SEQUENCE     �   CREATE SEQUENCE public.status_projeto_id_status_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 3   DROP SEQUENCE public.status_projeto_id_status_seq;
       public          postgres    false    212    7                       0    0    status_projeto_id_status_seq    SEQUENCE OWNED BY     ]   ALTER SEQUENCE public.status_projeto_id_status_seq OWNED BY public.status_projeto.id_status;
          public          postgres    false    211            �            1259    16700    tipo_projeto    TABLE     c   CREATE TABLE public.tipo_projeto (
    id_tipo integer NOT NULL,
    nome character varying(40)
);
     DROP TABLE public.tipo_projeto;
       public         heap    postgres    false    7            �            1259    16698    tipo_projeto_id_tipo_seq    SEQUENCE     �   CREATE SEQUENCE public.tipo_projeto_id_tipo_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 /   DROP SEQUENCE public.tipo_projeto_id_tipo_seq;
       public          postgres    false    214    7                       0    0    tipo_projeto_id_tipo_seq    SEQUENCE OWNED BY     U   ALTER SEQUENCE public.tipo_projeto_id_tipo_seq OWNED BY public.tipo_projeto.id_tipo;
          public          postgres    false    213            �            1259    16558    usuario    TABLE     b  CREATE TABLE public.usuario (
    id_usuario integer NOT NULL,
    username character varying(40) NOT NULL,
    nome character varying(40),
    senha character varying(100) NOT NULL,
    email character varying(40) NOT NULL,
    id_plano integer NOT NULL,
    imagem_url text,
    data_nascimento date,
    id_cidade integer,
    id_profissao integer
);
    DROP TABLE public.usuario;
       public         heap    postgres    false    7            �            1259    16954    usuario_habilidade    TABLE     �   CREATE TABLE public.usuario_habilidade (
    id_usuario_habilidade integer NOT NULL,
    id_usuario integer NOT NULL,
    id_habilidade integer NOT NULL
);
 &   DROP TABLE public.usuario_habilidade;
       public         heap    postgres    false    7            �            1259    16952 ,   usuario_habilidade_id_usuario_habilidade_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_habilidade_id_usuario_habilidade_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 C   DROP SEQUENCE public.usuario_habilidade_id_usuario_habilidade_seq;
       public          postgres    false    224    7                       0    0 ,   usuario_habilidade_id_usuario_habilidade_seq    SEQUENCE OWNED BY     }   ALTER SEQUENCE public.usuario_habilidade_id_usuario_habilidade_seq OWNED BY public.usuario_habilidade.id_usuario_habilidade;
          public          postgres    false    223            �            1259    16556    usuario_id_usuario_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.usuario_id_usuario_seq;
       public          postgres    false    7    204                       0    0    usuario_id_usuario_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;
          public          postgres    false    203            �            1259    16622    usuario_projeto    TABLE     �   CREATE TABLE public.usuario_projeto (
    id_usuario_projeto integer NOT NULL,
    id_usuario integer NOT NULL,
    id_projeto integer NOT NULL
);
 #   DROP TABLE public.usuario_projeto;
       public         heap    postgres    false    7            �            1259    17336    usuario_projeto_autorizacao    TABLE     
  CREATE TABLE public.usuario_projeto_autorizacao (
    id_autorizacao integer NOT NULL,
    id_usuario integer,
    id_projeto integer,
    data_solicitacao timestamp without time zone,
    data_resposta timestamp without time zone,
    status_autorizacao boolean
);
 /   DROP TABLE public.usuario_projeto_autorizacao;
       public         heap    postgres    false    7            �            1259    17334 .   usuario_projeto_autorizacao_id_autorizacao_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_projeto_autorizacao_id_autorizacao_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 E   DROP SEQUENCE public.usuario_projeto_autorizacao_id_autorizacao_seq;
       public          postgres    false    7    236                       0    0 .   usuario_projeto_autorizacao_id_autorizacao_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public.usuario_projeto_autorizacao_id_autorizacao_seq OWNED BY public.usuario_projeto_autorizacao.id_autorizacao;
          public          postgres    false    235            �            1259    17009    usuario_projeto_favorito    TABLE     �   CREATE TABLE public.usuario_projeto_favorito (
    id_projeto_favorito integer NOT NULL,
    id_usuario integer,
    id_projeto integer
);
 ,   DROP TABLE public.usuario_projeto_favorito;
       public         heap    postgres    false    7            �            1259    17007 0   usuario_projeto_favorito_id_projeto_favorito_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_projeto_favorito_id_projeto_favorito_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 G   DROP SEQUENCE public.usuario_projeto_favorito_id_projeto_favorito_seq;
       public          postgres    false    232    7                       0    0 0   usuario_projeto_favorito_id_projeto_favorito_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public.usuario_projeto_favorito_id_projeto_favorito_seq OWNED BY public.usuario_projeto_favorito.id_projeto_favorito;
          public          postgres    false    231            �            1259    16620 &   usuario_projeto_id_usuario_projeto_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_projeto_id_usuario_projeto_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 =   DROP SEQUENCE public.usuario_projeto_id_usuario_projeto_seq;
       public          postgres    false    7    208                       0    0 &   usuario_projeto_id_usuario_projeto_seq    SEQUENCE OWNED BY     q   ALTER SEQUENCE public.usuario_projeto_id_usuario_projeto_seq OWNED BY public.usuario_projeto.id_usuario_projeto;
          public          postgres    false    207            �            1259    16750    usuario_rede_social    TABLE     �   CREATE TABLE public.usuario_rede_social (
    id_usuario_rede_social integer NOT NULL,
    id_usuario integer NOT NULL,
    id_rede_social integer NOT NULL
);
 '   DROP TABLE public.usuario_rede_social;
       public         heap    postgres    false    7            �            1259    16748 .   usuario_rede_social_id_usuario_rede_social_seq    SEQUENCE     �   CREATE SEQUENCE public.usuario_rede_social_id_usuario_rede_social_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 E   DROP SEQUENCE public.usuario_rede_social_id_usuario_rede_social_seq;
       public          postgres    false    7    218                       0    0 .   usuario_rede_social_id_usuario_rede_social_seq    SEQUENCE OWNED BY     �   ALTER SEQUENCE public.usuario_rede_social_id_usuario_rede_social_seq OWNED BY public.usuario_rede_social.id_usuario_rede_social;
          public          postgres    false    217                       2604    16981    cidade id_cidade    DEFAULT     t   ALTER TABLE ONLY public.cidade ALTER COLUMN id_cidade SET DEFAULT nextval('public.cidade_id_cidade_seq'::regclass);
 ?   ALTER TABLE public.cidade ALTER COLUMN id_cidade DROP DEFAULT;
       public          postgres    false    228    227    228                       2604    16644    comentario id_comentario    DEFAULT     �   ALTER TABLE ONLY public.comentario ALTER COLUMN id_comentario SET DEFAULT nextval('public.comentario_id_comentario_seq'::regclass);
 G   ALTER TABLE public.comentario ALTER COLUMN id_comentario DROP DEFAULT;
       public          postgres    false    210    209    210                       2604    16973    estado id_estado    DEFAULT     t   ALTER TABLE ONLY public.estado ALTER COLUMN id_estado SET DEFAULT nextval('public.estado_id_estado_seq'::regclass);
 ?   ALTER TABLE public.estado ALTER COLUMN id_estado DROP DEFAULT;
       public          postgres    false    225    226    226                       2604    16949    habilidade id_habilidade    DEFAULT     �   ALTER TABLE ONLY public.habilidade ALTER COLUMN id_habilidade SET DEFAULT nextval('public.habilidade_id_habilidade_seq'::regclass);
 G   ALTER TABLE public.habilidade ALTER COLUMN id_habilidade DROP DEFAULT;
       public          postgres    false    222    221    222                       2604    16999    pais id_pais    DEFAULT     l   ALTER TABLE ONLY public.pais ALTER COLUMN id_pais SET DEFAULT nextval('public.pais_id_pais_seq'::regclass);
 ;   ALTER TABLE public.pais ALTER COLUMN id_pais DROP DEFAULT;
       public          postgres    false    230    229    230                       2604    16771    plano id_plano    DEFAULT     p   ALTER TABLE ONLY public.plano ALTER COLUMN id_plano SET DEFAULT nextval('public.plano_id_plano_seq'::regclass);
 =   ALTER TABLE public.plano ALTER COLUMN id_plano DROP DEFAULT;
       public          postgres    false    220    219    220                       2604    17036    profissao id_profissao    DEFAULT     �   ALTER TABLE ONLY public.profissao ALTER COLUMN id_profissao SET DEFAULT nextval('public.profissao_id_profissao_seq'::regclass);
 E   ALTER TABLE public.profissao ALTER COLUMN id_profissao DROP DEFAULT;
       public          postgres    false    234    233    234                       2604    16606    projeto id_projeto    DEFAULT     x   ALTER TABLE ONLY public.projeto ALTER COLUMN id_projeto SET DEFAULT nextval('public.projeto_id_projeto_seq'::regclass);
 A   ALTER TABLE public.projeto ALTER COLUMN id_projeto DROP DEFAULT;
       public          postgres    false    206    205    206            !           2604    17357     projeto_linha_do_tempo id_evento    DEFAULT     �   ALTER TABLE ONLY public.projeto_linha_do_tempo ALTER COLUMN id_evento SET DEFAULT nextval('public.projeto_linha_do_tempo_id_evento_seq'::regclass);
 O   ALTER TABLE public.projeto_linha_do_tempo ALTER COLUMN id_evento DROP DEFAULT;
       public          postgres    false    238    237    238                       2604    16724    rede_social id_rede_social    DEFAULT     �   ALTER TABLE ONLY public.rede_social ALTER COLUMN id_rede_social SET DEFAULT nextval('public.rede_social_id_rede_social_seq'::regclass);
 I   ALTER TABLE public.rede_social ALTER COLUMN id_rede_social DROP DEFAULT;
       public          postgres    false    215    216    216                       2604    16690    status_projeto id_status    DEFAULT     �   ALTER TABLE ONLY public.status_projeto ALTER COLUMN id_status SET DEFAULT nextval('public.status_projeto_id_status_seq'::regclass);
 G   ALTER TABLE public.status_projeto ALTER COLUMN id_status DROP DEFAULT;
       public          postgres    false    211    212    212                       2604    16703    tipo_projeto id_tipo    DEFAULT     |   ALTER TABLE ONLY public.tipo_projeto ALTER COLUMN id_tipo SET DEFAULT nextval('public.tipo_projeto_id_tipo_seq'::regclass);
 C   ALTER TABLE public.tipo_projeto ALTER COLUMN id_tipo DROP DEFAULT;
       public          postgres    false    214    213    214                       2604    16561    usuario id_usuario    DEFAULT     x   ALTER TABLE ONLY public.usuario ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuario_id_usuario_seq'::regclass);
 A   ALTER TABLE public.usuario ALTER COLUMN id_usuario DROP DEFAULT;
       public          postgres    false    204    203    204                       2604    16957 (   usuario_habilidade id_usuario_habilidade    DEFAULT     �   ALTER TABLE ONLY public.usuario_habilidade ALTER COLUMN id_usuario_habilidade SET DEFAULT nextval('public.usuario_habilidade_id_usuario_habilidade_seq'::regclass);
 W   ALTER TABLE public.usuario_habilidade ALTER COLUMN id_usuario_habilidade DROP DEFAULT;
       public          postgres    false    223    224    224                       2604    16625 "   usuario_projeto id_usuario_projeto    DEFAULT     �   ALTER TABLE ONLY public.usuario_projeto ALTER COLUMN id_usuario_projeto SET DEFAULT nextval('public.usuario_projeto_id_usuario_projeto_seq'::regclass);
 Q   ALTER TABLE public.usuario_projeto ALTER COLUMN id_usuario_projeto DROP DEFAULT;
       public          postgres    false    207    208    208                        2604    17339 *   usuario_projeto_autorizacao id_autorizacao    DEFAULT     �   ALTER TABLE ONLY public.usuario_projeto_autorizacao ALTER COLUMN id_autorizacao SET DEFAULT nextval('public.usuario_projeto_autorizacao_id_autorizacao_seq'::regclass);
 Y   ALTER TABLE public.usuario_projeto_autorizacao ALTER COLUMN id_autorizacao DROP DEFAULT;
       public          postgres    false    236    235    236                       2604    17012 ,   usuario_projeto_favorito id_projeto_favorito    DEFAULT     �   ALTER TABLE ONLY public.usuario_projeto_favorito ALTER COLUMN id_projeto_favorito SET DEFAULT nextval('public.usuario_projeto_favorito_id_projeto_favorito_seq'::regclass);
 [   ALTER TABLE public.usuario_projeto_favorito ALTER COLUMN id_projeto_favorito DROP DEFAULT;
       public          postgres    false    232    231    232                       2604    16753 *   usuario_rede_social id_usuario_rede_social    DEFAULT     �   ALTER TABLE ONLY public.usuario_rede_social ALTER COLUMN id_usuario_rede_social SET DEFAULT nextval('public.usuario_rede_social_id_usuario_rede_social_seq'::regclass);
 Y   ALTER TABLE public.usuario_rede_social ALTER COLUMN id_usuario_rede_social DROP DEFAULT;
       public          postgres    false    218    217    218            �          0    16978    cidade 
   TABLE DATA           <   COPY public.cidade (id_cidade, id_estado, nome) FROM stdin;
    public          postgres    false    228   s�       �          0    16641 
   comentario 
   TABLE DATA           W   COPY public.comentario (id_comentario, id_usuario, id_projeto, comentario) FROM stdin;
    public          postgres    false    210   ��       �          0    16970    estado 
   TABLE DATA           :   COPY public.estado (id_estado, nome, id_pais) FROM stdin;
    public          postgres    false    226   ��       �          0    16946 
   habilidade 
   TABLE DATA           9   COPY public.habilidade (id_habilidade, nome) FROM stdin;
    public          postgres    false    222   �       �          0    16996    pais 
   TABLE DATA           -   COPY public.pais (id_pais, nome) FROM stdin;
    public          postgres    false    230   .�       �          0    16768    plano 
   TABLE DATA           /   COPY public.plano (id_plano, nome) FROM stdin;
    public          postgres    false    220   T�       �          0    17033 	   profissao 
   TABLE DATA           7   COPY public.profissao (id_profissao, nome) FROM stdin;
    public          postgres    false    234   ��       �          0    16603    projeto 
   TABLE DATA           �   COPY public.projeto (id_projeto, titulo, descricao, imagem_url, id_status, id_tipo, id_localizacao, id_cidade, id_usuario_criador) FROM stdin;
    public          postgres    false    206   ��       �          0    17354    projeto_linha_do_tempo 
   TABLE DATA           k   COPY public.projeto_linha_do_tempo (id_evento, id_projeto, descricao, data_evento, imagem_url) FROM stdin;
    public          postgres    false    238   ��       �          0    16721    rede_social 
   TABLE DATA           S   COPY public.rede_social (id_rede_social, nome, imagem_url, perfil_url) FROM stdin;
    public          postgres    false    216   �       �          0    16687    status_projeto 
   TABLE DATA           9   COPY public.status_projeto (id_status, nome) FROM stdin;
    public          postgres    false    212   j�       �          0    16700    tipo_projeto 
   TABLE DATA           5   COPY public.tipo_projeto (id_tipo, nome) FROM stdin;
    public          postgres    false    214   ��       �          0    16558    usuario 
   TABLE DATA           �   COPY public.usuario (id_usuario, username, nome, senha, email, id_plano, imagem_url, data_nascimento, id_cidade, id_profissao) FROM stdin;
    public          postgres    false    204   ��       �          0    16954    usuario_habilidade 
   TABLE DATA           ^   COPY public.usuario_habilidade (id_usuario_habilidade, id_usuario, id_habilidade) FROM stdin;
    public          postgres    false    224   t�       �          0    16622    usuario_projeto 
   TABLE DATA           U   COPY public.usuario_projeto (id_usuario_projeto, id_usuario, id_projeto) FROM stdin;
    public          postgres    false    208   ��       �          0    17336    usuario_projeto_autorizacao 
   TABLE DATA           �   COPY public.usuario_projeto_autorizacao (id_autorizacao, id_usuario, id_projeto, data_solicitacao, data_resposta, status_autorizacao) FROM stdin;
    public          postgres    false    236   ��       �          0    17009    usuario_projeto_favorito 
   TABLE DATA           _   COPY public.usuario_projeto_favorito (id_projeto_favorito, id_usuario, id_projeto) FROM stdin;
    public          postgres    false    232   ��       �          0    16750    usuario_rede_social 
   TABLE DATA           a   COPY public.usuario_rede_social (id_usuario_rede_social, id_usuario, id_rede_social) FROM stdin;
    public          postgres    false    218   ��                  0    0    cidade_id_cidade_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.cidade_id_cidade_seq', 1, true);
          public          postgres    false    227                       0    0    comentario_id_comentario_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.comentario_id_comentario_seq', 2, true);
          public          postgres    false    209                       0    0    estado_id_estado_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.estado_id_estado_seq', 1, true);
          public          postgres    false    225                       0    0    habilidade_id_habilidade_seq    SEQUENCE SET     K   SELECT pg_catalog.setval('public.habilidade_id_habilidade_seq', 1, false);
          public          postgres    false    221                       0    0    pais_id_pais_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.pais_id_pais_seq', 1, true);
          public          postgres    false    229                       0    0    plano_id_plano_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.plano_id_plano_seq', 2, true);
          public          postgres    false    219                       0    0    profissao_id_profissao_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.profissao_id_profissao_seq', 1, true);
          public          postgres    false    233                       0    0    projeto_id_projeto_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.projeto_id_projeto_seq', 2, true);
          public          postgres    false    205                        0    0 $   projeto_linha_do_tempo_id_evento_seq    SEQUENCE SET     S   SELECT pg_catalog.setval('public.projeto_linha_do_tempo_id_evento_seq', 1, false);
          public          postgres    false    237            !           0    0    rede_social_id_rede_social_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.rede_social_id_rede_social_seq', 3, true);
          public          postgres    false    215            "           0    0    status_projeto_id_status_seq    SEQUENCE SET     J   SELECT pg_catalog.setval('public.status_projeto_id_status_seq', 3, true);
          public          postgres    false    211            #           0    0    tipo_projeto_id_tipo_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.tipo_projeto_id_tipo_seq', 3, true);
          public          postgres    false    213            $           0    0 ,   usuario_habilidade_id_usuario_habilidade_seq    SEQUENCE SET     [   SELECT pg_catalog.setval('public.usuario_habilidade_id_usuario_habilidade_seq', 1, false);
          public          postgres    false    223            %           0    0    usuario_id_usuario_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.usuario_id_usuario_seq', 3, true);
          public          postgres    false    203            &           0    0 .   usuario_projeto_autorizacao_id_autorizacao_seq    SEQUENCE SET     ]   SELECT pg_catalog.setval('public.usuario_projeto_autorizacao_id_autorizacao_seq', 1, false);
          public          postgres    false    235            '           0    0 0   usuario_projeto_favorito_id_projeto_favorito_seq    SEQUENCE SET     _   SELECT pg_catalog.setval('public.usuario_projeto_favorito_id_projeto_favorito_seq', 1, false);
          public          postgres    false    231            (           0    0 &   usuario_projeto_id_usuario_projeto_seq    SEQUENCE SET     T   SELECT pg_catalog.setval('public.usuario_projeto_id_usuario_projeto_seq', 2, true);
          public          postgres    false    207            )           0    0 .   usuario_rede_social_id_usuario_rede_social_seq    SEQUENCE SET     \   SELECT pg_catalog.setval('public.usuario_rede_social_id_usuario_rede_social_seq', 4, true);
          public          postgres    false    217            ;           2606    16983    cidade cidade_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.cidade
    ADD CONSTRAINT cidade_pkey PRIMARY KEY (id_cidade);
 <   ALTER TABLE ONLY public.cidade DROP CONSTRAINT cidade_pkey;
       public            postgres    false    228            )           2606    16649    comentario comentario_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_pkey PRIMARY KEY (id_comentario);
 D   ALTER TABLE ONLY public.comentario DROP CONSTRAINT comentario_pkey;
       public            postgres    false    210            9           2606    16975    estado estado_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.estado
    ADD CONSTRAINT estado_pkey PRIMARY KEY (id_estado);
 <   ALTER TABLE ONLY public.estado DROP CONSTRAINT estado_pkey;
       public            postgres    false    226            5           2606    16951    habilidade habilidade_pkey 
   CONSTRAINT     c   ALTER TABLE ONLY public.habilidade
    ADD CONSTRAINT habilidade_pkey PRIMARY KEY (id_habilidade);
 D   ALTER TABLE ONLY public.habilidade DROP CONSTRAINT habilidade_pkey;
       public            postgres    false    222            =           2606    17001    pais pais_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.pais
    ADD CONSTRAINT pais_pkey PRIMARY KEY (id_pais);
 8   ALTER TABLE ONLY public.pais DROP CONSTRAINT pais_pkey;
       public            postgres    false    230            3           2606    16773    plano plano_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.plano
    ADD CONSTRAINT plano_pkey PRIMARY KEY (id_plano);
 :   ALTER TABLE ONLY public.plano DROP CONSTRAINT plano_pkey;
       public            postgres    false    220            A           2606    17041    profissao profissao_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.profissao
    ADD CONSTRAINT profissao_pkey PRIMARY KEY (id_profissao);
 B   ALTER TABLE ONLY public.profissao DROP CONSTRAINT profissao_pkey;
       public            postgres    false    234            E           2606    17362 2   projeto_linha_do_tempo projeto_linha_do_tempo_pkey 
   CONSTRAINT     w   ALTER TABLE ONLY public.projeto_linha_do_tempo
    ADD CONSTRAINT projeto_linha_do_tempo_pkey PRIMARY KEY (id_evento);
 \   ALTER TABLE ONLY public.projeto_linha_do_tempo DROP CONSTRAINT projeto_linha_do_tempo_pkey;
       public            postgres    false    238            %           2606    16611    projeto projeto_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.projeto
    ADD CONSTRAINT projeto_pkey PRIMARY KEY (id_projeto);
 >   ALTER TABLE ONLY public.projeto DROP CONSTRAINT projeto_pkey;
       public            postgres    false    206            /           2606    16729    rede_social rede_social_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.rede_social
    ADD CONSTRAINT rede_social_pkey PRIMARY KEY (id_rede_social);
 F   ALTER TABLE ONLY public.rede_social DROP CONSTRAINT rede_social_pkey;
       public            postgres    false    216            +           2606    16692 "   status_projeto status_projeto_pkey 
   CONSTRAINT     g   ALTER TABLE ONLY public.status_projeto
    ADD CONSTRAINT status_projeto_pkey PRIMARY KEY (id_status);
 L   ALTER TABLE ONLY public.status_projeto DROP CONSTRAINT status_projeto_pkey;
       public            postgres    false    212            -           2606    16705    tipo_projeto tipo_projeto_pkey 
   CONSTRAINT     a   ALTER TABLE ONLY public.tipo_projeto
    ADD CONSTRAINT tipo_projeto_pkey PRIMARY KEY (id_tipo);
 H   ALTER TABLE ONLY public.tipo_projeto DROP CONSTRAINT tipo_projeto_pkey;
       public            postgres    false    214            7           2606    17048 *   usuario_habilidade usuario_habilidade_pkey 
   CONSTRAINT     {   ALTER TABLE ONLY public.usuario_habilidade
    ADD CONSTRAINT usuario_habilidade_pkey PRIMARY KEY (id_usuario_habilidade);
 T   ALTER TABLE ONLY public.usuario_habilidade DROP CONSTRAINT usuario_habilidade_pkey;
       public            postgres    false    224            #           2606    16563    usuario usuario_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);
 >   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_pkey;
       public            postgres    false    204            C           2606    17341 <   usuario_projeto_autorizacao usuario_projeto_autorizacao_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.usuario_projeto_autorizacao
    ADD CONSTRAINT usuario_projeto_autorizacao_pkey PRIMARY KEY (id_autorizacao);
 f   ALTER TABLE ONLY public.usuario_projeto_autorizacao DROP CONSTRAINT usuario_projeto_autorizacao_pkey;
       public            postgres    false    236            ?           2606    17014 6   usuario_projeto_favorito usuario_projeto_favorito_pkey 
   CONSTRAINT     �   ALTER TABLE ONLY public.usuario_projeto_favorito
    ADD CONSTRAINT usuario_projeto_favorito_pkey PRIMARY KEY (id_projeto_favorito);
 `   ALTER TABLE ONLY public.usuario_projeto_favorito DROP CONSTRAINT usuario_projeto_favorito_pkey;
       public            postgres    false    232            '           2606    16627 $   usuario_projeto usuario_projeto_pkey 
   CONSTRAINT     r   ALTER TABLE ONLY public.usuario_projeto
    ADD CONSTRAINT usuario_projeto_pkey PRIMARY KEY (id_usuario_projeto);
 N   ALTER TABLE ONLY public.usuario_projeto DROP CONSTRAINT usuario_projeto_pkey;
       public            postgres    false    208            1           2606    16755 ,   usuario_rede_social usuario_rede_social_pkey 
   CONSTRAINT     ~   ALTER TABLE ONLY public.usuario_rede_social
    ADD CONSTRAINT usuario_rede_social_pkey PRIMARY KEY (id_usuario_rede_social);
 V   ALTER TABLE ONLY public.usuario_rede_social DROP CONSTRAINT usuario_rede_social_pkey;
       public            postgres    false    218            V           2606    16984    cidade cidade_id_estado_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.cidade
    ADD CONSTRAINT cidade_id_estado_fkey FOREIGN KEY (id_estado) REFERENCES public.estado(id_estado);
 F   ALTER TABLE ONLY public.cidade DROP CONSTRAINT cidade_id_estado_fkey;
       public          postgres    false    228    2873    226            O           2606    16655 %   comentario comentario_id_projeto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_id_projeto_fkey FOREIGN KEY (id_projeto) REFERENCES public.projeto(id_projeto);
 O   ALTER TABLE ONLY public.comentario DROP CONSTRAINT comentario_id_projeto_fkey;
       public          postgres    false    210    2853    206            P           2606    16650 %   comentario comentario_id_usuario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.comentario
    ADD CONSTRAINT comentario_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);
 O   ALTER TABLE ONLY public.comentario DROP CONSTRAINT comentario_id_usuario_fkey;
       public          postgres    false    210    2851    204            U           2606    17002    estado estado_id_pais_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.estado
    ADD CONSTRAINT estado_id_pais_fkey FOREIGN KEY (id_pais) REFERENCES public.pais(id_pais);
 D   ALTER TABLE ONLY public.estado DROP CONSTRAINT estado_id_pais_fkey;
       public          postgres    false    230    2877    226            I           2606    17025    projeto projeto_id_cidade_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.projeto
    ADD CONSTRAINT projeto_id_cidade_fkey FOREIGN KEY (id_cidade) REFERENCES public.cidade(id_cidade);
 H   ALTER TABLE ONLY public.projeto DROP CONSTRAINT projeto_id_cidade_fkey;
       public          postgres    false    206    2875    228            J           2606    16693    projeto projeto_id_status_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.projeto
    ADD CONSTRAINT projeto_id_status_fkey FOREIGN KEY (id_status) REFERENCES public.status_projeto(id_status);
 H   ALTER TABLE ONLY public.projeto DROP CONSTRAINT projeto_id_status_fkey;
       public          postgres    false    206    212    2859            K           2606    16706    projeto projeto_id_tipo_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.projeto
    ADD CONSTRAINT projeto_id_tipo_fkey FOREIGN KEY (id_tipo) REFERENCES public.tipo_projeto(id_tipo);
 F   ALTER TABLE ONLY public.projeto DROP CONSTRAINT projeto_id_tipo_fkey;
       public          postgres    false    214    2861    206            L           2606    17329 '   projeto projeto_id_usuario_criador_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.projeto
    ADD CONSTRAINT projeto_id_usuario_criador_fkey FOREIGN KEY (id_usuario_criador) REFERENCES public.usuario(id_usuario);
 Q   ALTER TABLE ONLY public.projeto DROP CONSTRAINT projeto_id_usuario_criador_fkey;
       public          postgres    false    206    204    2851            [           2606    17363 =   projeto_linha_do_tempo projeto_linha_do_tempo_id_projeto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.projeto_linha_do_tempo
    ADD CONSTRAINT projeto_linha_do_tempo_id_projeto_fkey FOREIGN KEY (id_projeto) REFERENCES public.projeto(id_projeto);
 g   ALTER TABLE ONLY public.projeto_linha_do_tempo DROP CONSTRAINT projeto_linha_do_tempo_id_projeto_fkey;
       public          postgres    false    206    238    2853            S           2606    16963 8   usuario_habilidade usuario_habilidade_id_habilidade_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_habilidade
    ADD CONSTRAINT usuario_habilidade_id_habilidade_fkey FOREIGN KEY (id_habilidade) REFERENCES public.habilidade(id_habilidade);
 b   ALTER TABLE ONLY public.usuario_habilidade DROP CONSTRAINT usuario_habilidade_id_habilidade_fkey;
       public          postgres    false    2869    224    222            T           2606    16958 5   usuario_habilidade usuario_habilidade_id_usuario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_habilidade
    ADD CONSTRAINT usuario_habilidade_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);
 _   ALTER TABLE ONLY public.usuario_habilidade DROP CONSTRAINT usuario_habilidade_id_usuario_fkey;
       public          postgres    false    204    224    2851            F           2606    16989    usuario usuario_id_cidade_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_id_cidade_fkey FOREIGN KEY (id_cidade) REFERENCES public.cidade(id_cidade);
 H   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_id_cidade_fkey;
       public          postgres    false    204    2875    228            G           2606    16774    usuario usuario_id_plano_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_id_plano_fkey FOREIGN KEY (id_plano) REFERENCES public.plano(id_plano);
 G   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_id_plano_fkey;
       public          postgres    false    204    2867    220            H           2606    17042    usuario usuario_id_profissao    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_id_profissao FOREIGN KEY (id_profissao) REFERENCES public.profissao(id_profissao);
 F   ALTER TABLE ONLY public.usuario DROP CONSTRAINT usuario_id_profissao;
       public          postgres    false    234    204    2881            Y           2606    17347 G   usuario_projeto_autorizacao usuario_projeto_autorizacao_id_projeto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_projeto_autorizacao
    ADD CONSTRAINT usuario_projeto_autorizacao_id_projeto_fkey FOREIGN KEY (id_projeto) REFERENCES public.projeto(id_projeto);
 q   ALTER TABLE ONLY public.usuario_projeto_autorizacao DROP CONSTRAINT usuario_projeto_autorizacao_id_projeto_fkey;
       public          postgres    false    236    206    2853            Z           2606    17342 G   usuario_projeto_autorizacao usuario_projeto_autorizacao_id_usuario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_projeto_autorizacao
    ADD CONSTRAINT usuario_projeto_autorizacao_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);
 q   ALTER TABLE ONLY public.usuario_projeto_autorizacao DROP CONSTRAINT usuario_projeto_autorizacao_id_usuario_fkey;
       public          postgres    false    2851    204    236            W           2606    17020 A   usuario_projeto_favorito usuario_projeto_favorito_id_projeto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_projeto_favorito
    ADD CONSTRAINT usuario_projeto_favorito_id_projeto_fkey FOREIGN KEY (id_projeto) REFERENCES public.projeto(id_projeto);
 k   ALTER TABLE ONLY public.usuario_projeto_favorito DROP CONSTRAINT usuario_projeto_favorito_id_projeto_fkey;
       public          postgres    false    232    206    2853            X           2606    17015 A   usuario_projeto_favorito usuario_projeto_favorito_id_usuario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_projeto_favorito
    ADD CONSTRAINT usuario_projeto_favorito_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);
 k   ALTER TABLE ONLY public.usuario_projeto_favorito DROP CONSTRAINT usuario_projeto_favorito_id_usuario_fkey;
       public          postgres    false    232    204    2851            M           2606    16633 /   usuario_projeto usuario_projeto_id_projeto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_projeto
    ADD CONSTRAINT usuario_projeto_id_projeto_fkey FOREIGN KEY (id_projeto) REFERENCES public.projeto(id_projeto);
 Y   ALTER TABLE ONLY public.usuario_projeto DROP CONSTRAINT usuario_projeto_id_projeto_fkey;
       public          postgres    false    206    2853    208            N           2606    16628 /   usuario_projeto usuario_projeto_id_usuario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_projeto
    ADD CONSTRAINT usuario_projeto_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);
 Y   ALTER TABLE ONLY public.usuario_projeto DROP CONSTRAINT usuario_projeto_id_usuario_fkey;
       public          postgres    false    204    208    2851            Q           2606    16761 ;   usuario_rede_social usuario_rede_social_id_rede_social_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_rede_social
    ADD CONSTRAINT usuario_rede_social_id_rede_social_fkey FOREIGN KEY (id_rede_social) REFERENCES public.rede_social(id_rede_social);
 e   ALTER TABLE ONLY public.usuario_rede_social DROP CONSTRAINT usuario_rede_social_id_rede_social_fkey;
       public          postgres    false    216    2863    218            R           2606    16756 7   usuario_rede_social usuario_rede_social_id_usuario_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.usuario_rede_social
    ADD CONSTRAINT usuario_rede_social_id_usuario_fkey FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario);
 a   ALTER TABLE ONLY public.usuario_rede_social DROP CONSTRAINT usuario_rede_social_id_usuario_fkey;
       public          postgres    false    218    2851    204            �      x�3�4�t�/*I�I�J����� 2��      �   ;   x�3�4B�Ԝ|�����Ԓ|EEE.#��k�BAbQIfrfAj�BJjqq*L�W� &�      �      x�3�tNM,:��Ӑ+F��� '�      �      x������ � �      �      x�3�t*J,������� <J      �   !   x�3�t/:��$��ˈ3�(57�4�+F��� mx~      �      x�3�(�O/J�ML�/����� <�k      �   :   x�3��I-K�+I�I-�%%�V������9�z�����0�e�YV�L"dc���� ���      �      x������ � �      �   C   x�3���+.IL/J���())(�����MLO��K�����2�tKLNM���ơ��3$3;�t� � �      �   7   x�3�t�U(�O)=����|.#ΰ���bǤԢ��b.c΀���Ĕ|�=... ��4      �   .   x�3�t�O.�M�+9��(3�ˈ�-3'7�˘3��ʢ�T�=... CQ      �   u   x�3��M,J�/��S
���E���F�&�f�PI��������\N#�?8�2�LN,��vS
��9e���I�)P	$��(:�F�d���,Ә6��ѭ6����50"���\1z\\\ 'f97      �      x������ � �      �      x�3�4�4�2�4�1z\\\ 	      �      x������ � �      �      x������ � �      �   !   x�3�4�4�2�F\Ɯ@��Hs��qqq 40p     