package com.libra.apollo.analytics.service;

import java.util.Collection;
import java.util.Map;

public interface GroupsService {

	Collection<Long> getStockIdsForGroupIds(Collection<Long> groupIds);
	Map<Long,Collection<Long>> getStockIdsWithGroups(Collection<Long> groupIds);
}
