INSERT INTO STATES(
    status,
    visited,
    reachable,

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

	moves)
	SELECT
	    ?,
	    ?, ?,
	    ?, ?, ?, ?,
	    ?, ?,
	    ?, ?, ?, ?, ?,
	    ?
	WHERE NOT EXISTS (
	SELECT id FROM STATES
	WHERE visited = ?
	and reachable = ?

	and atk >= ?
	and def >= ?
	and exp >= ?
	and hp >= ?

	and exp_bonus >= ?
	and hp_bonus >= ?

	and blue_keys >= ?
	and crimson_keys >= ?
	and platinum_keys >= ?
	and violet_keys >= ?
	and yellow_keys >= ?
		) RETURNING id