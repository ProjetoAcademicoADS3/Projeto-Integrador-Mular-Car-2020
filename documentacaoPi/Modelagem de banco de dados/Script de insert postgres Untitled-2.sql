INSERT INTO public.tb_usuarios
( usu_nome, usu_cpf, usu_email, usu_senha, usu_perc_cupom, usu_dt_cupom, usu_perfil)
VALUES( 'Ivonete Ferreira da Costa Reis', '80745520120', 'ivone@gmail.com', '123456', 45, '2020-11-06', 'CLIENTE');
INSERT INTO public.tb_usuarios
( usu_nome, usu_cpf, usu_email, usu_senha, usu_perc_cupom, usu_dt_cupom, usu_perfil)
VALUES( 'Super usuario', '50794334008', 'usuario_admin@gmail.com', '123456', 0, '2020-11-10', 'ADMINISTRADOR');
INSERT INTO public.tb_usuarios
( usu_nome, usu_cpf, usu_email, usu_senha, usu_perc_cupom, usu_dt_cupom, usu_perfil)
VALUES( 'Rogerio Reis', '64066522076', 'rogerio@gmail.com', '123456', 55, '2020-11-10', 'CLIENTE');
INSERT INTO public.tb_usuarios
(usu_nome, usu_cpf, usu_email, usu_senha, usu_perc_cupom, usu_dt_cupom, usu_perfil)
VALUES( 'Rogerio de Souza Ramos', '82916982019', 'ramos@gmail.com', '123456', 35, '2020-11-10', 'CLIENTE');
INSERT INTO public.tb_usuarios
(usu_nome, usu_cpf, usu_email, usu_senha, usu_perc_cupom, usu_dt_cupom, usu_perfil)
VALUES( 'Rogério da Silva', '10464305055', 'silva@gmail.com', '123456', 36, '2020-11-06', 'CLIENTE');
INSERT INTO public.tb_usuarios
( usu_nome, usu_cpf, usu_email, usu_senha, usu_perc_cupom, usu_dt_cupom, usu_perfil)
VALUES( 'Carlos Menezes', '20860026027', 'menezes@gmail.com', '123456', 53, '2020-11-10', 'CLIENTE');
INSERT INTO public.tb_usuarios
(usu_nome, usu_cpf, usu_email, usu_senha, usu_perc_cupom, usu_dt_cupom, usu_perfil)
VALUES( 'Samuel dos Reis', '63591997005', 'samuel@gmail.com', '123456', 34, '2020-11-06', 'CLIENTE');
INSERT INTO public.tb_usuarios
(usu_nome, usu_cpf, usu_email, usu_senha, usu_perc_cupom, usu_dt_cupom, usu_perfil)
VALUES( 'Cora Coralina', '33447265876', 'cora@gmail.com', '123456', 15, '2020-11-10', 'CLIENTE');
INSERT INTO public.tb_usuarios
(usu_nome, usu_cpf, usu_email, usu_senha, usu_perc_cupom, usu_dt_cupom, usu_perfil)
VALUES( 'andere luis dos reis', '07403428609', 'andre@gmail.com', '123456', 13, '2020-11-10', 'CLIENTE');
INSERT INTO public.tb_usuarios
( usu_nome, usu_cpf, usu_email, usu_senha, usu_perc_cupom, usu_dt_cupom, usu_perfil)
VALUES( 'Rogério Tadeu dos Reis', '49707802260', 'admin', 'admin', 39, '2020-11-06', 'CLIENTE');
INSERT INTO public.tb_usuarios
(usu_nome, usu_cpf, usu_email, usu_senha, usu_perc_cupom, usu_dt_cupom, usu_perfil)
VALUES('Lucas França Fagundes', '23766661396', 'lucas@gmail.com', '123456', 27, '2020-11-10', 'CLIENTE');

