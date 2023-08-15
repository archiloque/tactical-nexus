CREATE INDEX positions_new
    ON positions USING btree (status)
    WHERE status = 'new'
