CREATE UNIQUE INDEX positions_duplicates
    ON positions USING btree (visited_entities)
