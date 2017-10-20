package com.libra.apollo.analytics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libra.apollo.analytics.repository.AnalyticsRepository;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

	@Autowired
	private AnalyticsRepository analyticsRepository;
}
