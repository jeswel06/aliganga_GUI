-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 04, 2025 at 05:46 PM
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
-- Database: `jobappointmen`
--

-- --------------------------------------------------------

--
-- Table structure for table `logs`
--

CREATE TABLE `logs` (
  `log_id` int(11) NOT NULL,
  `u_id` int(11) DEFAULT NULL,
  `activity` varchar(255) NOT NULL,
  `log_time` datetime DEFAULT current_timestamp(),
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `logs`
--

INSERT INTO `logs` (`log_id`, `u_id`, `activity`, `log_time`, `user_id`) VALUES
(3, NULL, 'Admin logged in', '2025-05-04 23:14:27', 10),
(4, NULL, 'Admin logged in', '2025-05-04 23:17:59', 10),
(5, NULL, 'Admin Registered a user', '2025-05-04 23:19:10', 10),
(6, NULL, 'Admin logged in', '2025-05-04 23:21:32', 10),
(7, NULL, 'Admin updated a user', '2025-05-04 23:21:44', 10),
(8, NULL, 'A new user registered', '2025-05-04 23:32:40', 16),
(9, NULL, 'User reset their password.', '2025-05-04 23:39:23', 15),
(10, NULL, 'User reset their password.', '2025-05-04 23:45:36', 15);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `u_id` int(11) NOT NULL,
  `fn` varchar(255) NOT NULL,
  `ln` varchar(255) NOT NULL,
  `cn` varchar(255) NOT NULL,
  `em` varchar(255) NOT NULL,
  `us` varchar(255) NOT NULL,
  `ps` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `status` varchar(255) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `sq` varchar(255) DEFAULT NULL,
  `ans` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`u_id`, `fn`, `ln`, `cn`, `em`, `us`, `ps`, `type`, `status`, `image`, `sq`, `ans`) VALUES
(3, 'jeswel', 'aliganga', '091232434', 'jeswel@gmail.com', 'jeswel123', '8ade4609ed30942a3f21aa103bfa26be8152719c4f9ce2cab38235646ab875be', 'Admin', 'Approved', '', NULL, NULL),
(4, 'test', 'test', '091323434', 'test123@gmail.com', 'test12345', '6d987a391d90fc2451751b2bb85890fc6e452bc057f39a85d8d1fd552f965fb2', 'Client', 'Approved', '', NULL, NULL),
(5, 'kamo', 'kamo', '12344534324', 'kamo@gmail.com', 'kamo123', '2d52f0d58a347e4b73f354ecee32b52717e5ac4ab29cd0c5cefa066822590695', 'Admin', 'Approved', '', NULL, NULL),
(6, 'jeswel', 'aliganga', '0912323232', 'jeswel123@gmail.com', 'jeswel1234', 'eff6b85e485fb304aaaaff05e2307ea72946d40b5cd985ad32015ed33dae5de1', 'Client', 'Approved', 'src/Images/5.PNG', NULL, NULL),
(7, 'jeswel', 'aliganga', '0912323232', 'jeswel123@gmail.com', 'jeswel1234', 'eff6b85e485fb304aaaaff05e2307ea72946d40b5cd985ad32015ed33dae5de1', 'Admin', 'Active', '', NULL, NULL),
(8, 'testt', 'testttt', '098762454', 'testttt@gmail.com', 'test1234567', '8776f108e247ab1e2b323042c049c266407c81fbad41bde1e8dfc1bb66fd267e', 'Admin', 'Active', 'src/Images/5.PNG', NULL, NULL),
(9, 'carlo', 'Ababan', '092344354', 'carlo@gmail.com', 'carlo123', '16a6664ddc1bd19770f67d6f2b86634843e86a3cf93026d474e79b82c33b960f', 'Admin', 'Pending', 'src/Images/4event.PNG', 'Whats the codename?', 'Delta'),
(10, 'andre', 'estrera', '09132434534', 'andre@gmail.com', 'andre123', 'c131c63023da974fb20016d91331797ca214f376ba64debf3a0c4aa9049e965c', 'Admin', 'Active', 'src/Images/6.PNG', 'What is your Favorite food?', 'Nesie'),
(11, 'nesie', 'labasores', '09876232434', 'nesie@gmail.com', 'nesie123', '8346272557eb3d5478a92a1a7ce4beebbc17ecdc0258e6ecd929acfdd4839320', 'Client', 'Pending', 'src/Images/491001377_1131193342108412_7973616869452142286_n.jpg', NULL, NULL),
(12, 'nesie', 'labasores', '09876232434', 'nesiee@gmail.com', 'nesie1234', '8346272557eb3d5478a92a1a7ce4beebbc17ecdc0258e6ecd929acfdd4839320', 'Client', 'Pending', 'src/Images/491001377_1131193342108412_7973616869452142286_n.jpg', NULL, NULL),
(13, 'nesie', 'labasores', '09876232434', 'nesiee3@gmail.com', 'nesie31234', '8346272557eb3d5478a92a1a7ce4beebbc17ecdc0258e6ecd929acfdd4839320', 'Client', 'Pending', 'src/Images/491001377_1131193342108412_7973616869452142286_n.jpg', NULL, NULL),
(14, 'james sven', 'ansali', '0932343434', 'james@gmail.com', 'james123', '338c8bf01f4552dff1d4b2eed84c7a38c3a5f001604804fba47e3d28fc6ad4f5', 'Admin', 'Pending', 'src/Images/dacdwarf.png', NULL, NULL),
(15, 'testtttttt', 'testttttt', '09645646456', 'testtttt@gmail.com', 'jamess123', '338c8bf01f4552dff1d4b2eed84c7a38c3a5f001604804fba47e3d28fc6ad4f5', 'Client', 'Pending', '', 'What is your Favorite food?', 'Hannah'),
(16, 'eheyy', 'heyy', '092323232', 'hey@gmail.com', 'hey123', '6d987a391d90fc2451751b2bb85890fc6e452bc057f39a85d8d1fd552f965fb2', 'Client', 'Pending', '', 'What is your Favorite food?', 'has');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `logs`
--
ALTER TABLE `logs`
  ADD PRIMARY KEY (`log_id`),
  ADD KEY `u_id` (`u_id`),
  ADD KEY `fk_user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`u_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `logs`
--
ALTER TABLE `logs`
  MODIFY `log_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `logs`
--
ALTER TABLE `logs`
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `logs_ibfk_1` FOREIGN KEY (`u_id`) REFERENCES `users` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
