package com.quasar;

import com.quasar.entities.Position;
import com.quasar.entities.Satellite;
import com.quasar.services.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class QuasarApplicationTests {

	@Autowired
	private Service service;

	@Test
	public void ValidPosition() throws Exception {
		List<Satellite> satelliteList = new ArrayList<Satellite>();
		satelliteList.add(new Satellite("sato", 142.7, null));
		satelliteList.add(new Satellite("skywalker", 115.5, null));
		satelliteList.add(new Satellite("kenobi", 100, null));
		Position calculatedPosition = service.getPosition(satelliteList);
		Position expectedLocation = new Position(new double[]{-58.3, -69.6});
		assertEquals(expectedLocation.getX(), calculatedPosition.getX());
		assertEquals(expectedLocation.getY(), calculatedPosition.getY());

	}

}
