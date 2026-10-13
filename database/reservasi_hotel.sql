-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 13, 2026 at 02:36 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `reservasi_hotel`
--

-- --------------------------------------------------------

--
-- Table structure for table `reservasi`
--

CREATE TABLE `reservasi` (
  `id_reservasi` int(11) NOT NULL,
  `tipe` varchar(20) NOT NULL,
  `nama_tamu` varchar(50) NOT NULL,
  `jumlah_malam` int(11) NOT NULL,
  `harga_per_malam` int(11) NOT NULL,
  `status` varchar(20) NOT NULL,
  `denda_persen` int(11) DEFAULT NULL,
  `jenis_penyambutan` varchar(50) DEFAULT NULL,
  `biaya_penyambutan` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservasi`
--

INSERT INTO `reservasi` (`id_reservasi`, `tipe`, `nama_tamu`, `jumlah_malam`, `harga_per_malam`, `status`, `denda_persen`, `jenis_penyambutan`, `biaya_penyambutan`) VALUES
(1, 'Standard', 'Karina', 2, 300000, 'Menunggu', 20, NULL, NULL),
(2, 'VIP', 'Bang Chan', 3, 750000, 'Check-In', NULL, 'Executive Lounge', 100000),
(3, 'Standard', 'Honey bee', 1, 300000, 'Check-Out', 20, NULL, NULL),
(4, 'Standard', 'nopi', 2, 300000, 'Menunggu', 20, NULL, NULL),
(5, 'Standard', 'qwerty', 1, 300000, 'Menunggu', 20, NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `reservasi`
--
ALTER TABLE `reservasi`
  ADD PRIMARY KEY (`id_reservasi`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `reservasi`
--
ALTER TABLE `reservasi`
  MODIFY `id_reservasi` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
