-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Wersja serwera:               10.1.18-MariaDB - mariadb.org binary distribution
-- Serwer OS:                    Win64
-- HeidiSQL Wersja:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Zrzut struktury bazy danych edziekanat
CREATE DATABASE IF NOT EXISTS `edziekanat` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `edziekanat`;


-- Zrzut struktury tabela edziekanat.dydaktyk
CREATE TABLE IF NOT EXISTS `dydaktyk` (
  `Id` int(11) NOT NULL,
  `Imie` varchar(50) NOT NULL,
  `Nazwisko` varchar(50) NOT NULL,
  `Login` varchar(50) NOT NULL,
  `Haslo` varchar(50) NOT NULL,
  `Plec` varchar(1) NOT NULL,
  `DataUrodzenia` date NOT NULL,
  `Adres` varchar(50) NOT NULL,
  `Tytul` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.dydaktyk: ~5 rows (około)
/*!40000 ALTER TABLE `dydaktyk` DISABLE KEYS */;
INSERT INTO `dydaktyk` (`Id`, `Imie`, `Nazwisko`, `Login`, `Haslo`, `Plec`, `DataUrodzenia`, `Adres`, `Tytul`) VALUES
	(1, 'Marek', 'Tomaszewski', 'mt12045', 'test012', 'M', '1990-01-20', 'Potulicka 9/6a', 'mgr inz.\r\n'),
	(2, 'Jacek', 'Kowalski', 'jk123009', 'test012', 'M', '1985-01-20', 'Wyszynskiego 14a', 'dr inz.'),
	(3, 'Adrian', 'Morczynski', 'am24578', 'test012', 'M', '1980-01-20', 'Chrobrego 2A', 'inz.'),
	(4, 'Anna', 'Marcinkiewicz', 'am22331', 'test012', 'K', '1998-01-22', 'Wyszynskiego 2', 'mgr inz.'),
	(5, 'Beata ', 'Mazurek', 'bm12234', 'test012', 'K', '1988-01-22', 'Paulsona 2', 'dr inz.');
/*!40000 ALTER TABLE `dydaktyk` ENABLE KEYS */;


-- Zrzut struktury tabela edziekanat.formakursu
CREATE TABLE IF NOT EXISTS `formakursu` (
  `Id` int(11) NOT NULL,
  `IdKurs` varchar(50) NOT NULL,
  `Nazwa` varchar(50) DEFAULT NULL,
  `Typ` varchar(3) NOT NULL,
  `Waga` float NOT NULL,
  `LiczbaGodzin` int(11) NOT NULL,
  `FormaZaliczenia` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdKurs` (`IdKurs`),
  CONSTRAINT `FKIdKurs` FOREIGN KEY (`IdKurs`) REFERENCES `kurs` (`Nazwa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.formakursu: ~6 rows (około)
/*!40000 ALTER TABLE `formakursu` DISABLE KEYS */;
INSERT INTO `formakursu` (`Id`, `IdKurs`, `Nazwa`, `Typ`, `Waga`, `LiczbaGodzin`, `FormaZaliczenia`) VALUES
	(1, 'Analiza Matematyczna i Algebra Liniowa', NULL, 'A', 0.5, 30, 'zal'),
	(2, 'Analiza Matematyczna i Algebra Liniowa', NULL, 'W', 0.5, 15, 'egz'),
	(3, 'Szkolenie Biblioteczne', NULL, 'W', 1, 15, 'zal'),
	(4, 'Fizyka', NULL, 'W', 0.4, 30, 'egz'),
	(5, 'Fizyka', NULL, 'A', 0.3, 15, 'zal'),
	(6, 'Fizyka', NULL, 'L', 0.3, 15, 'zal');
/*!40000 ALTER TABLE `formakursu` ENABLE KEYS */;


-- Zrzut struktury tabela edziekanat.grupacw
CREATE TABLE IF NOT EXISTS `grupacw` (
  `Id` varchar(50) NOT NULL,
  `IdGrupaWyk` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IdGrupaWyk` (`IdGrupaWyk`),
  CONSTRAINT `IdGrupaWyk` FOREIGN KEY (`IdGrupaWyk`) REFERENCES `grupawyk` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.grupacw: ~0 rows (około)
/*!40000 ALTER TABLE `grupacw` DISABLE KEYS */;
INSERT INTO `grupacw` (`Id`, `IdGrupaWyk`) VALUES
	('I1-S1C1', 'I1-S1W1');
/*!40000 ALTER TABLE `grupacw` ENABLE KEYS */;


-- Zrzut struktury tabela edziekanat.grupalab
CREATE TABLE IF NOT EXISTS `grupalab` (
  `Id` varchar(50) NOT NULL,
  `Licznosc` int(11) NOT NULL,
  `IdGrupaCw` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdGrupaCw` (`IdGrupaCw`),
  CONSTRAINT `FKIdGrupaCw` FOREIGN KEY (`IdGrupaCw`) REFERENCES `grupacw` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.grupalab: ~2 rows (około)
/*!40000 ALTER TABLE `grupalab` DISABLE KEYS */;
INSERT INTO `grupalab` (`Id`, `Licznosc`, `IdGrupaCw`) VALUES
	('I1-S1L1A', 10, 'I1-S1C1'),
	('I1-S1L1B', 10, 'I1-S1C1');
/*!40000 ALTER TABLE `grupalab` ENABLE KEYS */;


-- Zrzut struktury tabela edziekanat.grupawyk
CREATE TABLE IF NOT EXISTS `grupawyk` (
  `Id` varchar(50) NOT NULL,
  `IdRocznik` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdRocznik` (`IdRocznik`),
  CONSTRAINT `FKIdRocznik` FOREIGN KEY (`IdRocznik`) REFERENCES `rocznik` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.grupawyk: ~1 rows (około)
/*!40000 ALTER TABLE `grupawyk` DISABLE KEYS */;
INSERT INTO `grupawyk` (`Id`, `IdRocznik`) VALUES
	('I1-S1W1', 1);
/*!40000 ALTER TABLE `grupawyk` ENABLE KEYS */;


-- Zrzut struktury tabela edziekanat.kierunek
CREATE TABLE IF NOT EXISTS `kierunek` (
  `Id` int(11) NOT NULL,
  `Nazwa` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.kierunek: ~0 rows (około)
/*!40000 ALTER TABLE `kierunek` DISABLE KEYS */;
INSERT INTO `kierunek` (`Id`, `Nazwa`) VALUES
	(1, 'Informatyka');
/*!40000 ALTER TABLE `kierunek` ENABLE KEYS */;


-- Zrzut struktury tabela edziekanat.kurs
CREATE TABLE IF NOT EXISTS `kurs` (
  `Nazwa` varchar(50) NOT NULL,
  `IdSemestr` int(11) NOT NULL,
  `ECTS` int(11) NOT NULL,
  PRIMARY KEY (`Nazwa`),
  KEY `FKIdSemestrKurs` (`IdSemestr`),
  CONSTRAINT `FKIdSemestrKurs` FOREIGN KEY (`IdSemestr`) REFERENCES `semestr` (`NumerSemestru`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.kurs: ~6 rows (około)
/*!40000 ALTER TABLE `kurs` DISABLE KEYS */;
INSERT INTO `kurs` (`Nazwa`, `IdSemestr`, `ECTS`) VALUES
	('Analiza Matematyczna i Algebra Liniowa', 1, 5),
	('Elektronika', 1, 6),
	('Elementy Cyfrowe i Uklady Logiczne', 1, 5),
	('Fizyka', 1, 7),
	('Podstawy Informatyki', 1, 6),
	('Szkolenie Biblioteczne', 1, 0);
/*!40000 ALTER TABLE `kurs` ENABLE KEYS */;


-- Zrzut struktury tabela edziekanat.ocena
CREATE TABLE IF NOT EXISTS `ocena` (
  `Id` int(11) NOT NULL,
  `IdStudent` varchar(5) NOT NULL,
  `IdProwadzacyForme` int(11) NOT NULL,
  `ITermin` float DEFAULT NULL,
  `IITermin` float DEFAULT NULL,
  `IPoprawka` float DEFAULT NULL,
  `IIPoprawka` float DEFAULT NULL,
  `Komisja` float DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdProwadzacyZajecia` (`IdProwadzacyForme`),
  KEY `FKIdStudentOcena` (`IdStudent`),
  CONSTRAINT `FKIdProwadzacyZajecia` FOREIGN KEY (`IdProwadzacyForme`) REFERENCES `prowadzacyforme` (`Id`),
  CONSTRAINT `FKIdStudentOcena` FOREIGN KEY (`IdStudent`) REFERENCES `student` (`Album`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.ocena: ~2 rows (około)
/*!40000 ALTER TABLE `ocena` DISABLE KEYS */;
INSERT INTO `ocena` (`Id`, `IdStudent`, `IdProwadzacyForme`, `ITermin`, `IITermin`, `IPoprawka`, `IIPoprawka`, `Komisja`) VALUES
	(1, '24897', 1, 2, 4, NULL, NULL, NULL),
	(2, '24897', 2, 2, 3.5, NULL, NULL, NULL),
	(3, '24897', 3, 5, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `ocena` ENABLE KEYS */;


-- Zrzut struktury tabela edziekanat.ogloszenia
CREATE TABLE IF NOT EXISTS `ogloszenia` (
  `Id` int(11) NOT NULL,
  `Tytul` varchar(250) NOT NULL,
  `DataDodania` date NOT NULL,
  `Dodal` varchar(5) NOT NULL,
  `Dla` varchar(5) NOT NULL,
  `Tresc` varchar(500) NOT NULL,
  `Globalne` int(11) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.ogloszenia: ~0 rows (około)
/*!40000 ALTER TABLE `ogloszenia` DISABLE KEYS */;
/*!40000 ALTER TABLE `ogloszenia` ENABLE KEYS */;


-- Zrzut struktury tabela edziekanat.prowadzacyforme
CREATE TABLE IF NOT EXISTS `prowadzacyforme` (
  `Id` int(11) NOT NULL,
  `IdDydaktyk` int(11) NOT NULL,
  `IdForma` int(11) NOT NULL,
  `IdGrupaL` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdDydaktyk` (`IdDydaktyk`),
  KEY `FKIdForma` (`IdForma`),
  KEY `FKIdGrupaL` (`IdGrupaL`),
  CONSTRAINT `FKIdDydaktyk` FOREIGN KEY (`IdDydaktyk`) REFERENCES `dydaktyk` (`Id`),
  CONSTRAINT `FKIdForma` FOREIGN KEY (`IdForma`) REFERENCES `formakursu` (`Id`),
  CONSTRAINT `FKIdGrupaL` FOREIGN KEY (`IdGrupaL`) REFERENCES `grupalab` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.prowadzacyforme: ~5 rows (około)
/*!40000 ALTER TABLE `prowadzacyforme` DISABLE KEYS */;
INSERT INTO `prowadzacyforme` (`Id`, `IdDydaktyk`, `IdForma`, `IdGrupaL`) VALUES
	(1, 1, 1, 'I1-S1L1A'),
	(2, 2, 2, 'I1-S1L1A'),
	(3, 3, 3, 'I1-S1L1A'),
	(4, 5, 4, 'I1-S1L1A'),
	(5, 5, 5, 'I1-S1L1A'),
	(6, 4, 6, 'I1-S1L1A');
/*!40000 ALTER TABLE `prowadzacyforme` ENABLE KEYS */;


-- Zrzut struktury tabela edziekanat.rocznik
CREATE TABLE IF NOT EXISTS `rocznik` (
  `Id` int(11) NOT NULL,
  `IdSemestr` int(11) NOT NULL,
  `RocznikAkademicki` varchar(50) NOT NULL,
  `DataPoczatkowa` date NOT NULL,
  `DataKoncowa` date NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdSemestr` (`IdSemestr`),
  CONSTRAINT `FKIdSemestr` FOREIGN KEY (`IdSemestr`) REFERENCES `semestr` (`NumerSemestru`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.rocznik: ~0 rows (około)
/*!40000 ALTER TABLE `rocznik` DISABLE KEYS */;
INSERT INTO `rocznik` (`Id`, `IdSemestr`, `RocznikAkademicki`, `DataPoczatkowa`, `DataKoncowa`) VALUES
	(1, 1, '2016/2017', '2016-10-01', '2017-02-14');
/*!40000 ALTER TABLE `rocznik` ENABLE KEYS */;


-- Zrzut struktury tabela edziekanat.semestr
CREATE TABLE IF NOT EXISTS `semestr` (
  `NumerSemestru` int(11) NOT NULL,
  `TypSemestru` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`NumerSemestru`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.semestr: ~2 rows (około)
/*!40000 ALTER TABLE `semestr` DISABLE KEYS */;
INSERT INTO `semestr` (`NumerSemestru`, `TypSemestru`) VALUES
	(1, 'Zima'),
	(2, 'Lato');
/*!40000 ALTER TABLE `semestr` ENABLE KEYS */;


-- Zrzut struktury tabela edziekanat.specjalnosc
CREATE TABLE IF NOT EXISTS `specjalnosc` (
  `Id` int(11) NOT NULL,
  `Nazwa` varchar(50) NOT NULL,
  `IdKierunek` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdSKierunek` (`IdKierunek`),
  CONSTRAINT `FKIdSKierunek` FOREIGN KEY (`IdKierunek`) REFERENCES `kierunek` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.specjalnosc: ~0 rows (około)
/*!40000 ALTER TABLE `specjalnosc` DISABLE KEYS */;
INSERT INTO `specjalnosc` (`Id`, `Nazwa`, `IdKierunek`) VALUES
	(1, 'Systemy Komputerowe i Oprogramowanie', 1);
/*!40000 ALTER TABLE `specjalnosc` ENABLE KEYS */;


-- Zrzut struktury tabela edziekanat.student
CREATE TABLE IF NOT EXISTS `student` (
  `Album` varchar(5) NOT NULL,
  `Imie` varchar(50) NOT NULL,
  `Nazwisko` varchar(50) NOT NULL,
  `Haslo` varchar(50) NOT NULL,
  `Plec` varchar(1) NOT NULL,
  `DataUrodzenia` date NOT NULL,
  `Adres` varchar(50) NOT NULL,
  `IdKierunek` int(11) NOT NULL,
  `IdGrupaLab` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  PRIMARY KEY (`Album`),
  KEY `FKIdKierunek` (`IdKierunek`),
  KEY `FKIdGrupaLab` (`IdGrupaLab`),
  CONSTRAINT `FKIdGrupaLab` FOREIGN KEY (`IdGrupaLab`) REFERENCES `grupalab` (`Id`),
  CONSTRAINT `FKIdKierunek` FOREIGN KEY (`IdKierunek`) REFERENCES `kierunek` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Zrzucanie danych dla tabeli edziekanat.student: ~2 rows (około)
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` (`Album`, `Imie`, `Nazwisko`, `Haslo`, `Plec`, `DataUrodzenia`, `Adres`, `IdKierunek`, `IdGrupaLab`, `Email`) VALUES
	('24897', 'Marek', 'Tuczynski', 'test012', 'M', '2017-01-15', 'potulicka', 1, 'I1-S1L1A', 'mk@wp.pl'),
	('24898', 'Joanna', 'Marciniak', 'test012', 'K', '2017-01-15', 'putulicka', 1, 'I1-S1L1A', 'jm@o2.pl');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
