DROP DATABASE IF EXISTS wiss_quiz;
CREATE DATABASE wiss_quiz;
USE wiss_quiz;
DROP USER IF EXISTS 'wiss_quiz'@'%';
CREATE USER 'wiss_quiz'@'%' IDENTIFIED BY 'wiss_quiz';
GRANT ALL PRIVILEGES ON wiss_quiz.* TO 'wiss_quiz'@'%';

CREATE TABLE `category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `category` VALUES
(1,'database'),
(2,'java'),
(3,'mathe'),
(4,'php'),
(5,'Applikationen testen');

CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7jaqbm9p4prg7n91dd1uabrvj` (`category_id`),
  CONSTRAINT `FK7jaqbm9p4prg7n91dd1uabrvj` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `question` VALUES
(1,'Welcher SQL-Befehl dient zum Abfragen von Daten?',1),
(2,'Welcher SQL-Befehl fuegt neue Datensaetze ein?',1),
(3,'Welcher SQL-Befehl erstellt eine neue Tabelle?',1),
(4,'Welcher SQL-Befehl aendert bestehende Datensaetze?',1),
(5,'Welcher SQL-Befehl loescht Datensaetze?',1),
(6,'Welcher SQL-Befehl entfernt eine ganze Tabelle?',1),
(7,'Welcher SQL-Befehl sortiert ein Resultat?',1),
(8,'Mit welcher Klausel filtert man Zeilen?',1),
(9,'Welcher Join liefert nur passende Datensaetze aus beiden Tabellen?',1),
(10,'Welche Funktion zaehlt Datensaetze?',1),

(11,'Welcher Datentyp eignet sich fuer Ganzzahlen in Java?',2),
(12,'Welcher Datentyp eignet sich fuer Kommazahlen in Java?',2),
(13,'Wie startet man ein Java-Programm?',2),
(14,'Womit erzeugt man ein Objekt in Java?',2),
(15,'Welches Zeichen beendet in Java eine Anweisung?',2),
(16,'Welches Schluesselwort vererbt von einer Klasse?',2),
(17,'Welches Schluesselwort gehoert zu einer Bedingung?',2),
(18,'Wie deklariert man ein Array von int?',2),
(19,'Welche Methode wird bei einer Java-Anwendung als Einstiegspunkt verwendet?',2),
(20,'Welcher Zugriffsmodifikator erlaubt Zugriff von ueberall?',2),

(21,'Was ist 2 + 2?',3),
(22,'Was ist 5 * 5?',3),
(23,'Was ist 10 - 3?',3),
(24,'Was ist 12 / 3?',3),
(25,'Was ist 3 * 7?',3),
(26,'Was ist die Wurzel von 81?',3),
(27,'Was ist 15 + 6?',3),
(28,'Was ist 9 * 8?',3),
(29,'Was ist 100 / 25?',3),
(30,'Was ist 14 - 9?',3),

(31,'Welches Zeichen beginnt in PHP eine Variable?',4),
(32,'Welches Tag beginnt einen PHP-Block?',4),
(33,'Mit welcher Funktion gibt man in PHP Text aus?',4),
(34,'Welche Zeichen beenden in PHP eine Anweisung?',4),
(35,'Wie verbindet man in PHP Strings?',4),
(36,'Welches Schluesselwort definiert in PHP eine Funktion?',4),
(37,'Welches Superglobal enthaelt GET-Parameter?',4),
(38,'Welches Superglobal enthaelt POST-Daten?',4),
(39,'Mit welchem Operator vergleicht man in PHP auf Gleichheit?',4),
(40,'Welcher Datentyp speichert in PHP Wahrheitswerte?',4),

