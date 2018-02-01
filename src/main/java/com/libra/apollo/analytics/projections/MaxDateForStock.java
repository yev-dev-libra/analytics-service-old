package com.libra.apollo.analytics.projections;

import java.util.Date;

public interface MaxDateForStock {

	Date getMaxStampDate();
	Long getStockId();
}
