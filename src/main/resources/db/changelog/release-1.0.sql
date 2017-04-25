--liquibase formatted sql

--changeset Swaroop:ResultTypeReferenceTableGeneration(dbms:postgresql failOnError:true splitStatements:false)
CREATE TABLE ref_result_type (
  result_type_id BIGSERIAL PRIMARY KEY,
  result_type TEXT NOT NULL
);
--rollback SELECT 1;

--changeset Swaroop:RoundReferenceTableGeneration(dbms:postgresql failOnError:true splitStatements:false)
CREATE TABLE ref_round (
  round_id BIGSERIAL PRIMARY KEY,
  round_name TEXT NOT NULL
);
--rollback SELECT 1;

--changeset Swaroop:BracketSchemaGeneration(dbms:postgresql failOnError:true splitStatements:false)
CREATE TABLE brackets (
  bracket_id BIGSERIAL PRIMARY KEY,
  name TEXT NOT NULL,
  type INTEGER NOT NULL,
  sport INTEGER,
  participant_count INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE bracket_teams (
  team_id BIGSERIAL PRIMARY KEY,
  bracket_id BIGINT REFERENCES brackets (bracket_id),
  team_name TEXT NOT NULL,
  seed INTEGER,
  UNIQUE (bracket_id, team_name)
);

CREATE TABLE bracket_participants (
  participant_id BIGSERIAL PRIMARY KEY,
  bracket_id BIGINT REFERENCES brackets (bracket_id),
  participant_name TEXT NOT NULL,
  seed INTEGER,
  UNIQUE (bracket_id, participant_id)
);

CREATE TABLE bracket_matches (
  match_id BIGSERIAL PRIMARY KEY,
  bracket_id BIGINT REFERENCES brackets (bracket_id),
  match_seq INT NOT NULL,
  participant_id1 BIGINT REFERENCES bracket_participants (participant_id),
  participant_id2 BIGINT REFERENCES bracket_participants (participant_id),
  team_id1 BIGINT REFERENCES bracket_teams (team_id),
  team_id2 BIGINT REFERENCES bracket_teams (team_id),
  round BIGINT REFERENCES ref_round (round_id),
  result BIGINT REFERENCES ref_result_type (result_type_id),
  score_id BIGINT DEFAULT 0,
  UNIQUE (bracket_id, match_id),
  UNIQUE (bracket_id, match_seq)
);
--rollback SELECT 1;
