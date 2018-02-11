package com.libra.apollo.analytics.projection;

import java.util.Date;

public interface ApolloIndicators {

	public Long getStockId();

	public Date getStampDate();

	public Double getOpenPrice();

	public Double getHighPrice();

	public Double getLowPrice();

	public Double getClosePrice();

	public Double getVolume();

	public Double getClosePrice1D();

	public Double getClosePrice5D();

	public Double getClosePrice1M();

	public Double getClosePrice3M();

	public Double getFairValue();
	
	public Double getDiscountToFairValue();
	
	public Double getDiscountPremiumToFairValue();
	
	public Double getStarRating();

	public Double getNetDiscountMedianFairValue();

	public Double getFairValueChange1m();

	public Double getExpectedReturn2m();

	public Double getDiscountPremiumToIntrinsicValue();

	public Double getIntrinsicValueChange3m();

	public Double getFairValueChange3m();

	public Double getIntrinsicValueChange1m();

	public Double getPctInFairValueRange();
	
	public default Double getCalcPctInFairValueRange() {
		return getPctInFairValueRange();
		
	}

}
