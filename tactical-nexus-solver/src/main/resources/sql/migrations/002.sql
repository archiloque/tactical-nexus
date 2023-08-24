CREATE TABLE states
(
    id BIGSERIAL NOT NULL,

    atk INTEGER NOT NULL,
    def INTEGER NOT NULL,
    exp INTEGER NOT NULL,
    hp INTEGER NOT NULL,

    exp_bonus INTEGER NOT NULL,
    hp_bonus INTEGER NOT NULL,

    blue_keys INTEGER NOT NULL,
    crimson_keys INTEGER NOT NULL,
    platinum_keys INTEGER NOT NULL,
    violet_keys INTEGER NOT NULL,
    yellow_keys INTEGER NOT NULL,

    visited BIT VARYING(4096) NOT NULL,
    reachable BIT VARYING(4096) NOT NULL,

    status STATE_STATUS NOT NULL,

    moves INTEGER[] NOT NULL,

    PRIMARY KEY (id)
)
