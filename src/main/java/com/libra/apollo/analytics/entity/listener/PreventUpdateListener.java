package com.libra.apollo.analytics.entity.listener;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * A simple listener to prevent entity from any updates
 * @author yyermosh
 *
 */
public class PreventUpdateListener  {

	@PrePersist
    void onPrePersist(Object o) {
        throw new UnsupportedOperationException("Entity object can not be persist");
    }

    @PreUpdate
    void onPreUpdate(Object o) {
    	throw new UnsupportedOperationException("Entity object can not be persist");
    }

    @PreRemove
    void onPreRemove(Object o) {
    	throw new UnsupportedOperationException("Entity object can not be persist");
    }
}
