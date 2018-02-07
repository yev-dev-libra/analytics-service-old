package com.libra.apollo.analytics.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.libra.apollo.group.api.GroupsRestControllerApi;

@Service
public class GroupsRestServiceImpl implements GroupsService {

	private final GroupsRestControllerApi api = new GroupsRestControllerApi();

	@Override
	public Collection<Long> getStockIdsForGroupIds(Collection<Long> groupIds) {
		if (groupIds.contains(1L)) {
			return Arrays.asList(1L);
		} else
			return Collections.emptyList();
	}

	@Override
	public Map<Long, Collection<Long>> getStockIdsWithGroups(Collection<Long> groupIds) {
		if (groupIds.contains(1L)) {

			Map<Long, Collection<Long>> values = new HashMap<>();
			values.put(1L, Arrays.asList(1L, 2L));
			values.put(2L, Arrays.asList(1L, 2L));
			values.put(3L, Arrays.asList(1L));

			return values;
		} else
			return Collections.emptyMap();
	}

}
