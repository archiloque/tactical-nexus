CREATE UNIQUE INDEX positions_duplicates
    ON positions USING btree (
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
