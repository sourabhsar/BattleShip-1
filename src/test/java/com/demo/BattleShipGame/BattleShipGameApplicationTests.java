package com.demo.BattleShipGame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.config.WebConfig;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {WebConfig.class})
@SpringBootTest
public class BattleShipGameApplicationTests {

	@Test
	public void contextLoads() {
	}

}
