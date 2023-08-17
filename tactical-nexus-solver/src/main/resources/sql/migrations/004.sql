CREATE UNIQUE INDEX states_duplicates
    ON states USING btree (
    visited_entities,
    reachable_entities,

    atk,
    def,

    hp,
    blue_keys,
    crimson_keys,
    platinum_keys,
    violet_keys,
    yellow_keys
    )
