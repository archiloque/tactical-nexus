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
        ORDER BY
            id DESC
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
    greenblue_keys,
    platinum_keys,
    violet_keys,
    yellow_keys,

    reachable,
    moves,

    level