(41,'Welcher Test prueft eine einzelne Methode isoliert?',5),
(42,'Welches Framework wird in eurem Projekt fuer Unit-Tests verwendet?',5),
(43,'Wozu dient Mockito?',5),
(44,'Was beschreibt die Testpyramide unten?',5),
(45,'Welche Testart prueft das Zusammenspiel mehrerer Komponenten?',5),
(46,'Welche Testart prueft die Anwendung als Ganzes aus Benutzersicht?',5),
(47,'Was bedeutet Arrange-Act-Assert?',5),
(48,'Warum sind automatisierte Tests bei Regressionen nuetzlich?',5),
(49,'Was ist ein Mock?',5),
(50,'Was ist ein typischer Grenzwerttest?',5);


CREATE TABLE `answer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `answer` varchar(255) DEFAULT NULL,
  `correct` TINYINT(1) NOT NULL,
  `question_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8frr4bcabmmeyyu60qt7iiblo` (`question_id`),
  CONSTRAINT `FK8frr4bcabmmeyyu60qt7iiblo` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `answer` VALUES
(1,'SELECT',1,1),
(2,'INSERT',0,1),
(3,'UPDATE',0,1),
(4,'DELETE',0,1),

(5,'CREATE',0,2),
(6,'INSERT',1,2),
(7,'SELECT',0,2),
(8,'DROP',0,2),

(9,'ALTER',0,3),
(10,'CREATE TABLE',1,3),
(11,'UPDATE TABLE',0,3),
(12,'BUILD TABLE',0,3),

(13,'SELECT',0,4),
(14,'UPDATE',1,4),
(15,'ORDER BY',0,4),
(16,'COUNT',0,4),

(17,'REMOVE',0,5),
(18,'DROP',0,5),
(19,'DELETE',1,5),
(20,'CLEAR',0,5),

(21,'DELETE',0,6),
(22,'REMOVE TABLE',0,6),
(23,'DROP TABLE',1,6),
(24,'CLEAR TABLE',0,6),

(25,'GROUP BY',0,7),
(26,'ORDER BY',1,7),
(27,'WHERE',0,7),
(28,'HAVING',0,7),

(29,'HAVING',0,8),
(30,'WHERE',1,8),
(31,'ORDER BY',0,8),
(32,'JOIN',0,8),

(33,'LEFT JOIN',0,9),
(34,'RIGHT JOIN',0,9),
(35,'INNER JOIN',1,9),
(36,'OUTER JOIN',0,9),

(37,'SUM()',0,10),
(38,'AVG()',0,10),
(39,'COUNT()',1,10),
(40,'MIN()',0,10),

(41,'String',0,11),
(42,'int',1,11),
(43,'boolean',0,11),
(44,'double',0,11),

(45,'char',0,12),
(46,'double',1,12),
(47,'int',0,12),
(48,'long',0,12),

(49,'start()',0,13),
(50,'run()',0,13),
(51,'main()',1,13),
(52,'init()',0,13),

(53,'create',0,14),
(54,'object',0,14),
(55,'new',1,14),
(56,'class',0,14),

(57,'.',0,15),
(58,':',0,15),
(59,';',1,15),
(60,',',0,15),

(61,'implements',0,16),
(62,'extends',1,16),
(63,'inherits',0,16),
(64,'instanceof',0,16),

(65,'switch',0,17),
(66,'class',0,17),
(67,'if',1,17),
(68,'new',0,17),

(69,'int[] zahlen;',1,18),
(70,'array<int> zahlen;',0,18),
(71,'int zahlen[]();',0,18),
(72,'numbers:int[];',0,18),

(73,'execute',0,19),
(74,'public static void main(String[] args)',1,19),
(75,'start(String[] args)',0,19),
(76,'main(void)',0,19),

(77,'private',0,20),
(78,'protected',0,20),
(79,'public',1,20),
(80,'static',0,20),

(81,'3',0,21),
(82,'4',1,21),
(83,'5',0,21),
(84,'22',0,21),

(85,'20',0,22),
(86,'25',1,22),
(87,'30',0,22),
(88,'15',0,22),

(89,'6',0,23),
(90,'7',1,23),
(91,'8',0,23),
(92,'13',0,23),

(93,'3',0,24),
(94,'4',1,24),
(95,'6',0,24),
(96,'9',0,24),

(97,'18',0,25),
(98,'20',0,25),
(99,'21',1,25),
(100,'24',0,25),

(101,'7',0,26),
(102,'8',0,26),
(103,'9',1,26),
(104,'10',0,26),

(105,'20',0,27),
(106,'21',1,27),
(107,'22',0,27),
(108,'24',0,27),

(109,'63',0,28),
(110,'72',1,28),
(111,'81',0,28),
(112,'98',0,28),

(113,'2',0,29),
(114,'3',0,29),
(115,'4',1,29),
(116,'5',0,29),

(117,'4',0,30),
(118,'5',1,30),
(119,'6',0,30),
(120,'7',0,30),

(121,'#',0,31),
(122,'$',1,31),
(123,'%',0,31),
(124,'&',0,31),

(125,'<php>',0,32),
(126,'<?php',1,32),
(127,'<script php>',0,32),
(128,'{{php}}',0,32),

(129,'print oder echo',1,33),
(130,'show',0,33),
(131,'write',0,33),
(132,'console.log',0,33),

(133,'.',0,34),
(134,',',0,34),
(135,';',1,34),
(136,':',0,34),

(137,'+',0,35),
(138,'.',1,35),
(139,'&',0,35),
(140,':',0,35),

(141,'method',0,36),
(142,'func',0,36),
(143,'function',1,36),
(144,'define',0,36),

(145,'$_POST',0,37),
(146,'$_GET',1,37),
(147,'$_REQUEST_BODY',0,37),
(148,'$_URL',0,37),

(149,'$_GET',0,38),
(150,'$_FORM',0,38),
(151,'$_POST',1,38),
(152,'$_BODY',0,38),

(153,'=',0,39),
(154,'==',1,39),
(155,'=>',0,39),
(156,'!=',0,39),

(157,'int',0,40),
(158,'string',0,40),
(159,'bool',1,40),
(160,'array',0,40),

(161,'Integrationstest',0,41),
(162,'Systemtest',0,41),
(163,'Unit-Test',1,41),
(164,'Abnahmetest',0,41),

(165,'JUnit',1,42),
(166,'Docker',0,42),
(167,'MySQL',0,42),
(168,'React',0,42),

(169,'Zum Mocken von Abhaengigkeiten',1,43),
(170,'Zum Starten von Docker',0,43),
(171,'Zum Schreiben von CSS',0,43),
(172,'Zum Deployen ins Internet',0,43),

(173,'Viele End-to-End-Tests',0,44),
(174,'Viele Unit-Tests',1,44),
(175,'Keine Tests',0,44),
(176,'Nur manuelle Tests',0,44),

(177,'Unit-Test',0,45),
(178,'Integrationstest',1,45),
(179,'Usability-Test',0,45),
(180,'Code-Review',0,45),

(181,'Systemtest',1,46),
(182,'Unit-Test',0,46),
(183,'Linting',0,46),
(184,'Refactoring',0,46),

(185,'Vorbereiten-Ausfuehren-Pruefen',1,47),
(186,'Analysieren-Aendern-Archivieren',0,47),
(187,'Abfrage-Antwort-Ausgabe',0,47),
(188,'Anmelden-Aktualisieren-Abmelden',0,47),

(189,'Weil man Fehler nach Aenderungen schnell erkennt',1,48),
(190,'Weil man dann keine Anforderungen braucht',0,48),
(191,'Weil man nie mehr manuell testen muss',0,48),
(192,'Weil die App dadurch automatisch schneller wird',0,48),

(193,'Ein Platzhalter fuer eine Abhaengigkeit im Test',1,49),
(194,'Ein echter Datenbankserver',0,49),
(195,'Ein Produktionsfehler',0,49),
(196,'Ein Benutzerkonto fuer Tests',0,49),

(197,'59%, 60%, 61% pruefen',1,50),
(198,'Nur einen Zufallswert pruefen',0,50),
(199,'Nie Randfaelle pruefen',0,50),
(200,'Nur den Happy Path pruefen',0,50);


