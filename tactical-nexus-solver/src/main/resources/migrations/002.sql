CREATE TABLE positions
(
    id bigserial NOT NULL,
    visited_entities bit varying(2048) NOT NULL,
    reachable_entities bit varying(2048) NOT NULL,
    moves integer[] NOT NULL,
    status POSITION_STATUS NOT NULL,
    PRIMARY KEY (id)
)
