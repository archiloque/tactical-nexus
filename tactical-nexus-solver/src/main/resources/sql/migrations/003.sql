CREATE INDEX states_new
    ON states USING btree (status)
    WHERE status = 'new'
