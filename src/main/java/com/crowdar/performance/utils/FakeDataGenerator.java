package com.crowdar.performance.utils;

import com.github.javafaker.Faker;

/**
 * Class used to generate fake data necesary to create accout, transactions
 * Includes methods for creating plates, anonymous plates, USA plates,
 * addresses, ISO compliant region codes and credit cards
 */
public class FakeDataGenerator {
	private static Faker faker = new Faker();

	public static String generateRandomDni() {
		return Integer.toString(faker.number().numberBetween(10000000, 99000000));
	}
	
}
