-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 08, 2022 at 04:39 PM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `test_prieds`
--

-- --------------------------------------------------------

--
-- Table structure for table `visitor`
--

CREATE TABLE `visitor` (
  `id` bigint(20) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `illness` varchar(500) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL,
  `visit_date` date DEFAULT NULL,
  `visitor_queue_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `visitor`
--

INSERT INTO `visitor` (`id`, `address`, `illness`, `name`, `phone_number`, `visit_date`, `visitor_queue_id`) VALUES
(1, 'denpasar', 'pilek', 'putu', '12345678', '2022-03-06', 1),
(2, 'denpasar', 'batuk', 'kadek', '12345678', '2022-03-06', 2),
(3, 'wwdps', 'flu', 'wawe', '132', '2022-03-06', 3),
(4, 'wer', 'fg', 'wer', '12345', '2022-03-08', 4),
(5, 'qw', 'ws', 'qw', '123', '2022-03-08', 5),
(6, 'bar', 'bar', 'bar', '12', '2022-03-08', 6);

-- --------------------------------------------------------

--
-- Table structure for table `visitor_queue`
--

CREATE TABLE `visitor_queue` (
  `id` bigint(20) NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `queue_id` varchar(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `visitor_queue`
--

INSERT INTO `visitor_queue` (`id`, `created_date`, `queue_id`) VALUES
(1, '2022-03-06 17:08:56', 'A001'),
(2, '2022-03-06 17:10:37', 'A002'),
(3, '2022-03-06 22:16:54', 'A003'),
(4, '2022-03-08 21:05:00', 'A001'),
(5, '2022-03-08 21:07:05', 'A002'),
(6, '2022-03-08 21:10:30', 'A003');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `visitor`
--
ALTER TABLE `visitor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgi7ajgykgru1smfmsd8rik9er` (`visitor_queue_id`);

--
-- Indexes for table `visitor_queue`
--
ALTER TABLE `visitor_queue`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `visitor`
--
ALTER TABLE `visitor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `visitor_queue`
--
ALTER TABLE `visitor_queue`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `visitor`
--
ALTER TABLE `visitor`
  ADD CONSTRAINT `FKgi7ajgykgru1smfmsd8rik9er` FOREIGN KEY (`visitor_queue_id`) REFERENCES `visitor_queue` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
