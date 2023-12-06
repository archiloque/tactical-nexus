CREATE UNIQUE INDEX states_duplicates
    ON states USING btree (
    visited,

    atk,
    def,
    exp,
    hp,

    exp_mult,
    hp_mult,

    blue_keys,
    green_blue_keys,
    crimson_keys,
    platinum_keys,
    violet_keys,
    yellow_keys
    )
