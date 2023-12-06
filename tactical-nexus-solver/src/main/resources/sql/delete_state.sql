DELETE FROM STATES WHERE
    visited = ? and

    atk >= ? and
    def >= ? and
    exp >= ? and
    hp >= ? and
    exp_mult <= ? and
    hp_mult <= ? and
    blue_keys <= ? and
    crimson_keys <= ? and
    green_blue_keys <= ? and
    platinum_keys <= ? and
    violet_keys <= ? and
    yellow_keys <= ? and

    status = ? and

    id != ?