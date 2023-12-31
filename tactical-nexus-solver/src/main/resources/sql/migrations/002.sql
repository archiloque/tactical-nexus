CREATE TABLE states
(
    id BIGSERIAL NOT NULL,

    visited BIT VARYING(4096) NOT NULL,

    atk INTEGER NOT NULL,
    def INTEGER NOT NULL,
    exp INTEGER NOT NULL,
    hp INTEGER NOT NULL,

    exp_mult INTEGER NOT NULL,
    hp_mult INTEGER NOT NULL,

    blue_keys SMALLINT NOT NULL,
    crimson_keys SMALLINT NOT NULL,
    green_blue_keys SMALLINT NOT NULL,
    platinum_keys SMALLINT NOT NULL,
    violet_keys SMALLINT NOT NULL,
    yellow_keys SMALLINT NOT NULL,

    status STATE_STATUS NOT NULL,

    reachable BIT VARYING(4096),
    moves SMALLINT[],
    one_ways SMALLINT[],
    level SMALLINT NOT NULL,

    PRIMARY KEY (id)
)
