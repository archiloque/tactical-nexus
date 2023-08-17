UPDATE positions
SET status = ?
WHERE id =
		(SELECT id
			FROM positions
			WHERE status = ?
			LIMIT 1
			FOR UPDATE SKIP LOCKED) RETURNING id,
    visited_entities,
    reachable_entities,

    atk,
    def,
    hp,

    blue_keys,
    crimson_keys,
    platinum_keys,
    violet_keys,
    yellow_keys,

	moves
