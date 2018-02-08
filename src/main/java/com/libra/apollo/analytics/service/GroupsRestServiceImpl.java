package com.libra.apollo.analytics.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.ImmutableList;
import com.libra.apollo.group.api.GroupsRestControllerApi;
import com.libra.apollo.group.v1client.domain.GroupExtendedWithType;
import com.libra.apollo.group.v1client.domain.StockBasic;

@Service
public class GroupsRestServiceImpl implements GroupsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
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
		List<Long> ids = ImmutableList.copyOf(groupIds);
		List<GroupExtendedWithType> response = api.getGroupsWithListIdentifiersUsingGET(ids );
		
		if(response == null || response.isEmpty()) {
			return Collections.emptyMap();
		}
		
		Map<Long, Collection<Long>> stockGroups = new HashMap<>();
		for (GroupExtendedWithType group : response) {
			
			final Long groupId = group.getId();
			for(StockBasic stock : group.getMemberships()) {
				
				final Long stockId = stock.getId();
				
				if(stockId != null) {
					Collection<Long> existedGroups = null;
					if(stockGroups.containsKey(stockId)) {
						existedGroups = stockGroups.get(stockId);
					}
					else {
						existedGroups = new LinkedList<>();
					}
					existedGroups.add(groupId);
					stockGroups.put(stockId, existedGroups);
				}
				else {
					logger.debug("Group Id:" + groupId + " does not have any associated stock ids");
				}
			}
		}
		return stockGroups;
	}

}
