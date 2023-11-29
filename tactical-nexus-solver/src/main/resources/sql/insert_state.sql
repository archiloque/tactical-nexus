INSERT INTO
	states(
		status,

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
	)
SELECT
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?,
	?
WHERE
	NOT EXISTS (
		SELECT
			1
		FROM
			states
		WHERE
			visited = ?
			and atk >= ?
			and def >= ?
			and exp >= ?
			and hp >= ?
			and exp_bonus >= ?
			and hp_bonus >= ?
			and blue_keys >= ?
			and green_blue_keys >= ?
			and crimson_keys >= ?
			and platinum_keys >= ?
			and violet_keys >= ?
			and yellow_keys >= ?
	) ON CONFLICT(
		visited,
		atk,
		def,
		exp,
		hp,
		exp_bonus,
		hp_bonus,
		blue_keys,
		green_blue_keys,
		crimson_keys,
		platinum_keys,
		violet_keys,
		yellow_keys
	) DO NOTHING RETURNING id