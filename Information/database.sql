CREATE TABLE `coordinate` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `latitude` varchar(50) NOT NULL DEFAULT '',
  `longitude` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

INSERT INTO `coordinate` (`id`, `latitude`, `longitude`)
VALUES
	(1,'18.975','72.8258'),
	(2,'23.03','72.58'),
	(3,'13.0839','80.27'),
	(4,'22.5667','88.3667'),
	(5,'26.847','80.947'),
	(6,'28.6139','77.2089');