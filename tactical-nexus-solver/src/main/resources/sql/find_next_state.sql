UPDATE states
SET status = ?
WHERE id =
		(SELECT id
			FROM states
			WHERE status = ?
			ORDER BY id DESC
			LIMIT 1
			FOR UPDATE SKIP LOCKED) RETURNING id,
    visited,
    reachable,

    atk,
    def,
    hp,

    blue_keys,
    crimson_keys,
    platinum_keys,
    violet_keys,
    yellow_keys,

	moves
