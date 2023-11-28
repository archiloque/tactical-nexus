DELETE FROM STATES WHERE
    visited = ? and

    atk >= ? and
    def >= ? and
    exp >= ? and
    hp >= ? and
    exp_bonus <= ? and
    hp_bonus <= ? and
    blue_keys <= ? and
    crimson_keys <= ? and
    greenblue_keys <= ? and
    platinum_keys <= ? and
    violet_keys <= ? and
    yellow_keys <= ? and

    status = ? and

    id != ?