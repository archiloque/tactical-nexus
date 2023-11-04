UPDATE states
    SET status = ?, reachable = NULL, moves = NULL
    WHERE ID in (?)