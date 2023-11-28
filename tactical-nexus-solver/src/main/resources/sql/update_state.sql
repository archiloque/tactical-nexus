UPDATE states
    SET status = ?, visited = NULL, moves = NULL
    WHERE ID in (?)