INSERT INTO public.tb_categorias
( cat_descricao)
VALUES() 'aventura');
INSERT INTO public.tb_categorias
(cat_descricao)
VALUES('comedia');
INSERT INTO public.tb_categorias
(cat_descricao)
VALUES('ficcao');
INSERT INTO public.tb_categorias
(cat_descricao)
VALUES('misterio');
INSERT INTO public.tb_categorias
(cat_descricao)
VALUES( 'nacional');
INSERT INTO public.tb_categorias
( cat_descricao)
VALUES( 'series');
INSERT INTO public.tb_categorias
( cat_descricao)
VALUES( 'lançamentos');
INSERT INTO public.tb_categorias
( cat_descricao)
VALUES('suspense');
INSERT INTO public.tb_categorias
( cat_descricao)
VALUES( 'dmca');
INSERT INTO public.tb_categorias
( cat_descricao)
VALUES( 'acao 01');

INSERT INTO public.tb_planos
( pla_acesso_simultaneo, pla_descricao, pla_preco)
VALUES( 1, 'individual', 20.00);
INSERT INTO public.tb_planos
( pla_acesso_simultaneo, pla_descricao, pla_preco)
VALUES(2, 'standart', 25.00);
INSERT INTO public.tb_planos
( pla_acesso_simultaneo, pla_descricao, pla_preco)
VALUES( 5, 'familiar', 50.00);
INSERT INTO public.tb_planos
( pla_acesso_simultaneo, pla_descricao, pla_preco)
VALUES( 10, 'especial', 75.00);
INSERT INTO public.tb_planos
(pla_acesso_simultaneo, pla_descricao, pla_preco)
VALUES(1, 'plano teste1', 1.10);
INSERT INTO public.tb_planos
(pla_acesso_simultaneo, pla_descricao, pla_preco)
VALUES( 15, 'amigo', 105.00);

