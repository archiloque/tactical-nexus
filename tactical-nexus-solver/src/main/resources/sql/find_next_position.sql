UPDATE POSITIONS
SET STATUS = ?
WHERE ID =
		(SELECT ID
			FROM POSITIONS
			WHERE STATUS = ?
			LIMIT 1
			FOR UPDATE SKIP LOCKED) RETURNING ID,
	VISITED_ENTITIES,
	REACHABLE_ENTITIES,
	MOVES