package com.libra.apollo.analytics.engine.core;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.libra.apollo.analytics.entity.type.IndicatorBigDecimal;

public enum ValueDataFieldType {
	
	//Libra stock price and indicator fields
	DV_CB("DV-CB"),
	DV_CC("DV-CC"),
	DV_CD("DV-CD"),
	DV_CE("DV-CE"),
	DV_CF("DV-CF"),
	DV_CG("DV-CG"),
	DV_CH("DV-CH"),
	DV_CI("DV-CI"),
	DV_CJ("DV-CJ"),
	DV_CK("DV-CK"),
	DV_CL("DV-CL"),
	DV_CM("DV-CM"),
	DV_CN("DV-CN"),
	DV_CO("DV-CO"),
	DV_CP("DV-CP"),
	DV_CR("DV-CR"),
	DV_CU("DV-CU"),
	DV_CX("DV-CX"),
	DV_DA("DV-DA"),
	DV_DD("DV-DD"),
	MOS_FR("MoS-FR"),
	ADX("adx"),
	ADXR("adxr"),
	APOLLO_ALPHA("apolloAlpha"),
	BASE_VALUE("baseValue"),
	BOLLINGER_LOWER("bollingerLower"),
	BOLLINGER_MIDDLE("bollingerMiddle"),
	BOLLINGER_UPPER("bollingerUpper"),
	BOOK_VALUE_FY0("bookValueFY0"),
	BOOK_VALUE_FY0A("bookValueFY0a"),
	BOOK_VALUE_FY1("bookValueFY1"),
	BOOK_VALUE_FY1A("bookValueFY1a"),
	BOOK_VALUE_FY2("bookValueFY2"),
	BOOK_VALUE_FY2A("bookValueFY2a"),
	BOOK_VALUE_FY3("bookValueFY3"),
	BOOK_VALUE_FY3A("bookValueFY3a"),
	CASHFLOW_PER_SHARE_FY0("cashFlowPerShareFY0"),
	CASHFLOW_PER_SHARE_FY0A("cashFlowPerShareFY0a"),
	CASHFLOW_PER_SHARE_FY1("cashFlowPerShareFY1"),
	CASHFLOW_PER_SHARE_FY1A("cashFlowPerShareFY1a"),
	CASHFLOW_PER_SHARE_FY2("cashFlowPerShareFY2"),
	CASHFLOW_PER_SHARE_FY2A("cashFlowPerShareFY2a"),
	CASHFLOW_PER_SHARE_FY3("cashFlowPerShareFY3"),
	CASHFLOW_PER_SHARE_FY3A("cashFlowPerShareFY3a"),
	CLOSE_PRICE("closePrice"),
	CLOSE_PRICE_1M("closePrice1M"),
	CMCI("cmci"),
	CVI_BUY_COUNT("cviBuyCount"),
	CVI_CLOSE_COUNT("cviCloseCount"),
	CVI_COVER_COUNT("cviCoverCount"),
	CVI_SHORT_COUNT("cviShortCount"),
	DAILY_ALPHA_1W("dailyAlpha1W"), 
	DISCOUNT_TO_FAIR_VALUE("discountToFairValue", BigDecimal.class),
	DYNAMIC_LONG_SHORT("dynamicLongShort"),
	DYNAMIC_SHORT_WEIGHTING("dynamicShortWeighting"),
	DYNAMIC_WEIGHTING("dynamicWeighting"),
	EBITDA_FY0("ebitdFY0"),
	EBITDA_FY0A("ebitdFY0a"),
	EBITDA_FY1("ebitdFY1"),
	EBITDA_FY1A("ebitdFY1a"),
	EBITDA_FY2("ebitdFY2"),
	EBITDA_FY2A("ebitdFY2a"),
	EBITDA_FY3("ebitdFY3"),
	EBITDA_FY3A("ebitdFY3a"),
	EFFICIENCY_RATIO("efficiencyRatio"),
	EFFICIENCY_RATIO_SMA_10D("efficiencyRatioSMA10D"),
	EPS_FY0("epsFY0"),
	EPS_FY0A("epsFY0a"),
	EPS_FY1("epsFY1"),
	EPS_FY1_REVISIONS_DOWN("epsFY1RevisionsDown"),
	EPS_FY1_REVISIONS_UNCHANGED("epsFY1RevisionsUnchanged"),
	EPS_FY1_REVISIONS_UP("epsFY1RevisionsUp"),
	EPS_FY1A("epsFY1a"),
	EPS_FY2("epsFY2"),
	EPS_FY2_REVISIONS_DOWN("epsFY2RevisionsDown"),
	EPS_FY2_REVISIONS_UNCHANGED("epsFY2RevisionsUnchanged"),
	EPS_FY2_REVISIONS_UP("epsFY2RevisionsUp"),
	EPS_FY2A("epsFY2a"),
	EPS_FY3("epsFY3"),
	EPS_FY3A("epsFY3a"),
	FAIR_VALUE("fairValue", BigDecimal.class),
	FAIR_VALUE_LOWER("fairValueLower", BigDecimal.class),
	FAIR_VALUE_MIDDLE("fairValueMiddle", BigDecimal.class),
	FAIR_VALUE_PROJECTION("fairValueProjection"),
	FAIR_VALUE_RANGE_FLAGS("fairValueRangeFlags"),
	FAIR_VALUE_RANGE_LIMITS("fairValueRangeLimits"),
	FAIR_VALUE_SLOPE("fairValueSlope"),
	FAIR_VALUE_UPPER("fairValueUpper"),
	FIB_061("fib061"),
	FIB_076("fib076"),
	FY0_END_DATE("fiscalYearEndDate"),
	FY1_END_DATE("fiscalYearEndDateFY1"),
	FLOW("flow"),
	FLOW_MOMENTUM("flowMomentum"),
	FLOW_DRIVER("flowDriver"),
	FORECAST_15D("forecast15d"),
	FORECAST_1D("forecast1d"),
	FORECAST_40D("forecast40d"),
	FORECAST_5D("forecast5d"),
	GREEN_IV_PROJECTION("greenIvProjection"),
	GREEN_CVI("greenCvi"),
	GREEN_FV_PROJECTION("greenFvProjection"),
	GREEN_FORECAST_RETURN("greenForecastReturn"),
	GREEN_ALPHA_BETA("greenAlphaBeta"),
	GREEN_IV_SLOPE("greenIvSlope"),
	GREEN_MOMENTUM("greenMomentum"),
	GREEN_EXTREME_FILTER("greenExtremeFilter"),
	GREEN_RANGE_LIMIT("greenRangeLimit"),
	GREEN_IV_PCT_SCORE("greenIvPctScore"),
	GREEN_VOLATILITY_SCORE("greenVolatilityScore"),
	HIGH_PRICE("highPrice"),
	INTRINSIC_VALUE("intrinsicValue", BigDecimal.class),
	INTRINSIC_VALUE_PCT("intrinsicValuePct", BigDecimal.class),
	INTRINSIC_VALUE_PROJECTION("intrinsicValueProjection"),
	INTRINSIC_VALUE_RSQ("intrinsicValueRsq"),
	INTRINSIC_VALUE_SLOPE("intrinsicValueSlope"),
	INTRINSIC_VALUE_SLOPE_SIGNAL("intrinsicValueSlopeSignal"),
	LOW_PRICE("lowPrice"),
	LOWER_MARGIN_OF_SAFETY_LEVEL("lowerMarginOfSafetyLevel"),
	LTV_FN("LTV-FN"),
	LTV_FO("LTV-FO"),
	LTV_FP("LTV-FP"),
	LTV_GB("LTV-GB"),
	LTV_GU("LTV-GU"),
	MARKET_CAP_TO_SALES_FY1A("marketCapToSalesFY1a"),
	MARKET_CAP_TO_SALES_FY2A("marketCapToSalesFY2a"),
	MEDIAN_DISCOUNT_TO_FAIR_VALUE("medianDiscountToFairValue"),
	MERGER_VALUE("mergerValue"), 
	MOVE_TO_INTRINSIC_VALUE_PROJECTION("moveToIntrinsicValueProjection"),
	MOVE_TO_FAIR_VALUE_PROJECTION("moveToFairValueProjection"),
	NEW_TARGET_RETURN("newTargetReturn"), 
	NO_EPS_FY1("noEPSFY1"),
	OPEN_PRICE("openPrice"),
	PRICE_TO_BOOK_FY1A("priceToBookFY1a"),
	PRICE_TO_CASHFLOW_FY1A("priceToCashFlowFY1a"),
	PRICE_TO_EARNINGS_FY1A("priceToEarningsFY1a"),
	PRICE_TO_EBITDA_FY1A("priceToEbitdaFY1a"),
	PRICE_TO_BOOK_FY2A("priceToBookFY2a"),
	PRICE_TO_CASHFLOW_FY2A("priceToCashFlowFY2a"),
	PRICE_TO_EARNINGS_FY2A("priceToEarningsFY2a"),
	PRICE_TO_EBITDA_FY2A("priceToEbitdaFY2a"),
	PTPS_SAR("ptpsSAR"),
	PTPS_TREND("ptpsTrend"),
	RANGE_EXP20D_MINUS("rangeExp20dMinus"),
	RANGE_EXP20D_PLUS("rangeExp20dPlus"),
	RAW_FAIR_VALUE("rawFairValue"),
	RED_IV_PROJECTION("redIvProjection"),
	RED_CVI("redCvi"),
	RED_FV_PROJECTION("redFvProjection"),
	RED_FORECAST_RETURN("redForecastReturn"),
	RED_ALPHA_BETA("redAlphaBeta"),
	RED_IV_SLOPE("redIvSlope"),
	RED_MOMENTUM("redMomentum"),
	RED_EXTREME_FILTER("redExtremeFilter"),
	RED_RANGE_LIMIT("redRangeLimit"),
	RED_IV_PCT_SCORE("redIvPctScore"),
	RED_VOLATILITY_SCORE("redVolatilityScore"),
	RETURN_ON_EQUITY_FY1A("returnOnEquityFY1a"),
	RISK("risk"),
	RSI_14D("rsi14D"),
	SALES_FY0("salesFY0"),
	SALES_FY0A("salesFY0a"),
	SALES_FY1("salesFY1"),
	SALES_FY1A("salesFY1a"),
	SALES_FY2("salesFY2"),
	SALES_FY2A("salesFY2a"),
	SALES_FY3("salesFY3"),
	SALES_FY3A("salesFY3a"),
	SENTIMENT("sentiment"),
	SHARES_OUTSTANDING("sharesOutstanding"),
	SKEW("skew"),
	SMA_20D("sma20D"),
	SMA_50D("sma50D"),
	SMA_200D("sma200D"),
	SMOOTHED_FAIR_VALUE("smoothedFairValue"),
	STAMP_DATE("stampDate",Date.class),
	MAX_STAMP_DATE("maxStampDate",Date.class),
	STAR_RATING("starRating",BigDecimal.class),
	STOCHASTICS_PERCENT_K("stochasticsPercentK"),
	STOCHASTICS_PERCENT_D("stochasticsPercentD"),
	STOCK_INDEX_BETA("stockIndexBeta"),
	STRETCH_VALUE("stretchValue"), 
	SWEET_SPOT("sweetSpot"),
	TARGET_20D("target20D"),
	TARGET_20D_RETURN("target20DReturn"),
	TARGET_2M("target2M"),
	TRADING_FLAGS("tradingFlags"),
	TRIGGER_LEVEL("triggerLevel"), 
	UPPER_MARGIN_OF_SAFETY_LEVEL("upperMarginOfSafetyLevel"),
	VALUATION_TRADING_FLAGS("valuationTradingFlags"),
	VOLUME("volume"),
	WEIGHTING_BIAS("weightingBias"),
	YEAR2_FAIR_VALUES("year2FairValues"),
	YEAR3_FAIR_VALUES("year3FairValues"),
	WILLIAMS("williams"),
	
	//Index aggregation/breadth/deciles
	MONEY_MANAGER_ADVICE("moneyManagerAdvice"),
	MARKET_CAP("marketCap"),
	DECILE_1_20D("decile_1_20D"),
	DECILE_2_20D("decile_2_20D"),
	DECILE_3_20D("decile_3_20D"),
	DECILE_4_20D("decile_4_20D"),
	DECILE_5_20D("decile_5_20D"),
	DECILE_6_20D("decile_6_20D"),
	DECILE_7_20D("decile_7_20D"),
	DECILE_8_20D("decile_8_20D"),
	DECILE_9_20D("decile_9_20D"),
	STOCK_ID("stockId",Long.class),
	INSTRUMENT_ID("instrumentId",Long.class),
	
	//---------- ACUS indicators -------------//
	PRICE_CHANGE_1M("priceChange1m", BigDecimal.class),
	DISCOUNT_PREMIUM_TO_FAIR_VALUE("discountPremiumToFairValue", BigDecimal.class),
	NET_DISCOUNT_MEDIAN_FAIR_VALUE("netDiscountMedianFairValue", BigDecimal.class),
	FAIR_VALUE_CHANGE_1M("fairValueChange1m", BigDecimal.class),
	VALUE_INDICATOR_SCORE("valueIndicatorScore",BigDecimal.class),
	EXPECTED_RETURN_2M("expectedReturn2m", BigDecimal.class),
	DISCOUNT_PREMIUM_TO_INTRINSIC_VALUE("discountPremiumToIntrinsicValue", BigDecimal.class),
	INTRINSIC_VALUE_CHANGE_3M("intrinsicValueChange3m", BigDecimal.class),
	FAIR_VALUE_CHANGE_3M("fairValueChange3m", BigDecimal.class),
	INTRINSIC_VALUE_CHANGE_1M("intrinsicValueChange1m", BigDecimal.class),
	PCT_IN_FAIR_VALUE_RANGE("pctInFairValueRange", BigDecimal.class),
	LONG_TERM_PESSIMISTIC("longTermPessimistic", BigDecimal.class),
	LONG_TERM_OPTIMISTIC("longTermOptimistic", BigDecimal.class)
	;
	
	
	private final String fieldName;
	private Class<?> clazz;
	
	@JsonValue
	public String getFieldName() {
		return fieldName;
	}

	private ValueDataFieldType(final String name) {
		this.fieldName = name;
	}
	private ValueDataFieldType(final String name, final Class<?> clazz) {
		this.fieldName = name;
		this.clazz = clazz;
	}
	
	@Override
    public String toString() {
        return fieldName;
    }
	
	
	
	public Class<?> getClazz() {
		return clazz;
	}

	/**
	 * Converts the string to its equivalent InstrumentDataFieldType or null if it doesn't exist
	 * @param name		Field name to convert
	 * @return			The converted InstrumentDataFieldType
	 */
	@JsonCreator
	public static ValueDataFieldType convertToInstrumentDataFieldType(String name) {
		for(ValueDataFieldType field: ValueDataFieldType.values()) {
			if(field.toString().equals(name)) {
				return field;
			}
		}
		
		return null;
	}
}
