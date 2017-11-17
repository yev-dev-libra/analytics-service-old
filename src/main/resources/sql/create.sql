
    create table analytics.analytics_view (
        id bigint generated by default as identity,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        description varchar(255),
        name varchar(255) not null,
        priority integer,
        analytics_id bigint,
        parent_id bigint,
        primary key (id)
    )

    create table analytics.apollo_analytics (
        id bigint generated by default as identity,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        description varchar(255),
        name varchar(255) not null,
        run_type varchar(255) not null,
        analytics_type varchar(255) not null,
        primary key (id)
    )

    create table analytics.conditions (
        kind varchar(31) not null,
        id bigint generated by default as identity,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        operator varchar(255),
        data_field_type varchar(255),
        parameter double,
        sort_direction varchar(255),
        primary key (id)
    )

    create table analytics.data_source (
        id bigint generated by default as identity,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        clazz_name varchar(255),
        data_source_type varchar(255) not null,
        url_base varchar(255),
        primary key (id)
    )

    create table analytics.investment_style (
        id bigint generated by default as identity,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        description varchar(255),
        name varchar(255) not null,
        priority integer,
        analytics_id bigint,
        parent_id bigint,
        view_id bigint,
        primary key (id)
    )

    create table analytics.investment_style_condition (
        id bigint generated by default as identity,
        priority integer,
        condition_id bigint,
        investment_style_id bigint,
        primary key (id)
    )

    create table analytics.investment_style_datasource (
        id bigint not null,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        data_source_id bigint not null,
        investment_style_id bigint not null,
        primary key (id)
    )
create index analytics.type_idx on analytics.apollo_analytics (analytics_type)
create index analytics.run_type_idx on analytics.apollo_analytics (run_type)

    create table apollo.stockindicators (
        id bigint generated by default as identity,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        adr decimal(19,4),
        adx decimal(19,4),
        apollo_alpha decimal(19,4),
        base_value decimal(19,4),
        bollinger_lower decimal(19,4),
        bollinger_middle decimal(19,4),
        bollinger_upper decimal(19,4),
        cmci decimal(19,4),
        cmci_score decimal(19,4),
        conditional_direction_forecast decimal(19,4),
        cvi decimal(19,4),
        cvi_buy_count integer,
        cvi_buy_signal boolean,
        cvi_close_count integer,
        cvi_close_signal boolean,
        cvi_cover_count integer,
        cvi_cover_signal boolean,
        cvi_fv_flag boolean,
        cvi_iv_flag boolean,
        cvi_range_downside decimal(19,4),
        cvi_range_upside decimal(19,4),
        cvi_short_count integer,
        cvi_short_signal boolean,
        daily_alpha_1d decimal(19,4),
        daily_alpha_1w decimal(19,4),
        daily_alpha_3w decimal(19,4),
        daily_alpha_8w decimal(19,4),
        day_index integer,
        dfv_score decimal(10,4),
        discount_to_fair_value decimal(19,4),
        dm_score_bookvalue decimal(19,4),
        dm_score_cashflow decimal(19,4),
        dm_score_earnings decimal(19,4),
        dm_score_ebitda decimal(19,4),
        dm_score_sales decimal(19,4),
        dmi_negative decimal(19,4),
        dmi_positive decimal(19,4),
        dmi_score decimal(19,4),
        dynamic_long_short decimal(10,4),
        dynamic_short_weighting decimal(10,4),
        dynamic_weighting decimal(10,4),
        efficiency_ratio decimal(19,4),
        efficiency_ratio_sma10d decimal(19,4),
        efficiency_score decimal(19,4),
        eps_fy1_pct_change_1d decimal(10,4),
        eps_fy1_pct_change_1m decimal(10,4),
        eps_fy1_pct_change_3m decimal(10,4),
        eps_fy1_pct_change_5d decimal(10,4),
        eps_trend decimal(19,4),
        estimates_fy1 decimal(19,4),
        estimates_fy2 decimal(19,4),
        fair_value decimal(19,4),
        fair_value_lower decimal(19,4),
        fair_value_middle decimal(19,4),
        fair_value_projection decimal(19,4),
        fair_value_range_flag boolean,
        fair_value_range_limits boolean,
        fair_value_slope decimal(19,4),
        fair_value_upper decimal(19,4),
        fib_023 decimal(19,4),
        fib_038 decimal(19,4),
        fib_050 decimal(19,4),
        fib_061 decimal(19,4),
        fib_076 decimal(19,4),
        fireline integer,
        flow decimal(19,4),
        flow_driver decimal(19,4),
        flow_driver_score decimal(10,4),
        flow_momentum decimal(19,4),
        flow_momentum_score decimal(10,4),
        flow_score decimal(10,4),
        fractional_buy boolean,
        fractional_sell boolean,
        green_alpha_beta decimal(19,4),
        green_cvi decimal(19,4),
        green_extreme_filter decimal(19,4),
        green_forecast_return decimal(19,4),
        green_fv_projection decimal(19,4),
        green_iv_pct_score decimal(19,4),
        green_iv_projection decimal(19,4),
        green_iv_slope decimal(19,4),
        green_momentum decimal(19,4),
        green_range_limit decimal(19,4),
        green_volatility_score decimal(19,4),
        high_price_52w decimal(19,4),
        intrinsic_value decimal(19,4),
        intrinsic_value_pct double,
        intrinsic_value_projection decimal(19,4),
        intrinsic_value_rsq decimal(19,4),
        intrinsic_value_slope decimal(19,4),
        intrinsic_value_slope_signal boolean,
        intrinsic_value_volatility double,
        last_20d_performance decimal(19,4),
        long_term_neutral decimal(19,4),
        long_term_optimistic decimal(19,4),
        long_term_pb_forecast decimal(19,4),
        long_term_pcf_forecast decimal(19,4),
        long_term_pebitda_forecast decimal(19,4),
        long_term_pe_forecast decimal(19,4),
        long_term_ps_forecast decimal(19,4),
        long_term_pessimistic decimal(19,4),
        long_term_value decimal(19,4),
        low_price_52w decimal(19,4),
        lower_margin_of_safety_level decimal(19,4),
        macd1 decimal(19,4),
        macd2 decimal(19,4),
        macd_score decimal(19,4),
        macd_signal decimal(19,4),
        median_discount_to_fair_value decimal(19,4),
        merger_value decimal(19,4),
        money_manager integer,
        money_manager_advice varchar(255),
        move_to_fair_value_projection decimal(19,4),
        move_to_intrinsic_value_projection decimal(19,4),
        new_target decimal(19,4),
        new_target_return decimal(19,4),
        oscillator decimal(19,4),
        oscillator_score decimal(19,4),
        pb_driver decimal(19,4),
        pb_lower decimal(19,4),
        pb_maximum decimal(19,4),
        pb_minimum decimal(19,4),
        pb_second_order decimal(19,4),
        pb_third_order decimal(19,4),
        pb_upper decimal(19,4),
        pb_value decimal(19,4),
        pb_tpm decimal(19,4),
        pcf_maximum decimal(19,4),
        pcf_minimum decimal(19,4),
        pcf_driver decimal(19,4),
        pcf_lower decimal(19,4),
        pcf_second_order decimal(19,4),
        pcf_third_order decimal(19,4),
        pcf_upper decimal(19,4),
        pcf_value decimal(19,4),
        pcf_tpm decimal(19,4),
        pct_change_ytd decimal(19,4),
        pe_driver decimal(19,4),
        pe_lower decimal(19,4),
        pe_maximum decimal(19,4),
        pe_minimum decimal(19,4),
        pe_second_order decimal(19,4),
        pe_third_order decimal(19,4),
        pe_upper decimal(19,4),
        pe_value decimal(19,4),
        pebitda_driver decimal(19,4),
        pebitda_lower decimal(19,4),
        pebitda_maximum decimal(19,4),
        pebitda_minimum decimal(19,4),
        pebitda_second_order decimal(19,4),
        pebitda_third_order decimal(19,4),
        pebitda_upper decimal(19,4),
        pebitda_value decimal(19,4),
        pebitda_tpm decimal(19,4),
        pe_tpm decimal(19,4),
        price_driver decimal(19,4),
        ps_driver decimal(19,4),
        ps_lower decimal(19,4),
        ps_maximum decimal(19,4),
        ps_minimum decimal(19,4),
        ps_second_order decimal(19,4),
        ps_third_order decimal(19,4),
        ps_upper decimal(19,4),
        ps_value decimal(19,4),
        ps_tpm decimal(19,4),
        ptps_af decimal(19,4),
        ptps_sar decimal(19,4),
        ptps_trend varchar(255),
        range_exp_20d_minus decimal(19,4),
        range_exp_20d_plus decimal(19,4),
        red_alpha_beta decimal(19,4),
        red_cvi decimal(19,4),
        red_extreme_filter decimal(19,4),
        red_forecast_return decimal(19,4),
        red_fv_projection decimal(19,4),
        red_iv_pct_score decimal(19,4),
        red_iv_projection decimal(19,4),
        red_iv_slope decimal(19,4),
        red_momentum decimal(19,4),
        red_range_limit decimal(19,4),
        red_volatility_score decimal(19,4),
        revisions decimal(19,4),
        revisions1m decimal(19,4),
        revisions3m decimal(19,4),
        risk decimal(19,4),
        roe_maximum decimal(19,4),
        roe_minimum decimal(19,4),
        rsi_14d decimal(19,4),
        rsi_score decimal(19,4),
        sentiment decimal(19,4),
        skew decimal(19,4),
        sma_200d decimal(19,4),
        sma_20d decimal(19,4),
        sma_50d decimal(19,4),
        stamp_date date not null,
        star_rating decimal(19,4),
        stochastics_percent_d decimal(19,4),
        stochastics_percent_k decimal(19,4),
        stochastic_score decimal(19,4),
        instrument_id bigint,
        stock_index_beta decimal(19,4),
        stretch_value decimal(19,4),
        sweet_spot boolean,
        target20d decimal(19,4),
        target_20d_return decimal(19,4),
        target2m decimal(19,4),
        trigger_level decimal(19,4),
        upper_margin_of_safety_level decimal(19,4),
        valuation_indicator decimal(19,4),
        valuation_indicator_10d decimal(19,4),
        valuation_trading_flags varchar(8),
        value_indicator_score decimal(10,4),
        volume_avg_3m decimal(19,4),
        volume_score decimal(10,4),
        williams decimal(19,4),
        williams_score decimal(19,4),
        primary key (id)
    )

    alter table analytics.analytics_view 
        add constraint FK59uq9qa9h4y3abowcrfkceqso 
        foreign key (analytics_id) 
        references analytics.apollo_analytics

    alter table analytics.analytics_view 
        add constraint FKptjwdubiog4vq0aryvb3jtbts 
        foreign key (parent_id) 
        references analytics.analytics_view

    alter table analytics.investment_style 
        add constraint FKoc58scqab2fc7j2xjqod56en8 
        foreign key (analytics_id) 
        references analytics.apollo_analytics

    alter table analytics.investment_style 
        add constraint FKaqi6vc07xy4f3rjsiko7wdr8p 
        foreign key (parent_id) 
        references analytics.investment_style

    alter table analytics.investment_style 
        add constraint FK71jdxs3e3ggtm5jy267dw0diy 
        foreign key (view_id) 
        references analytics.analytics_view

    alter table analytics.investment_style_condition 
        add constraint FKovjkyxkhwmk4hm162pel1smox 
        foreign key (condition_id) 
        references analytics.conditions

    alter table analytics.investment_style_condition 
        add constraint FK8ocbv7t03rce0gbgdck1lvyh5 
        foreign key (investment_style_id) 
        references analytics.investment_style

    alter table analytics.investment_style_datasource 
        add constraint FK17jfgncr8lnbfb8d2aptukbrv 
        foreign key (data_source_id) 
        references analytics.data_source

    alter table analytics.investment_style_datasource 
        add constraint FK3yg6avpqn2jkyhugoxvuddha0 
        foreign key (investment_style_id) 
        references analytics.investment_style

    create table analytics.analytics_view (
        id bigint generated by default as identity,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        description varchar(255),
        name varchar(255) not null,
        priority integer,
        analytics_id bigint,
        parent_id bigint,
        primary key (id)
    )

    create table analytics.apollo_analytics (
        id bigint generated by default as identity,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        description varchar(255),
        name varchar(255) not null,
        run_type varchar(255) not null,
        analytics_type varchar(255) not null,
        primary key (id)
    )

    create table analytics.conditions (
        kind varchar(31) not null,
        id bigint generated by default as identity,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        operator varchar(255),
        data_field_type varchar(255),
        parameter double,
        sort_direction varchar(255),
        primary key (id)
    )

    create table analytics.data_source (
        id bigint generated by default as identity,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        clazz_name varchar(255),
        data_source_type varchar(255) not null,
        url_base varchar(255),
        primary key (id)
    )

    create table analytics.investment_style (
        id bigint generated by default as identity,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        description varchar(255),
        name varchar(255) not null,
        priority integer,
        analytics_id bigint,
        parent_id bigint,
        view_id bigint,
        primary key (id)
    )

    create table analytics.investment_style_condition (
        id bigint generated by default as identity,
        priority integer,
        condition_id bigint,
        investment_style_id bigint,
        primary key (id)
    )

    create table analytics.investment_style_datasource (
        id bigint not null,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        data_source_id bigint not null,
        investment_style_id bigint not null,
        primary key (id)
    )
create index analytics.type_idx on analytics.apollo_analytics (analytics_type)
create index analytics.run_type_idx on analytics.apollo_analytics (run_type)

    create table apollo.stockindicators (
        id bigint generated by default as identity,
        created_on timestamp not null,
        updated_on timestamp not null,
        version integer not null,
        adr decimal(19,4),
        adx decimal(19,4),
        apollo_alpha decimal(19,4),
        base_value decimal(19,4),
        bollinger_lower decimal(19,4),
        bollinger_middle decimal(19,4),
        bollinger_upper decimal(19,4),
        cmci decimal(19,4),
        cmci_score decimal(19,4),
        conditional_direction_forecast decimal(19,4),
        cvi decimal(19,4),
        cvi_buy_count integer,
        cvi_buy_signal boolean,
        cvi_close_count integer,
        cvi_close_signal boolean,
        cvi_cover_count integer,
        cvi_cover_signal boolean,
        cvi_fv_flag boolean,
        cvi_iv_flag boolean,
        cvi_range_downside decimal(19,4),
        cvi_range_upside decimal(19,4),
        cvi_short_count integer,
        cvi_short_signal boolean,
        daily_alpha_1d decimal(19,4),
        daily_alpha_1w decimal(19,4),
        daily_alpha_3w decimal(19,4),
        daily_alpha_8w decimal(19,4),
        day_index integer,
        dfv_score decimal(10,4),
        discount_to_fair_value decimal(19,4),
        dm_score_bookvalue decimal(19,4),
        dm_score_cashflow decimal(19,4),
        dm_score_earnings decimal(19,4),
        dm_score_ebitda decimal(19,4),
        dm_score_sales decimal(19,4),
        dmi_negative decimal(19,4),
        dmi_positive decimal(19,4),
        dmi_score decimal(19,4),
        dynamic_long_short decimal(10,4),
        dynamic_short_weighting decimal(10,4),
        dynamic_weighting decimal(10,4),
        efficiency_ratio decimal(19,4),
        efficiency_ratio_sma10d decimal(19,4),
        efficiency_score decimal(19,4),
        eps_fy1_pct_change_1d decimal(10,4),
        eps_fy1_pct_change_1m decimal(10,4),
        eps_fy1_pct_change_3m decimal(10,4),
        eps_fy1_pct_change_5d decimal(10,4),
        eps_trend decimal(19,4),
        estimates_fy1 decimal(19,4),
        estimates_fy2 decimal(19,4),
        fair_value decimal(19,4),
        fair_value_lower decimal(19,4),
        fair_value_middle decimal(19,4),
        fair_value_projection decimal(19,4),
        fair_value_range_flag boolean,
        fair_value_range_limits boolean,
        fair_value_slope decimal(19,4),
        fair_value_upper decimal(19,4),
        fib_023 decimal(19,4),
        fib_038 decimal(19,4),
        fib_050 decimal(19,4),
        fib_061 decimal(19,4),
        fib_076 decimal(19,4),
        fireline integer,
        flow decimal(19,4),
        flow_driver decimal(19,4),
        flow_driver_score decimal(10,4),
        flow_momentum decimal(19,4),
        flow_momentum_score decimal(10,4),
        flow_score decimal(10,4),
        fractional_buy boolean,
        fractional_sell boolean,
        green_alpha_beta decimal(19,4),
        green_cvi decimal(19,4),
        green_extreme_filter decimal(19,4),
        green_forecast_return decimal(19,4),
        green_fv_projection decimal(19,4),
        green_iv_pct_score decimal(19,4),
        green_iv_projection decimal(19,4),
        green_iv_slope decimal(19,4),
        green_momentum decimal(19,4),
        green_range_limit decimal(19,4),
        green_volatility_score decimal(19,4),
        high_price_52w decimal(19,4),
        intrinsic_value decimal(19,4),
        intrinsic_value_pct double,
        intrinsic_value_projection decimal(19,4),
        intrinsic_value_rsq decimal(19,4),
        intrinsic_value_slope decimal(19,4),
        intrinsic_value_slope_signal boolean,
        intrinsic_value_volatility double,
        last_20d_performance decimal(19,4),
        long_term_neutral decimal(19,4),
        long_term_optimistic decimal(19,4),
        long_term_pb_forecast decimal(19,4),
        long_term_pcf_forecast decimal(19,4),
        long_term_pebitda_forecast decimal(19,4),
        long_term_pe_forecast decimal(19,4),
        long_term_ps_forecast decimal(19,4),
        long_term_pessimistic decimal(19,4),
        long_term_value decimal(19,4),
        low_price_52w decimal(19,4),
        lower_margin_of_safety_level decimal(19,4),
        macd1 decimal(19,4),
        macd2 decimal(19,4),
        macd_score decimal(19,4),
        macd_signal decimal(19,4),
        median_discount_to_fair_value decimal(19,4),
        merger_value decimal(19,4),
        money_manager integer,
        money_manager_advice varchar(255),
        move_to_fair_value_projection decimal(19,4),
        move_to_intrinsic_value_projection decimal(19,4),
        new_target decimal(19,4),
        new_target_return decimal(19,4),
        oscillator decimal(19,4),
        oscillator_score decimal(19,4),
        pb_driver decimal(19,4),
        pb_lower decimal(19,4),
        pb_maximum decimal(19,4),
        pb_minimum decimal(19,4),
        pb_second_order decimal(19,4),
        pb_third_order decimal(19,4),
        pb_upper decimal(19,4),
        pb_value decimal(19,4),
        pb_tpm decimal(19,4),
        pcf_maximum decimal(19,4),
        pcf_minimum decimal(19,4),
        pcf_driver decimal(19,4),
        pcf_lower decimal(19,4),
        pcf_second_order decimal(19,4),
        pcf_third_order decimal(19,4),
        pcf_upper decimal(19,4),
        pcf_value decimal(19,4),
        pcf_tpm decimal(19,4),
        pct_change_ytd decimal(19,4),
        pe_driver decimal(19,4),
        pe_lower decimal(19,4),
        pe_maximum decimal(19,4),
        pe_minimum decimal(19,4),
        pe_second_order decimal(19,4),
        pe_third_order decimal(19,4),
        pe_upper decimal(19,4),
        pe_value decimal(19,4),
        pebitda_driver decimal(19,4),
        pebitda_lower decimal(19,4),
        pebitda_maximum decimal(19,4),
        pebitda_minimum decimal(19,4),
        pebitda_second_order decimal(19,4),
        pebitda_third_order decimal(19,4),
        pebitda_upper decimal(19,4),
        pebitda_value decimal(19,4),
        pebitda_tpm decimal(19,4),
        pe_tpm decimal(19,4),
        price_driver decimal(19,4),
        ps_driver decimal(19,4),
        ps_lower decimal(19,4),
        ps_maximum decimal(19,4),
        ps_minimum decimal(19,4),
        ps_second_order decimal(19,4),
        ps_third_order decimal(19,4),
        ps_upper decimal(19,4),
        ps_value decimal(19,4),
        ps_tpm decimal(19,4),
        ptps_af decimal(19,4),
        ptps_sar decimal(19,4),
        ptps_trend varchar(255),
        range_exp_20d_minus decimal(19,4),
        range_exp_20d_plus decimal(19,4),
        red_alpha_beta decimal(19,4),
        red_cvi decimal(19,4),
        red_extreme_filter decimal(19,4),
        red_forecast_return decimal(19,4),
        red_fv_projection decimal(19,4),
        red_iv_pct_score decimal(19,4),
        red_iv_projection decimal(19,4),
        red_iv_slope decimal(19,4),
        red_momentum decimal(19,4),
        red_range_limit decimal(19,4),
        red_volatility_score decimal(19,4),
        revisions decimal(19,4),
        revisions1m decimal(19,4),
        revisions3m decimal(19,4),
        risk decimal(19,4),
        roe_maximum decimal(19,4),
        roe_minimum decimal(19,4),
        rsi_14d decimal(19,4),
        rsi_score decimal(19,4),
        sentiment decimal(19,4),
        skew decimal(19,4),
        sma_200d decimal(19,4),
        sma_20d decimal(19,4),
        sma_50d decimal(19,4),
        stamp_date date not null,
        star_rating decimal(19,4),
        stochastics_percent_d decimal(19,4),
        stochastics_percent_k decimal(19,4),
        stochastic_score decimal(19,4),
        instrument_id bigint,
        stock_index_beta decimal(19,4),
        stretch_value decimal(19,4),
        sweet_spot boolean,
        target20d decimal(19,4),
        target_20d_return decimal(19,4),
        target2m decimal(19,4),
        trigger_level decimal(19,4),
        upper_margin_of_safety_level decimal(19,4),
        valuation_indicator decimal(19,4),
        valuation_indicator_10d decimal(19,4),
        valuation_trading_flags varchar(8),
        value_indicator_score decimal(10,4),
        volume_avg_3m decimal(19,4),
        volume_score decimal(10,4),
        williams decimal(19,4),
        williams_score decimal(19,4),
        primary key (id)
    )

    alter table analytics.analytics_view 
        add constraint FK59uq9qa9h4y3abowcrfkceqso 
        foreign key (analytics_id) 
        references analytics.apollo_analytics

    alter table analytics.analytics_view 
        add constraint FKptjwdubiog4vq0aryvb3jtbts 
        foreign key (parent_id) 
        references analytics.analytics_view

    alter table analytics.investment_style 
        add constraint FKoc58scqab2fc7j2xjqod56en8 
        foreign key (analytics_id) 
        references analytics.apollo_analytics

    alter table analytics.investment_style 
        add constraint FKaqi6vc07xy4f3rjsiko7wdr8p 
        foreign key (parent_id) 
        references analytics.investment_style

    alter table analytics.investment_style 
        add constraint FK71jdxs3e3ggtm5jy267dw0diy 
        foreign key (view_id) 
        references analytics.analytics_view

    alter table analytics.investment_style_condition 
        add constraint FKovjkyxkhwmk4hm162pel1smox 
        foreign key (condition_id) 
        references analytics.conditions

    alter table analytics.investment_style_condition 
        add constraint FK8ocbv7t03rce0gbgdck1lvyh5 
        foreign key (investment_style_id) 
        references analytics.investment_style

    alter table analytics.investment_style_datasource 
        add constraint FK17jfgncr8lnbfb8d2aptukbrv 
        foreign key (data_source_id) 
        references analytics.data_source

    alter table analytics.investment_style_datasource 
        add constraint FK3yg6avpqn2jkyhugoxvuddha0 
        foreign key (investment_style_id) 
        references analytics.investment_style