INSERT INTO public.tb_filmes
( fil_sinopse, fil_titulo, fil_ano, fil_cat_iden, fil_path)
VALUES( 'Um dos maiores produtores de ecstasy do mundo, Ferry Bouman (Frank Lammers), vive uma vida encantada em sua villa na fronteira entre a Bélgica e a Holanda. Mas as coisas começam a mudar quando dois agentes disfarçados entram em seu território, tentam se infiltrar na vida de Bouman e encerrar sua rede. Undercover é uma série policial fictícia inspirada em uma série de eventos da vida real.', 'Operação Ecstasy 2ª Temporada', 2020, 13, 'C:\Users\roger\Videos\4K Video Downloader\.mp4\Operação Ecstasy 2ª Temporada.mp4');
INSERT INTO public.tb_filmes
( fil_sinopse, fil_titulo, fil_ano, fil_cat_iden, fil_path)
VALUES( 'Antônia (Samantha Schmütz) está desempregada e com dificuldades para comprar o básico. Quando vai ao mercado e descobre que os preços aumentaram, faz uma verdadeira revolução e todos levam os produtos sem pagar. Agora, ela precisa esconder o crime do marido (Edmilson Filho) e da polícia (Fernando Caruso e Flávio Bauraqui), contando com a ajuda de sua melhor amiga (Flavia Reis) para resolver a confusão que causou..', 'Não Vamos Pagar Nada Torrent', 2020, 6, 'C:\Users\roger\Videos\4K Video Downloader\nao.mp4');
INSERT INTO public.tb_filmes
( fil_sinopse, fil_titulo, fil_ano, fil_cat_iden, fil_path)
VALUES('Depois que uma batalha devastadora contra um exército de drones deixou a sargento Barbara Slade paralisada, sua única chance de sobrevivência é ser colocada dentro de um corpo andróide artificial. No entanto, uma vez dentro de uma unidade cibernética, a identidade de Slade é equivocada, levando-a a uma célula de ativistas antigovernamentais e a um plano alucinante que ameaça destruir tudo o que ela fez sacrifícios para proteger.', 'Android Uprising Torrent 2020 Legendado WEB-DL 1080p', 2020, 4, 'C:\Users\roger\Videos\4K Video Downloader\android.mp4');
INSERT INTO public.tb_filmes
( fil_sinopse, fil_titulo, fil_ano, fil_cat_iden, fil_path)
VALUES( 'Na Série Bless This Mess O plano de um casal de recém casados de abandonar uma cidade grande e viver por uma vida mais simples em Nebraska, não era o que eles esperava.', 'Bless This Mess 2ª Temporada Torrent', 2019, 3, 'C:\Users\roger\Videos\4K Video Downloader\bless.mp4');
INSERT INTO public.tb_filmes
( fil_sinopse, fil_titulo, fil_ano, fil_cat_iden, fil_path)
VALUES( 'Os primeiros passos de Olaf conforme ele ganha vida e busca sua identidade nas montanhas nevadas dos arredores de Arendelle.', 'Era Uma Vez um Boneco de Neve Torrent', 2020, 8, 'C:\Users\roger\Videos\4K Video Downloader\era.mp4');
INSERT INTO public.tb_filmes
( fil_sinopse, fil_titulo, fil_ano, fil_cat_iden, fil_path)
VALUES( 'A Lenda das Sereias – Bristol Cove é uma cidade costeira conhecida por sua lenda urbana de ser o lar das sereias. Quando a chegada de uma garota misteriosa comprova que este folclore é verdadeiro, a batalha entre os seres humanos e os mares toma um rumo perverso.', 'Siren Torrent 3ª Temporada Torrent', 2020, 5, 'C:\Users\roger\Videos\4K Video Downloader\.sirenmp4');
INSERT INTO public.tb_filmes
(fil_sinopse, fil_titulo, fil_ano, fil_cat_iden, fil_path)
VALUES('A épica jornada dos irmãos Winchester finalmente chega ao fim na última temporada de Supernatural. Sam (Jared Padalecki) e Dean (Jensen Ackles) lutou com demônios e anjos, criaturas místicas e monstros, numa aparentemente inacabável batalha para salvar o mundo. Mas agora, eles terão que enfrentar o próprio Deus (Rob Benedict)…', 'Sobrenatural Supernatural Torrent 15ª Temporada', 2020, 7, 'C:\Users\roger\Videos\4K Video Downloader\sobrenatural.mp4');
INSERT INTO public.tb_filmes
( fil_sinopse, fil_titulo, fil_ano, fil_cat_iden, fil_path)
VALUES( 'sinopse teste2', 'filme teste 2', 2020, 13, 'caminho teste2');
INSERT INTO public.tb_filmes
( fil_sinopse, fil_titulo, fil_ano, fil_cat_iden, fil_path)
VALUES( 'sinopse teste1', 'filme teste', 2015, 2, 'caminho teste1');


INSERT INTO public.tb_contratos
( con_status, con_inicio, con_fim, con_usu_iden, con_pla_iden)
VALUES( 'inativo', '2020-11-06', '2020-12-06', 8, 2);
INSERT INTO public.tb_contratos
( con_status, con_inicio, con_fim, con_usu_iden, con_pla_iden)
VALUES('inativo', '2020-11-06', '2020-12-06', 9, 2);
INSERT INTO public.tb_contratos
( con_status, con_inicio, con_fim, con_usu_iden, con_pla_iden)
VALUES( 'A', '2020-11-10', '2021-11-10', 11, 3);
INSERT INTO public.tb_contratos
( con_status, con_inicio, con_fim, con_usu_iden, con_pla_iden)
VALUES( 'A', '2020-11-10', '2021-11-10', 15, 1);
INSERT INTO public.tb_contratos
( con_status, con_inicio, con_fim, con_usu_iden, con_pla_iden)
VALUES( 'A', '2020-11-10', '2021-11-10', 19, 4);



