CREATE TABLE cidade (
  id BIGINT AUTO_INCREMENT NOT NULL,
   nome VARCHAR(60) NULL,
   estado_id BIGINT NOT NULL,
   CONSTRAINT pk_cidade PRIMARY KEY (id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci