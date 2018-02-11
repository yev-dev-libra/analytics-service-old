/*

drop table analytics if EXISTS;
drop table analytics_parameter if EXISTS;
drop table analytics_decimal_parameter if EXISTS;
drop table analytics_double_parameter if EXISTS;
drop table analytics_field_parameter if EXISTS;
drop table analytics_investment_style if EXISTS;
drop table analytics_investment_style_parameter if EXISTS;
drop table analytics_investment_style_field_parameter if EXISTS;
drop table analytics_view if EXISTS;



CREATE TABLE `analytics` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL,
  `updated_on` datetime NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `run_type` varchar(255) NOT NULL,
  `analytics_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fwpkaumc6roge0i7eymstuot3` (`name`),
  KEY `type_idx` (`analytics_type`),
  KEY `run_type_idx` (`run_type`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `analytics_decimal_parameter` (
  `decimal_value` decimal(19,2) DEFAULT NULL,
  `operand` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKl6n3vdqr90qr3esxurxld2e37` FOREIGN KEY (`id`) REFERENCES `analytics_parameter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `analytics_double_parameter` (
  `double_value` double DEFAULT NULL,
  `operand` varchar(255) NOT NULL,
  `id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKjt4uqsmxe9p0dei1laar2qdqg` FOREIGN KEY (`id`) REFERENCES `analytics_parameter` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `analytics_field_parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_field_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

CREATE TABLE `analytics_investment_style` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL,
  `updated_on` datetime NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `priority` int(11) DEFAULT NULL,
  `analytics_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `view_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_m8cnfs0oi4n49ccdbo0h18fkj` (`name`),
  KEY `FKhn8kvfhtvux5bluebrlcus8pr` (`analytics_id`),
  KEY `FKisp24obbnhvcucqe5bve6h9yd` (`parent_id`),
  KEY `FKds97b40e7wdpok24a7lpxmd0` (`view_id`),
  CONSTRAINT `FKds97b40e7wdpok24a7lpxmd0` FOREIGN KEY (`view_id`) REFERENCES `analytics_view` (`id`),
  CONSTRAINT `FKhn8kvfhtvux5bluebrlcus8pr` FOREIGN KEY (`analytics_id`) REFERENCES `analytics` (`id`),
  CONSTRAINT `FKisp24obbnhvcucqe5bve6h9yd` FOREIGN KEY (`parent_id`) REFERENCES `analytics_investment_style` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

CREATE TABLE `analytics_investment_style_field_parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `priority` int(11) DEFAULT NULL,
  `field_parameter_id` bigint(20) NOT NULL,
  `investment_style_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK65am1j7go00nmpxyg8vh06yue` (`field_parameter_id`),
  KEY `FK5dqvlptrag3b4au89c6if7gm4` (`investment_style_id`),
  CONSTRAINT `FK5dqvlptrag3b4au89c6if7gm4` FOREIGN KEY (`investment_style_id`) REFERENCES `analytics_investment_style` (`id`),
  CONSTRAINT `FK65am1j7go00nmpxyg8vh06yue` FOREIGN KEY (`field_parameter_id`) REFERENCES `analytics_field_parameter` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

CREATE TABLE `analytics_investment_style_parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `priority` int(11) DEFAULT NULL,
  `investment_style_id` bigint(20) DEFAULT NULL,
  `parameter_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKl0djprx4sav63nad74m4wkt13` (`investment_style_id`),
  KEY `FK22a7xvu9caduxx7lh2qcouke4` (`parameter_id`),
  CONSTRAINT `FK22a7xvu9caduxx7lh2qcouke4` FOREIGN KEY (`parameter_id`) REFERENCES `analytics_parameter` (`id`),
  CONSTRAINT `FKl0djprx4sav63nad74m4wkt13` FOREIGN KEY (`investment_style_id`) REFERENCES `analytics_investment_style` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

CREATE TABLE `analytics_parameter` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `composition_type` varchar(255) DEFAULT NULL,
  `data_field_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;

CREATE TABLE `analytics_view` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_on` datetime NOT NULL,
  `updated_on` datetime NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `priority` int(11) DEFAULT NULL,
  `analytics_id` bigint(20) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_2rsfjarqtpii38h7q0hlcccjr` (`name`),
  KEY `FKma8jnkly15fgf3r4ayn9uybrt` (`analytics_id`),
  KEY `FKptjwdubiog4vq0aryvb3jtbts` (`parent_id`),
  CONSTRAINT `FKma8jnkly15fgf3r4ayn9uybrt` FOREIGN KEY (`analytics_id`) REFERENCES `analytics` (`id`),
  CONSTRAINT `FKptjwdubiog4vq0aryvb3jtbts` FOREIGN KEY (`parent_id`) REFERENCES `analytics_view` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
*/