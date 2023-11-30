UPDATE
    states
SET
    status = ?
WHERE
    id in (
        SELECT
            id
        FROM
            states
        WHERE
            status = ?
        LIMIT
            25 FOR
        UPDATE
            SKIP LOCKED
    ) RETURNING id,
    visited,

    atk,
    def,
    exp,
    hp,

    exp_bonus,
    hp_bonus,

    blue_keys,
    crimson_keys,
    green_blue_keys,
    platinum_keys,
    violet_keys,
    yellow_keys,

    reachable,
    moves,
    one_ways,

    level