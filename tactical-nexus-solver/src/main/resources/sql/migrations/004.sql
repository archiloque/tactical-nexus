CREATE UNIQUE INDEX states_duplicates
    ON states USING btree (
    atk,
    def,
    exp,
    hp,

    exp_bonus,
    hp_bonus,

    blue_keys,
    crimson_keys,
    platinum_keys,
    violet_keys,
    yellow_keys,

    visited,
    reachable

    )
