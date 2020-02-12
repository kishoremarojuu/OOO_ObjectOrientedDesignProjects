package com.carrentalooo.carrentalmytest.vehicle.dao;

import com.carrentalooo.carrentalmytest.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleDAO extends JpaRepository<Vehicle, Integer> {

	void deleteByVehicleId(Integer vehicleId);

	Vehicle findByVehicleId(Integer vehicleId);

	List<Vehicle> findAll();

/*	List<Vehicle> findByBrandIgnoringCaseLikeAndTypeIgnoringCaseLikeAndVehiclePlateNumberIgnoringCaseLikeAndNumberOfSeatsGreaterThanEqualAndDailyPriceLessThanEqualOrderByVehicleIdDesc(
			String brand, String type, String VehiclePlateNumber, Integer NumberOfSeatsGreater, Double DailyPrice);

	List<Vehicle> findByBrandIgnoringCaseLikeAndTypeIgnoringCaseLikeAndVehiclePlateNumberIgnoringCaseLikeAndNumberOfSeatsGreaterThanEqualAndDailyPriceLessThanEqualAndIsAvailableOrderByVehicleIdDesc(
			String brand, String type, String VehiclePlateNumber, Integer NumberOfSeatsGreater, Double DailyPrice,
			Boolean isAvailable);
*/
	List<Vehicle> findByNumberOfSeatsGreaterThanEqualAndDailyPriceLessThanEqualAndDailyPriceGreaterThanEqualAndIsAvailableOrderByVehicleIdDesc(
            int seats, double maxDailyPrice, double minDailyPrice, boolean avaliable);

	List<Vehicle> findByNumberOfSeatsGreaterThanEqualAndDailyPriceLessThanEqualAndDailyPriceGreaterThanEqualOrderByVehicleIdDesc(
            int seats, double maxDailyPrice, double minDailyPrice);

	@Modifying
	@Query("update Vehicle v set v.brand = ?1, v.type = ?2, v.vehiclePlateNumber = ?3, v.model = ?4, v.numberOfSeats = ?5, v.dailyPrice = ?6, v.isAvailable = ?7 where v.vehicleId = ?8")

	void setVehicleByVehicleId(String brand, String type, String vehiclePlateNumber, int model, int numberOfSeats,
                               double dailyPrice, Boolean isAvailable, int vehicleId);
}
