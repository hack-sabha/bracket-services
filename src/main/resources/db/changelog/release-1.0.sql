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

--changeset Swaroop:TournamentSchemaGeneration(dbms:postgresql failOnError:true splitStatements:false)
CREATE TABLE tournaments (
  tournament_id        BIGSERIAL PRIMARY KEY,
  name              TEXT    NOT NULL,
  type              INTEGER NOT NULL,
  sport_id          BIGINT REFERENCES ref_sports (sport_id),
  participant_count INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE tournament_teams (
  team_id    BIGSERIAL PRIMARY KEY,
  tournament_id BIGINT REFERENCES tournaments (tournament_id) NOT NULL,
  team_name  TEXT NOT NULL,
  seed       INTEGER,
  UNIQUE (tournament_id, team_name)
);

CREATE TABLE tournament_participants (
  participant_id   BIGSERIAL PRIMARY KEY,
  tournament_id    BIGINT REFERENCES tournaments (tournament_id) NOT NULL,
  participant_name TEXT NOT NULL,
  team_id          BIGINT REFERENCES tournament_teams(team_id),
  seed             INTEGER,
  UNIQUE (tournament_id, participant_id)
);

CREATE TABLE tournament_matches (
  match_id        BIGSERIAL PRIMARY KEY,
  tournament_id      BIGINT REFERENCES tournaments (tournament_id),
  match_seq       INT NOT NULL,
  participant_id1 BIGINT REFERENCES tournament_participants (participant_id),
  participant_id2 BIGINT REFERENCES tournament_participants (participant_id),
  round           BIGINT REFERENCES ref_rounds (round_id),
  winner_id       BIGINT REFERENCES tournament_participants (participant_id),
  UNIQUE (tournament_id, match_id),
  UNIQUE (tournament_id, match_seq)
);
--rollback SELECT 1;
