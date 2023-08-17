CREATE TABLE states
(
    id BIGSERIAL NOT NULL,

    status STATE_STATUS NOT NULL,

    visited_entities BIT VARYING(2048) NOT NULL,
    reachable_entities BIT VARYING(2048) NOT NULL,

    atk INTEGER NOT NULL,
    def INTEGER NOT NULL,
    hp INTEGER NOT NULL,

    blue_keys INTEGER NOT NULL,
    crimson_keys INTEGER NOT NULL,
    platinum_keys INTEGER NOT NULL,
    violet_keys INTEGER NOT NULL,
    yellow_keys INTEGER NOT NULL,

    moves INTEGER[] NOT NULL,

    PRIMARY KEY (id)
)
