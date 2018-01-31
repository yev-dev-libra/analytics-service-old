package com.libra.apollo.analytics.projection;

import java.math.BigDecimal;
import java.util.Date;

public interface ScreeningResult {

	Long getStockId();

	Date getStampDate();

	BigDecimal getFairValue();
}
