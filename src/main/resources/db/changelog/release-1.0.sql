--liquibase formatted sql

--changeset Swaroop:MatchStatusReferenceTableGeneration(dbms:postgresql failOnError:true splitStatements:false)
CREATE TABLE ref_match_status (
  status_type_id BIGSERIAL PRIMARY KEY,
  status         TEXT NOT NULL
);
--rollback SELECT 1;

--changeset Swaroop:RoundReferenceTableGeneration(dbms:postgresql failOnError:true splitStatements:false)
CREATE TABLE ref_rounds (
  round_id   BIGSERIAL PRIMARY KEY,
  round_name TEXT NOT NULL
);
--rollback SELECT 1;

--changeset Swaroop:SportsReferenceTableGeneration(dbms:postgresql failOnError:true splitStatements:false)
CREATE TABLE ref_sports (
  sport_id BIGSERIAL PRIMARY KEY,
  name     TEXT NOT NULL
);
--rollback SELECT 1;

--changeset Swaroop:BracketSchemaGeneration(dbms:postgresql failOnError:true splitStatements:false)
CREATE TABLE brackets (
  bracket_id        BIGSERIAL PRIMARY KEY,
  name              TEXT    NOT NULL,
  type              INTEGER NOT NULL,
  sport_id          BIGINT REFERENCES ref_sports (sport_id),
  participant_count INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE bracket_teams (
  team_id    BIGSERIAL PRIMARY KEY,
  bracket_id BIGINT REFERENCES brackets (bracket_id),
  team_name  TEXT NOT NULL,
  seed       INTEGER,
  UNIQUE (bracket_id, team_name)
);

CREATE TABLE bracket_participants (
  participant_id   BIGSERIAL PRIMARY KEY,
  bracket_id       BIGINT REFERENCES brackets (bracket_id),
  participant_name TEXT NOT NULL,
  team_id          TEXT NOT NULL,
  seed             INTEGER,
  UNIQUE (bracket_id, participant_id)
);

CREATE TABLE bracket_matches (
  match_id        BIGSERIAL PRIMARY KEY,
  bracket_id      BIGINT REFERENCES brackets (bracket_id),
  match_seq       INT NOT NULL,
  participant_id1 BIGINT REFERENCES bracket_participants (participant_id),
  participant_id2 BIGINT REFERENCES bracket_participants (participant_id),
  round           BIGINT REFERENCES ref_rounds (round_id),
  winner_id       BIGINT REFERENCES bracket_participants (participant_id),
  UNIQUE (bracket_id, match_id),
  UNIQUE (bracket_id, match_seq)
);
--rollback SELECT 1;
