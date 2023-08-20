CREATE INDEX states_new
    ON states USING btree (status, id DESC)
    WHERE status = 'new'
