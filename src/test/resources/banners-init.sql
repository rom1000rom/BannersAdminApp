
DROP TABLE  IF EXISTS banners CASCADE;
CREATE TABLE banners
(
        banner_id serial NOT NULL,
        img_src TEXT NOT NULL,
        width integer NOT NULL,
        height integer NOT NULL,
        target_url TEXT NOT NULL,
        lang_id varchar(50) NOT NULL,

    CONSTRAINT "banners_pk" PRIMARY KEY ("banner_id")
) WITH
    (
       OIDS=FALSE
    );

INSERT INTO banners( img_src, width, height, target_url, lang_id )
VALUES ( 'TEST', 0, 0, 'TEST', 'TEST'),
       ( 'TEST', 2, 3, 'TEST2', 'TEST3');