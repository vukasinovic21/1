-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 26, 2021 at 06:07 PM
-- Server version: 10.4.20-MariaDB
-- PHP Version: 8.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tim23`
--

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE `korisnik` (
  `ID` int(11) NOT NULL,
  `Ime` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `MestoID` int(50) DEFAULT NULL,
  `Mobilni` int(50) NOT NULL,
  `Poslodavac` tinyint(1) NOT NULL,
  `Admin` tinyint(1) NOT NULL,
  `oSebi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`ID`, `Ime`, `Email`, `Username`, `Password`, `MestoID`, `Mobilni`, `Poslodavac`, `Admin`, `oSebi`) VALUES
(1, 'Nikola Vukasinovic', 'nikolavukasinovic8@gmail.com', 'vukasinovic21', 'nikola21', 1, 628006726, 0, 0, 'jedan veliki drmr'),
(2, 'Dusan Markovic', 'dusan112233@gmail.com', 'martel7', 'dule7', 1, 644399754, 0, 0, 'volim partizan crvene boje'),
(3, 'Marko Kostic', 'kosticmarko046@gmail.com', 'kolinho', 'kole17', 3, 649960480, 0, 0, 'polu-covek, polu-poljoprivrednik'),
(4, 'Mateja Trifunovic', 'mateja.trifunovic15@gmail.com', 'riHa', 'matrif15', 4, 606540800, 0, 0, 'hari mota vari'),
(5, 'admin', 'admin@gmail.com', 'admin', 'admin', 2, 60123456, 0, 1, 'Admin na korisnike');

-- --------------------------------------------------------

--
-- Table structure for table `mesto`
--

CREATE TABLE `mesto` (
  `MestoID` int(50) NOT NULL,
  `Naziv` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `mesto`
--

INSERT INTO `mesto` (`MestoID`, `Naziv`) VALUES
(1, 'Kragujevac'),
(2, 'Beograd'),
(3, 'Novi Sad'),
(4, 'Nis'),
(5, 'Kraljevo');

-- --------------------------------------------------------

--
-- Table structure for table `oglas`
--

CREATE TABLE `oglas` (
  `IDOglasa` int(50) NOT NULL,
  `IDKorisnika` int(50) NOT NULL,
  `Naziv` varchar(50) NOT NULL,
  `Tip` tinyint(4) NOT NULL,
  `Plata` int(11) NOT NULL,
  `Opis` varchar(500) NOT NULL,
  `Mesto` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `prijave`
--

CREATE TABLE `prijave` (
  `IDKorisnika` int(50) NOT NULL,
  `IDOglasa` int(50) NOT NULL,
  `OpisPrijave` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `MestoID` (`MestoID`);

--
-- Indexes for table `mesto`
--
ALTER TABLE `mesto`
  ADD PRIMARY KEY (`MestoID`);

--
-- Indexes for table `oglas`
--
ALTER TABLE `oglas`
  ADD PRIMARY KEY (`IDOglasa`),
  ADD KEY `IDKorisnika` (`IDKorisnika`);

--
-- Indexes for table `prijave`
--
ALTER TABLE `prijave`
  ADD PRIMARY KEY (`IDKorisnika`,`IDOglasa`),
  ADD KEY `IDOglasa` (`IDOglasa`,`IDKorisnika`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `korisnik`
--
ALTER TABLE `korisnik`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `korisnik`
--
ALTER TABLE `korisnik`
  ADD CONSTRAINT `korisnik_ibfk_1` FOREIGN KEY (`MestoID`) REFERENCES `mesto` (`MestoID`);

--
-- Constraints for table `oglas`
--
ALTER TABLE `oglas`
  ADD CONSTRAINT `oglas_ibfk_1` FOREIGN KEY (`IDKorisnika`) REFERENCES `korisnik` (`ID`);

--
-- Constraints for table `prijave`
--
ALTER TABLE `prijave`
  ADD CONSTRAINT `prijave_ibfk_1` FOREIGN KEY (`IDOglasa`) REFERENCES `oglas` (`IDOglasa`),
  ADD CONSTRAINT `prijave_ibfk_2` FOREIGN KEY (`IDOglasa`) REFERENCES `oglas` (`IDOglasa`),
  ADD CONSTRAINT `prijave_ibfk_3` FOREIGN KEY (`IDKorisnika`) REFERENCES `korisnik` (`ID`),
  ADD CONSTRAINT `prijave_ibfk_4` FOREIGN KEY (`IDKorisnika`) REFERENCES `korisnik` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
